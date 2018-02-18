package sb.app.conf;

import javax.persistence.EntityManager;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.jpa.DeleteHandler;
import org.apache.camel.component.rest.RestComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sb.app.domain.Order;

@Configuration
public class AppCamelConfig implements CamelContextConfiguration {
    @Override
    public void beforeApplicationStart(CamelContext arg0) {
        // TODO Auto-generated method stub
        System.err.println("################# Before: Camel Context ---- about to start ...");
    }

    @Override
    public void afterApplicationStart(CamelContext arg0) {
        // TODO Auto-generated method stub
        System.err.println("################# After: Camel Context Started");
    }

    @Bean
    public RestComponent createRestComponent() {
        return new RestComponent();
    }

    @Bean(name = "orderProcessedMarker")
    public DeleteHandler<Order> createOrderProcessHandler() {
        return new DeleteHandler<Order>() {
            @Override
            public void deleteObject(EntityManager arg0, Object arg1, Exchange arg2) {
                ((Order) arg1).setProcessed(true);
            }
        };
    }
}
