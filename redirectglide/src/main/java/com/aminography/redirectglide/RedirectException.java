package com.aminography.redirectglide;

import java.io.IOException;

/**
 * When an exception occurs during redirection, a {@link RedirectException} would be thrown.
 */
public class RedirectException extends IOException {

    private int statusCode;

    public RedirectException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
