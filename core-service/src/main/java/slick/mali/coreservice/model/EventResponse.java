package slick.mali.coreservice.model;

import java.io.Serializable;

/**
 * Domain class for EventResponse
 */
public class EventResponse extends BaseModel implements Serializable {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        /**
         * email of the user
         */
        private String email;

        /**
         * username should be unique
         */
        private String username;

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
}