package slick.mali.userservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import slick.mali.coreservice.model.Response;
import slick.mali.coreservice.model.user.OTP;
import slick.mali.coreservice.model.user.User;
import slick.mali.coreservice.model.Request;
import slick.mali.userservice.service.otp.IOtpService;
import slick.mali.userservice.service.user.IUserService;
import slick.mali.coreservice.controller.BaseController;

import javax.validation.Valid;


/**
 * This class handles all the users request for api v1
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Inject the user service
     */
    @Autowired
    private IUserService userService;

    /**
     * Inject the OTP Service
     */
    @Autowired
    private IOtpService otpService;

    /**
     * Find by Id
     * @param id
     * @return
     */
    @GetMapping("id/{id}")
    public ResponseEntity<Response<User>> findById(@PathVariable(value = "id") String id) {
        try {
            User result = userService.findById(id);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }

    /**
     * Find by email
     * @param email
     * @return
     */
    @GetMapping("email/{email}")
    public ResponseEntity<Response<User>> findByEmail(@PathVariable(value = "email") String email) {
        try {
            User result = userService.findByEmail(email);
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }

    /**
     * This is end point is responsible registered user
     * @param req
     * @return
     */
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Response<User>> register(@Valid @RequestBody Request<User> req) {
        try {
            User result = userService.create(req.getParam());
            return this.successfulResponse(result);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }

    /**
     * This is end point for verification os user email
     * @param  otp
     * @return User
     */
    @GetMapping("/otp/{otp}")
    public ResponseEntity<Response<OTP>> verifyUser(@PathVariable(value = "otp") String otp) {
        try {
            OTP result = otpService.verifyOTP(otp);
            return this.successfulResponse(null);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }

    /**
     * Resend user OTP
     * @param userId
     * @return
     */
    @GetMapping("resend/otp/{id}")
    public ResponseEntity<Response<OTP>> resendOTP(@PathVariable(value = "id") String userId) {
        try {
            String otp = otpService.create(userId);
            return this.successfulResponse(null);
        } catch (Exception e) {
            return this.errorResponse(e.getMessage());
        }
    }
}
