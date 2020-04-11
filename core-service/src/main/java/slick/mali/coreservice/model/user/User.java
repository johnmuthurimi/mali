package slick.mali.coreservice.model.user;


import slick.mali.coreservice.model.BaseModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Domain class for user
 */
public class User extends BaseModel {

        /**
         * First name
         */
        private String firstName;

        /**
         * First name
         */
        private String lastName;

        /**
         * email of the user
         */
        @NotNull(message = "Please provide email")
        @Email
        private String email;

        /**
         * User password
         */
        @NotNull(message="Password is a required field")
        @Size(min=8, max=16, message="Password must be equal to or greater than 8 characters and less than 16 characters")
        private String password;

        /**
         * User salt
         */
        private String salt;

        /**
         * User status
         */
        private Integer status;

        /**
         * User verified
         */
        private Boolean verified;

        /**
         * Get the firstName
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * Set the firstName
         */
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        /**
         * Get the lastName
         */
        public String getLastName() {
                return lastName;
        }

        /**
         * Set the lastName
         */
        public void setLastName(String lastName) {
                this.lastName = lastName;
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
         * Get the user deleted
         */
        public Boolean isVerified() {
                return verified;
        }

        /**
         * Set the user deleted
         */
        public void setVerified(Boolean verified) {
                this.verified = verified;
        }
}