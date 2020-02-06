package slick.mali.user.repository;

import java.util.ArrayList;
import java.util.List;

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
@Transactional
public class UserRepository {

    /**
     * Persist the entity manager
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Use only stored procedures to select from the database A call store procedure
     * with arguments
     * 
     * @param page
     * @param row
     * @return List of users
     */
    public List<User> getUsers(long page, long row) {
        List<User> list = new ArrayList<>();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_getUsers", "User");
        try {
            // Execute query
            // set input parameter
            query.setParameter("page", page);
            query.setParameter("row", row);
            query.execute();
            list = query.getResultList();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                query.unwrap(ProcedureOutputs.class).release();
            } catch (Exception e) {
            }
        }
        return list;
    }
}