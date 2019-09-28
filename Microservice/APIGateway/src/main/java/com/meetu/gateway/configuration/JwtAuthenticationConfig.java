package com.meetu.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;

/**
 * Config JWT. Only one property 'raj.security.jwt.secret' is mandatory.
 *
 * @author bharat
 */
public class JwtAuthenticationConfig {

    @Value("${raj.security.jwt.url:/eventservice/category}")
    private String url;

    @Value("${raj.security.jwt.header:Authorization}")
    private String header;

    @Value("${raj.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${raj.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${raj.security.jwt.secret:fuqqqq}")
    private String secret;

    public String getUrl() {
        return url;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationConfig [url=" + url + ", header=" + header + ", prefix=" + prefix + ", expiration="
                + expiration + ", secret=" + secret + "]";
    }

}
