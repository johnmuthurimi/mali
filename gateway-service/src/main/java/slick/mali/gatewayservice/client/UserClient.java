package slick.mali.gatewayservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    /**
     * Get user by email
     * @param email
     * @return
     */
    @GetMapping("/user/{email}")
    UserDetails findUserByEmail(@PathVariable("email") String email);

}