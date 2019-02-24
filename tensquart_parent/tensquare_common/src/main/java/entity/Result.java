package entity;

import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean flag, int code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, int code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
