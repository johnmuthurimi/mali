package slick.mali.coreservice.model.alert;

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
         * createdAt
         */
        private Date createdAt;

        /**
         * sentAt
         */
        private Date sentAt;

        /**
         * deliveredAt
         */
        private Date deliveredAt;

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
         * Get createdAt
         */
        public Date getCreatedAt() {
                return createdAt;
        }

        /**
         * Set createdAt
         */
        public void setCreatedAt(Date createdAt) {
                this.createdAt = createdAt;
        }

        /**
         * Get deliveredAt
         */
        public Date getDeliveredAt() {
                return deliveredAt;
        }

        /**
         * Set deliveredAt
         */
        public void setDeliveredAt(Date deliveredAt) {
                this.deliveredAt = deliveredAt;
        }

        /**
         * Get SentAt
         */
        public Date getSentAt() {
                return sentAt;
        }

        /**
         * Set SentAt
         */
        public void setSentAt(Date sentAt) {
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