package com.example.shortener.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.shortener.jsonViews.LinkView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Entity
@Table(name = "links")
@Data
public class Link {
	@Id
	private String shortName;

	@Column
	@JsonView({ LinkView.OriginalLinkInfo.class, LinkView.SummaryInfo.class })
	private String original;

	@Column
	@JsonView({ LinkView.SummaryInfo.class })
	private int count;

	private static final String PREFIX = "/short.ly/";

	@JsonProperty(value = "link", index = 1)
	@JsonView({ LinkView.ShortLinkInfo.class, LinkView.SummaryInfo.class })
	public String getShortLink() {
		return PREFIX + shortName;
	}
}
