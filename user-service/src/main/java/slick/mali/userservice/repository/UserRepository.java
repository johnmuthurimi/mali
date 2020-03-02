package slick.mali.userservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import slick.mali.userservice.model.User;

/**
 * User Repository for all user actions
 */
@Repository
public class UserRepository extends JpaRepository<User, Long> {

    List<User> findAll(Pageable pageable);
}