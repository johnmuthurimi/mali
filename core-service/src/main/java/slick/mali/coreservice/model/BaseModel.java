package slick.mali.coreservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    /**
     * String id
     */
    private String id;

    /**
     * created at
     */
    private Timestamp createdAt;

    /**
     * created by
     */
    private String createdBy;

    /**
     * updated at
     */
    private Timestamp updatedAt;

    /**
     * updated by
     */
    private String updatedBy;

    /**
     * enabled
     */
    private Boolean enabled;

    /**
     * deleted
     */
    private Boolean deleted;

    /**
     * Get id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id
     */
    public void setId(String id) {
            this.id = id;
    }

    /**
     * Get the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Set the createdAt
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Set the createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Get the updatedAt
     */
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Set the updatedAt
     */
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Get the updatedBy
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * Set the updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * Get the user enabled
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the user enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get the user deleted
     */
    public Boolean isDeleted() {
        return deleted;
    }

    /**
     * Set the user deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}