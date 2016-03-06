package chiragshenoy.myportfolio.Models;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public class BaseModel {
    private String status;
    private String message;
    private String code;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
