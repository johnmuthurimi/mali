package slick.mali.user.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;
import slick.mali.user.model.User;

/**
 * User Repository
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
        List<User> list = new ArrayList<>();
        try {
            // Execute query
            StoredProcedureQuery query = this.em.createNamedStoredProcedureQuery("sp_user_fetch");
            query.setParameter("pageNumber", pageNumber);
            list = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
        }
        return list;
    }

    /**
     * Adding user in the database
     * 
     * @param page
     * @return User
     */
    public User userAdd(User user) {
        return null;
    }
}