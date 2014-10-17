package com.salmon.test.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Constants {
    TWITTER_SHARE("twitter-share"),
    FACEBOOK_SHARE("facebook-share"),
    PINTEREST_SHARE("pinterest-share");

    @Getter
    String cssClass;
}
