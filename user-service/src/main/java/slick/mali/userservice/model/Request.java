package slick.mali.userservice.model;

public class Request<T> extends BaseModel{

    /**
     * This is the request id
     */
    private int id;

    /**
     * This is the object param returned
     */
    private T param;

    /**
     * Message returned for every request
     */
    private String hash;

    /**
     * Get id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id
     */
    public void setId(int id) {
        this.id = id;
    }

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