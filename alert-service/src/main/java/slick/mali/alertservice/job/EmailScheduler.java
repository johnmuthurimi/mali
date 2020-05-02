package slick.mali.alertservice.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import slick.mali.coreservice.util.LoggerUtil;

/**
 * This is the email scheduler
 */
@Component
public class EmailScheduler {

    /**
     * Logger class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailScheduler.class);

    /**
     * Inject the alertService
     */
    @Autowired
    EmailService emailService;

    /**
     * Scheduler email sending from the config
     */
	@Scheduled(fixedDelay = 5000)
	public void scheduleEmail() {
        try {
            LoggerUtil.info(LOGGER, "Email scheduler started");
            emailService.executeQueue();
        } catch (Exception e) {
            LoggerUtil.error(LOGGER, "Email scheduler  experienced failure");
        } finally {
            LoggerUtil.info(LOGGER, "Email scheduler stopped");
        }
	}
}