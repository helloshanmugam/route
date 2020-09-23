package com.mc.route.exception;

import com.mc.route.service.RouteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RouteExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RouteExceptionHandler.class);

    @ExceptionHandler(value = Throwable.class)
    public String exception(Throwable e) {
        logger.info("Exception Occured {}", ExceptionUtils.getStackTrace(e));
        return e.getMessage();
    }
}
