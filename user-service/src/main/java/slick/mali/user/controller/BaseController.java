package slick.mali.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The BaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 * 
 */
@RestController
@RequestMapping("/")
public class BaseController {

    /**
     * Create Logger class name
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}