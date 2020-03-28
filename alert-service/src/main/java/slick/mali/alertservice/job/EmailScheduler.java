package slick.mali.alertservice.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slick.mali.alertservice.service.IAlertService;

/**
 * This is the email scheduler
 */
@Component
public class EmailScheduler {

	private static final Logger log = LoggerFactory.getLogger(EmailScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    /**
     * Environment variable
     */
    @Autowired
    private Environment env;

    /**
     * Inject the alertService
     */
    @Autowired
    IAlertService alertService;

    /**
     * Scheduler email sending from the config
     */
	@Scheduled(fixedDelay = 5000)
	public void scheduleEmail() {
		alertService.sendEmail();
	}
}