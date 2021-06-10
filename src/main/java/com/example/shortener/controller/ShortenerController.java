package com.example.shortener.controller;

import com.example.shortener.jsonViews.LinkView;
import com.example.shortener.model.Link;
import com.example.shortener.service.ShortenerService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShortenerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShortenerController.class);
    private ShortenerService shortenerService;

    @Autowired
    public void setShortenerService(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @JsonView(LinkView.ShortLinkInfo.class)
    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Link generate(@RequestBody Link link) {
        LOGGER.info("The request to generate short link was received");
        LOGGER.debug(String.format("The object with next properties was received: %s", link));
        String shortName = shortenerService.generateShortName(link.getOriginal());
        LOGGER.info(String.format("The short name of original link: %s was generated", shortName));
        link.setShortName(shortName);
        shortenerService.createLink(link);
        return link;
    }

    @GetMapping(value = "/short.ly/{shortName}")
    public ModelAndView redirectByShortName(@PathVariable String shortName) {
        LOGGER.info(String.format("The request to redirect by short link (/short.ly/%s) was received", shortName));
        ModelAndView modelAndView = new ModelAndView();
        Link link = shortenerService.getLinkByShortName(shortName);
        int incremented = link.getCount();
        link.setCount(incremented + 1);
        shortenerService.updateLink(link);
        modelAndView.setViewName("redirect:" + link.getOriginal());
        return modelAndView;
    }

    @JsonView(LinkView.SummaryInfo.class)
    @GetMapping(value = "/stats/{shortName}")
    @ResponseBody
    public Link showStatistics(@PathVariable String shortName) {
        return shortenerService.getLinkByShortName(shortName);
    }

    @JsonView(LinkView.SummaryInfo.class)
    @GetMapping(value = "/stats")
    @ResponseBody
    public List<Link> showStatistics(@RequestParam int page, @RequestParam int count) {
        return shortenerService.getListOfLinks(page, count);
    }
}
