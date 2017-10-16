package spring.boot.mvc.exception;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Error404Controller implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public ModelAndView handleError() {
	return new ModelAndView("404");
    }

    @Override
    public String getErrorPath() {
	return ERROR_PATH;
    }
}