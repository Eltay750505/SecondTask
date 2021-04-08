package ru.gmail.gasimov.secondtask.exception;

public class XmlParserCustomException extends Exception {
    public XmlParserCustomException() {
    }

    public XmlParserCustomException(String message) {
        super(message);
    }

    public XmlParserCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlParserCustomException(Throwable cause) {
        super(cause);
    }
}
