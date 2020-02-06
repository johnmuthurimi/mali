package slick.mali.user.model;

import java.util.UUID;
import javax.persistence.*;

/**
 * Domain class for user
 */
@MappedSuperclass
@SqlResultSetMapping(name = "User", classes = @ConstructorResult(targetClass = User.class, columns = {
        @ColumnResult(name = "id", type = String.class), @ColumnResult(name = "username", type = String.class) }))
public class User {

    /**
     * Auto generated uuid identifier for user
     */
    private UUID id;

    /**
     * Username should be unique
     */
    private String username;

    /**
     * Firstname of the user and allow nulls
     */
    private String firstName;

    /**
     * LastName of the user and allow nulls
     */
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