package com.ascending.demo.api.exception;

import java.io.Serial;

public class ItemNotFoundException extends RuntimeException{


    @Serial
    private static final long serialVersionUID = -6366409572638710331L;

    public ItemNotFoundException() {
        super();
    }
    public ItemNotFoundException(String arg0) {
        super(arg0);
    }
    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }
    public ItemNotFoundException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }
}
