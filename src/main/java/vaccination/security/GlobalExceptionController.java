package vaccination.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {
    @ExceptionHandler
    public void handleException(HttpServletRequest req, Exception e) throws Exception {
        log.info("Exception was thrown: ",e);
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            log.info("OPTIONS is requested");
        } else {
            throw e;
        }
    }
}