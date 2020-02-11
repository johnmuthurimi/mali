package slick.mali.user.model;

import java.math.BigInteger;
import javax.persistence.*;

/**
 * Domain class for user
 */
@Entity
@Table(name = "user_hash")
@NamedStoredProcedureQueries(
{
        @NamedStoredProcedureQuery(name = "sp_user_fetch", procedureName = "sp_user_fetch", resultClasses = { User.class }, 
        parameters = {
                @StoredProcedureParameter(name = "pageNumber", type = Integer.class, mode = ParameterMode.IN) 
        }),
        @NamedStoredProcedureQuery(name = "sp_user_add", procedureName = "sp_user_add", resultClasses = { User.class }, 
        parameters = {
                @StoredProcedureParameter(name = "type", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "email", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "identifier", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "value", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "status", type = Integer.class, mode = ParameterMode.IN) 
        }) 
})
public class User {

        /**
         * User unique id
         */
        @Id
        @Column(name = "id")
        private BigInteger id;

        /**
         * type of the user authentication
         */
        @Column(name = "type")
        private String type;

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
         * User password
         */
        private String password;

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
         * user status
         */
        @Column(name = "status")
        private Integer status;

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

        /**
         * Get the user status
         */
        public Integer getStatus() {
                return status;
        }

        /**
         * Set the user status
         */
        @Column(name = "status")
        public void setStatus(Integer status) {
                this.status = status;
        }

}