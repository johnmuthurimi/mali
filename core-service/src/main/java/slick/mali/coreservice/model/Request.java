package slick.mali.coreservice.model;

public class Request<T> {

    /**
     * This is the object param returned
     */
    private T param;

    /**
     * Message returned for every request
     */
    private String hash;

    /**
     * Get param
     */
    public T getParam() {
        return param;
    }

    /**
     * Set the param
     */
    public void setParam(T param) {
        this.param = param;
    }

    /**
     * Get hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }
}