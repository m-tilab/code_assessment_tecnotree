package io.project.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CrudOperationLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger("CrudOperations");

    public void createInfo(Object object) {
        LOGGER.info(" CREATE %s".formatted(object.toString()));
    }

    public void readInfo(Object object) {
        LOGGER.info(" READ %s".formatted(object.toString()));
    }

    public void deleteInfo(Object object) {
        LOGGER.info(" DELETE %s".formatted(object.toString()));
    }

    public void updateInfo(Object object) {
        LOGGER.info(" UPDATE %s".formatted(object.toString()));
    }
}
