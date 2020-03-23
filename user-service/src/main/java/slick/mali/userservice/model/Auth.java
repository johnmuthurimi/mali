package slick.mali.userservice.model;

/**
 * Domain class for user
 */
public class Auth {

        /**
         * User String id
         */
        private String id;

        /**
         * type of the user authentication e.g Password, PIN
         */
        private String type;

        /**
         * User salt
         */
        private String salt;

        /**
         * email of the user
         */
        private String email;

        /**
         * username should be unique
         */
        private String username;

        /**
         * User password
         */
        private String password;

        /**
         * User status
         */
        private Integer status;

        /**
         * User enabled
         */
        private Boolean enabled;

        /**
         * User deleted
         */
        private Boolean deleted;

        /**
         * Get user id
         */
        public String getId() {
                return id;
        }

        /**
         * Set the user id
         */
        public void setId(String id) {
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
         * Get the username
         */
        public String getusername() {
                return username;
        }

        /**
         * Set the username
         */
        public void setusername(String username) {
                this.username = username;
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