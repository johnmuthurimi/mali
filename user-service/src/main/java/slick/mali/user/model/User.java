package slick.mali.user.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Domain class for user
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * Auto generated uuid identifier for user
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", length = 16, unique = true, nullable = false)
    private UUID id;

    /**
     * Username should be unique
     */
    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 100)
    private String username;

    /**
     * Firstname of the user and allow nulls
     */
    @NotBlank
    @Column(name = "firstName", nullable = false)
    @Size(max = 50)
    private String firstName;

    /**
     * LastName of the user and allow nulls
     */
    @Column(name = "lastName", nullable = false)
    @Size(max = 50)
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