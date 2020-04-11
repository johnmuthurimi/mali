package slick.mali.coreservice.model.alert;

import java.sql.Timestamp;
import java.util.Date;
import slick.mali.coreservice.model.BaseModel;

/**
 * Domain class for Email
 */
public class EmailRequest extends BaseModel {

        /**
         * sender
         */
        private String sender;

        /**
         * recepient
         */
        private String recepient;

        /**
         * email message
         */
        private String message;

        /**
         * sentAt
         */
        private Timestamp sentAt;

        /**
         * deliveredAt
         */
        private Timestamp deliveredAt;

        /**
         * User status
         */
        private Integer status;

        /**
         * Get the sender
         */
        public String getSender() {
                return sender;
        }

        /**
         * Set the sender
         */
        public void setSender(String sender) {
                this.sender = sender;
        }

        /**
         * Get the recepient
         */
        public String getRecepient() {
                return recepient;
        }

        /**
         * Set the recepient
         */
        public void setRecepient(String recepient) {
                this.recepient = recepient;
        }

        /**
         * Get the message
         */
        public String getMessage() {
                return message;
        }

        /**
         * Set message
         */
        public void setMessage(String message) {
                this.message = message;
        }

        /**
         * Get deliveredAt
         */
        public Timestamp getDeliveredAt() {
                return deliveredAt;
        }

        /**
         * Set deliveredAt
         */
        public void setDeliveredAt(Timestamp deliveredAt) {
                this.deliveredAt = deliveredAt;
        }

        /**
         * Get SentAt
         */
        public Timestamp getSentAt() {
                return sentAt;
        }

        /**
         * Set SentAt
         */
        public void setSentAt(Timestamp sentAt) {
                this.sentAt = sentAt;
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

}