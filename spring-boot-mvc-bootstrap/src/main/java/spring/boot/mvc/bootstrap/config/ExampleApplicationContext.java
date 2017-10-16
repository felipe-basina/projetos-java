package spring.boot.mvc.bootstrap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({WebMvcConfig.class, PersistenceConfig.class})
@PropertySource("classpath:application.properties")
public class ExampleApplicationContext {

    /*private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }*/
}
