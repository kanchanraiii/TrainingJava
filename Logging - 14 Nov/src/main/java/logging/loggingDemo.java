package logging;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class loggingDemo {
    private static final Logger logger = LogManager.getLogger(loggingDemo.class);

    public static void main(String[] args) {

        logger.trace("This is a TRACE message");
        logger.debug("This is a DEBUG message");
        logger.info("Application started");
        logger.warn("A warning occurred");
        logger.error("An error occurred");
        logger.fatal("Critical failure!");

        System.out.println("Program finished.");
    }
}
