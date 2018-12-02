package br.edu.senac.auto.security.exception;

import java.io.Serializable;

public class InvalidTokenException extends Exception {

    public InvalidTokenException(String message) {
        super(message);
    }
}
