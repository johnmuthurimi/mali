package slick.mali.user.repository;

import java.util.List;
import java.util.UUID;
import slick.mali.user.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * User Repository
 */
@Component
@Transactional
public interface UserRepository extends CrudRepository<User, UUID> {

    /**
     * Use only stored procedures to select from the database
     *  A call store procedure with arguments
     */
    @Query(nativeQuery = true, value = "call sp_getUsers(:page:row)")
    List<User> getUsers(@Param("page")long page, @Param("row")long row);
}