package com.example.shortener.controller;

import com.example.shortener.jsonViews.LinkView;
import com.example.shortener.model.Link;
import com.example.shortener.model.User;
import com.example.shortener.service.LinkService;
import com.example.shortener.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class GeneralController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneralController.class);
    private LinkService linkService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setShortenerService(LinkService linkService) {
        this.linkService = linkService;
    }

    @JsonView(LinkView.ShortLinkInfo.class)
    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Link generate(@RequestBody Link link, Principal principal) {
        String shortname = linkService.generateShortName(link.getOriginal());
        link.setShortname(shortname);
        Optional.ofNullable(principal).ifPresent(authenticatedPrincipal -> {
            User author = userService.loadUserByUsername(principal.getName()).getUser();
            link.setAuthor(author);
        });
        linkService.create(link);
        return link;
    }

    @GetMapping(value = "/profile/links")
    public ModelAndView allProfileLinks(Principal principal, @RequestParam(defaultValue = "5") int records,
                                        @RequestParam(defaultValue = "1") int page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        User authenticated = userService.loadUserByUsername(principal.getName()).getUser();
        List<Link> profileLinks = authenticated.getLinks();
        int profileLinksSize = profileLinks.size();
        int profileLinksParts = (int) Math.ceil(profileLinksSize / records);
        int fromIndex = records * (page - 1);
        int assumedIndex = fromIndex + records;
        int toIndex = assumedIndex > profileLinksSize ? profileLinksSize : assumedIndex;
        List<Link> truncatedProfileLinks = profileLinks.subList(fromIndex, toIndex);
        modelAndView.addObject("truncatedList", truncatedProfileLinks);
        modelAndView.addObject("pagesCount", profileLinksParts);
        modelAndView.addObject("records", records);
        modelAndView.addObject("page", page);
        return modelAndView;
    }

    @GetMapping(value = "/link/{shortName}")
    public ModelAndView redirectByShortName(@PathVariable String shortName) {
        LOGGER.info(String.format("The request to redirect by short link %s was received", shortName));
        ModelAndView modelAndView = new ModelAndView();
        Link link = linkService.getLinkByShortName(shortName);
        int incremented = link.getCount();
        link.setCount(incremented + 1);
        linkService.update(link);
        modelAndView.setViewName("redirect:" + link.getOriginal());
        return modelAndView;
    }

    @DeleteMapping("/delete/{shortname}")
    public void deleteLink(@PathVariable String shortname) {
        Link deletable = linkService.getLinkByShortName(shortname);
        linkService.delete(deletable);
    }

    @JsonView(LinkView.RenamingLinkInfo.class)
    @PutMapping(value = "rename", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> renameLink(@RequestBody Link link) {
        Link editable = linkService.getLinkByShortName(link.getOld_shortname());
        String newShortname = link.getShortname();
        editable.setShortname(newShortname);
        linkService.update(editable);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
