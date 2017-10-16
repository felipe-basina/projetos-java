package spring.boot.mvc.jpa.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

    /*public static final String VIEW_NOT_FOUND = "error/404";

    @RequestMapping(value = "/error/404", method = RequestMethod.GET)
    public String show404Page() {
        LOGGER.debug("Rendering 404 page");
        return VIEW_NOT_FOUND;
    }*/

    @RequestMapping(value = "/500/error", method = RequestMethod.GET)
    public String showInternalServerErrorPage() {
        LOGGER.debug("Rendering internal server error page");
        return "error";
    }
}
