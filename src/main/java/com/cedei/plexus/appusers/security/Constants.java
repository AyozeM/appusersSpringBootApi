package com.cedei.plexus.appusers.security;

/**
 * Constants
 */

public class Constants {

    final String LOGIN_URL = "/api/login";

    final String HEADER_AUTORIZACION_KEY = "Authorization";

    final String TOKEN_BEARER_PREFIX = "Bearer";

    final String SUPER_SECRET_KEY = "Sus0_3n_Sus0l@d1@";

    public String getLOGIN_URL() {
        return this.LOGIN_URL;
    }

    public String getHEADER_AUTORIZACION_KEY() {
        return this.HEADER_AUTORIZACION_KEY;
    }

    public String getTOKEN_BEARER_PREFIX() {
        return this.TOKEN_BEARER_PREFIX;
    }

    public String getSUPER_SECRET_KEY() {
        return this.SUPER_SECRET_KEY;
    }
}