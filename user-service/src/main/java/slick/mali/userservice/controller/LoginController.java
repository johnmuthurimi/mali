package slick.mali.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import slick.mali.coreservice.controller.BaseController;
import slick.mali.coreservice.model.Request;
import slick.mali.coreservice.model.Response;
import slick.mali.coreservice.model.user.User;
import slick.mali.userservice.security.JwtTokenUtil;
import slick.mali.userservice.security.JwtUserDetailsService;
import slick.mali.userservice.service.Auth.IAuthService;

import javax.validation.Valid;


public class LoginController extends BaseController {

    /**
     * Inject authentication manager
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Inject the jwtTokenUtil
     */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Inject the userDetailsService
     */
    @Autowired
    private JwtUserDetailsService userDetailsService;

    /**
     * Inject the user service
     */
    @Autowired
    private IAuthService authService;

    /**
     * Use this end point for login
     * @param req
     * @return
     */
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<User>> authenticate(@Valid @RequestBody Request<User> req) {


        try {
            User jwtRequest = req.getParam();
            boolean valid = authService.validateCredentials(jwtRequest.getEmail(), jwtRequest.getPassword());
            if (valid) {
                authService.generateAuthenticationToken(authenticationManager, jwtRequest.getEmail(), jwtRequest.getPassword());
                final UserDetails userDetails = userDetailsService
                        .loadUserByUsername(jwtRequest.getEmail());
                final String token = jwtTokenUtil.generateToken(userDetails);
                jwtRequest.setToken(token);
                return this.successfulResponse(jwtRequest);

            } else {
                throw new Exception("Wrong email or password");
            }
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }
}
