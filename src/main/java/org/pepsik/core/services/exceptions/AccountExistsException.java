package org.pepsik.core.services.exceptions;

/**
 * Created by pepsik on 10/1/2015.
 */
public class AccountExistsException extends RuntimeException {

    public AccountExistsException() {
    }

    public AccountExistsException(String message) {
        super(message);
    }
}
