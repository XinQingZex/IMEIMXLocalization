package com.agile.qdmp.standalong.tool.error;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 11:37
 */
public class IMErrorException extends Exception {
    private static final long serialVersionUID = -6357149550353160810L;

    private final IMError error;

    private static final int DEFAULT_ERROR_CODE = -99;

    public IMErrorException(String message) {
        this(IMError.builder().errorCode(DEFAULT_ERROR_CODE).errorMsg(message).build());
    }

    public IMErrorException(IMError error) {
        super(error.toString());
        this.error = error;
    }

    public IMErrorException(IMError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public IMErrorException(Throwable cause) {
        super(cause.getMessage(), cause);
        this.error = IMError.builder().errorCode(DEFAULT_ERROR_CODE).errorMsg(cause.getMessage()).build();
    }

    public IMError getError() {
        return this.error;
    }
}
