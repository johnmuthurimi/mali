package slick.mali.user.model;

import java.util.UUID;
import javax.persistence.*;

/**
 * Domain class for user
 */
@Entity
@Table(name = "user")
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(
    name = "sp_user_fetch", 
    procedureName = "USER_FETCH", 
    resultClasses = { User.class }, 
    parameters = { 
        @StoredProcedureParameter(
          name = "pageNumber", 
          type = Integer.class) }) 
})
public class User {

    /**
     * Auto generated uuid identifier for user
     */
    @Id
    private UUID id;

    /**
     * Username should be unique
     */
    @Column(name = "username")
    private String username;

    /**
     * Firstname of the user and allow nulls
     */
    @Column(name = "firstName")
    private String firstName;

    /**
     * LastName of the user and allow nulls
     */
    @Column(name = "lastName")
    private String lastName;

    /**
     * Get user id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Set the user id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Get the username
     */    
    public String getUsername() {
        return username;
    }

    /**
     * Set the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the user lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the user lastname
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}