package sb.app.camel.routes;

import javax.persistence.EntityManager;

import org.apache.camel.Exchange;
import org.apache.camel.component.jpa.DeleteHandler;
import org.springframework.stereotype.Component;

import sb.app.bo.Order;

@Component
public class OrderProcessedHandler implements DeleteHandler<Order>{
    @Override
    public void deleteObject(EntityManager arg0, Object arg1, Exchange arg2) {
        ((Order) arg1).setProcessed(true);
    }
}
