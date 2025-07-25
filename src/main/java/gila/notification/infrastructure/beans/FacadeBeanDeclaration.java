package gila.notification.infrastructure.beans;

import gila.notification.application.facades.NotifyUsersFacadeImpl;
import gila.notification.domain.interfaces.facades.NotifyUsersFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FacadeBeanDeclaration {

    @Bean
    public NotifyUsersFacade notifyUsersFacade() {
        return new NotifyUsersFacadeImpl();
    }
}
