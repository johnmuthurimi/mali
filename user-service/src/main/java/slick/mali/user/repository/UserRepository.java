package slick.mali.user.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import slick.mali.user.model.User;

/**
 * User Repository for all user actions
 */
@Repository
public class UserRepository {

    /**
     * Persist the entity manager
     */
    @Autowired
    private EntityManager em;

    /**
     * Use only stored procedures to select from the database A call store procedure
     * with arguments
     * 
     * @param page
     * @return List of users
     */
    public List<User> userFetch(int pageNumber) {
        StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("sp_user_fetch");
        query.setParameter("pageNumber", pageNumber);
        query.execute();
        return query.getResultList();
    }

    /**
     * Create user in the database
     * 
     * @param user user request
     * @return User
     */
    public User userAdd(User user) {
        StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("sp_user_add");
        query.setParameter("type", user.getType());
        query.setParameter("email", user.getEmail());
        query.setParameter("identifier", user.getIdentifier());
        query.setParameter("value", user.getValue());
        query.setParameter("status", user.getStatus());
        query.execute();
        return (User) query.getSingleResult();
    }
}