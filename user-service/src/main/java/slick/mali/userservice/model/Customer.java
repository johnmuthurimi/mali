package slick.mali.userservice.model;


/**
 * Domain class for customer
 */
public class Customer {

        /**
         * User unique id
         */
        private Long id;

        
        /**
         * Get user id
         */
        public Long getId() {
                return id;
        }

        /**
         * Set the user id
         */
        public void setId(Long id) {
                this.id = id;
        }

}