package slick.mali.userservice.model;

public class Response<T> {

    /**
     * This is the response code
     */
    private int code;

    /**
     * This is the object result returned
     */
    private T result;

    /**
     * Message returned for every request
     */
    private String message;

    /**
     * Get code
     */
    public int getCode() {
        return code;
    }

    /**
     * Set the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Get result
     */
    public T getResult() {
        return result;
    }

    /**
     * Set the result
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Get message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}