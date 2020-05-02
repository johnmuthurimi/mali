package slick.mali.coreservice.model;

import java.io.Serializable;

/**
 * Domain class for EventResponse
 */
public class EventResponse extends BaseModel implements Serializable {

        /**
         * serialize version
         */
        private static final long serialVersionUID = 1L;

        /**
         * firstName of the user
         */
        private String firstName;

        /**
         * email of the user
         */
        private String email;

        /**
         * otp of the user
         */
        private String otp;


        /**
         * Get the user firstName
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * Set the user firstName
         */
        public void setFirstName(String firstName) {
                this.firstName = firstName;
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
         * Get the user otp
         */
        public String getOtp() {
                return otp;
        }

        /**
         * Set the user token
         */
        public void setOtp(String otp) {
                this.otp = otp;
        }
}