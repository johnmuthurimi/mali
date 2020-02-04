package slick.mali.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The BaseController class implements common functionality for all Controller
 * classes. The <code>@ExceptionHandler</code> methods provide a consistent
 * response when Exceptions are thrown from <code>@RequestMapping</code>
 * annotated Controller methods.
 * 
 */
public class BaseController {

    /**
     * Create Logger class name
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}