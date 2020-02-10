package slick.mali.user.model;

import java.math.BigInteger;
import javax.persistence.*;

/**
 * Domain class for user
 */
@Entity
@Table(name = "user_hash")
@NamedStoredProcedureQueries({
                @NamedStoredProcedureQuery(name = "sp_user_fetch", procedureName = "sp_user_fetch", resultClasses = {
                                User.class }, parameters = {
                                                @StoredProcedureParameter(name = "pageNumber", type = Integer.class, mode = ParameterMode.IN) }) })
public class User {

        /**
         * User unique id
         */
        @Id
        @Column(name = "id")
        private BigInteger id;

        /**
         * identifier should be unique
         */
        @Column(name = "identifier")
        private String identifier;

        /**
         * email of the user
         */
        @Column(name = "email")
        private String email;

        /**
         * User password
         */
        @Column(name = "value")
        private String value;

        /**
         * user enabled boolean
         */
        @Column(name = "enabled")
        private boolean enabled;

        /**
         * user deleted boolean
         */
        @Column(name = "deleted")
        private boolean deleted;

        /**
         * Get user id
         */
        @Id
        public BigInteger getId() {
                return id;
        }

        /**
         * Set the user id
         */
        public void setId(BigInteger id) {
                this.id = id;
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
        @Column(name = "value")
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
         * Get the user email
         */
        public String getEmail() {
                return email;
        }

        /**
         * Set the user email
         */
        @Column(name = "email")
        public void setEmail(String email) {
                this.email = email;
        }

}