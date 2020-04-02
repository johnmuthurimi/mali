package slick.mali.coreservice.model;

import java.io.Serializable;

/**
 * Domain class for EventRequest
 */
public class EventRequest extends BaseModel implements Serializable {

        /**
         * serialize version
         */
        private static final long serialVersionUID = 1L;

        /**
         * email of the user
         */
        private String email;

        /**
         * token of the user
         */
        private String token;

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
         * Get the user token
         */
        public String getToken() {
                return token;
        }

        /**
         * Set the user token
         */
        public void setToken(String email) {
                this.token = token;
        }
}