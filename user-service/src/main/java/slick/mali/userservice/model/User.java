package slick.mali.userservice.model;

import javax.persistence.*;

/**
 * Domain class for user
 */
@Entity
@Table(name = "user_hash")
public class User {

        /**
         * User unique id
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /**
         * type of the user authentication e.g Password, PIN
         */
        @NotNull
        @Size(max = 10)
        @Column(columnDefinition = "VARCHAR(10) NOT NULL")
        private String type;

        /**
         * User salt
         */
        @NotNull
        @Size(max = 50)
        @Column(columnDefinition = "VARCHAR(50) NOT NULL")
        private String salt;

        /**
         * email of the user
         */
        @NotNull
        @Email
        @Size(max = 50)
        @Column(columnDefinition = "VARCHAR(50) NOT NULL")
        private String email;

        /**
         * identifier should be unique
         */
        @NotNull
        @Size(max = 20)
        @Column(columnDefinition = "VARCHAR(20) NOT NULL")
        private String identifier;

        /**
         * User value
         */
        @NotNull
        @Size(max = 512)
        @Column(columnDefinition = "VARCHAR(512) NOT NULL")
        private String value;

        /**
         * User password
         */
        private String password;

        /**
         * User status
         */
        @NotNull
        @Size(max = 10)
        @Column(columnDefinition = "INT(10) NOT NULL")
        private Integer status;

        /**
         * User failedAttempts
         */
        @NotNull
        @Size(max = 10)
        @Column(columnDefinition = "INT(10) DEFAULT 0 NOT NULL")
        private Integer failedAttempts;

        /**
         * User lastAttempt
         */
        @NotNull
        @Column(columnDefinition = "DATE NOT NULL")
        private Date lastAttempt;

        /**
         * User expireDate
         */
        @NotNull
        @Column(columnDefinition = "DATE NOT NULL")
        private Date expireDate;

        /**
         * User enabled
         */
        @NotNull
        @Column(columnDefinition = "BOOLEAN DEFAULT false")
        private Boolean enabled;

        /**
         * User deleted
         */
        @NotNull
        @Column(columnDefinition = "BOOLEAN DEFAULT false")
        private Boolean deleted;

        /*
        https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-one-mapping-example/
        @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
        private UserProfile userProfile;
        */

        /**
         * Get user id
         */
        @Id
        public Long getId() {
                return id;
        }

        /**
         * Set the user id
         */
        public void setId(Long id) {
                this.id = id;
        }

        /**
         * Get the type
         */
        public String getType() {
                return type;
        }

        /**
         * Set the type
         */
        public void setType(String type) {
                this.type = type;
        }

        /**
         * Get the salt
         */
        public String getSalt() {
                return salt;
        }

        /**
         * Set the salt
         */
        public void setSalt(String salt) {
                this.salt = salt;
        }

        /**
         * Get the user email
         */
        public String getEmail() {
                return email;
        }

        /**
         * Set the user email
         */
        public void setEmail(String email) {
                this.email = email;
        }

        /**
         * Get the identifier
         */
        public String getIdentifier() {
                return identifier;
        }

        /**
         * Set the identifier
         */
        public void setIdentifier(String identifier) {
                this.identifier = identifier;
        }

        /**
         * Get the value
         */
        public String getValue() {
                return value;
        }

        /**
         * Set the value
         */
        public void setValue(String value) {
                this.value = value;
        }

        /**
         * Get the password
         */
        public String getPassword() {
                return password;
        }

        /**
         * Set the password
         */
        public void setPassword(String password) {
                this.password = password;
        }

        /**
         * Get the user status
         */
        public Integer getStatus() {
                return status;
        }

        /**
         * Set the user status
         */
        public void setStatus(Integer status) {
                this.status = status;
        }

        /**
         * Get the user failedAttempts
         */
        public Integer getFailedAttempts() {
                return failedAttempts;
        }

        /**
         * Set the user failedAttempts
         */
        public void setFailedAttempts(Integer failedAttempts) {
                this.failedAttempts = failedAttempts;
        }

        /**
         * Get the user LastAttempt
         */
        public Date getLastAttempt() {
                return lastAttempt;
        }

        /**
         * Set the user LastAttempt
         */
        public void setLastAttempt(Integer lastAttempt) {
                this.lastAttempt = lastAttempt;
        }

        /**
         * Get the user expireDate
         */
        public Date getExpireDate() {
                return expireDate;
        }

        /**
         * Set the user expireDate
         */
        public void setExpireDate(Date expireDate) {
                this.expireDate = expireDate;
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