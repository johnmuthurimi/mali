package slick.mali.coreservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import slick.mali.coreservice.model.Response;
import slick.mali.coreservice.constants.ResultCode;

/**
 * The BaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 * 
 */
@RestController
public abstract class BaseController {

    /**
     * Create Logger class name
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * Generic response for all successful requests
     * @param <T> type
     * @param result result object
     * @return ResponseEntity
     */
    public <T> ResponseEntity<Response<T>> successfulResponse(T result) {
        Response<T> res = new Response<T>();
        res.setCode(ResultCode.SUCCESS);
        res.setMessage("success");
        res.setResult(result);
        return new ResponseEntity<Response<T>>(res, HttpStatus.OK);
    }

    /**
     * Generic Reponse for all error requests
     * @param message
     * @return
     */
    public <T> ResponseEntity<Response<T>> errorResponse(String message) {
        Response<T> res = new Response<T>();
        res.setCode(ResultCode.ERROR);
        res.setMessage(message);
        return new ResponseEntity<Response<T>>(res, HttpStatus.OK);
    }    
}