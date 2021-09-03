package com.example.shortener.model;

import com.example.shortener.jsonViews.LinkView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Link {
    private static final String PREFIX = "/link/";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int link_id;
    @Column
    @JsonView({LinkView.RenamingLinkInfo.class})
    private String shortname;
    @Column
    @JsonView({LinkView.OriginalLinkInfo.class, LinkView.SummaryInfo.class})
    private String original;
    @Column
    @JsonView({LinkView.SummaryInfo.class})
    private int count;
    @ManyToOne
    private User author;

    @Transient
    @JsonView({LinkView.RenamingLinkInfo.class})
    @JsonProperty
    private String old_shortname;

    @JsonProperty(value = "link", index = 1)
    @JsonView({LinkView.ShortLinkInfo.class, LinkView.SummaryInfo.class})
    public String getShortLink() {
        return PREFIX + shortname;
    }
}
