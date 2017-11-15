package sb.app.camel.routes;

import javax.persistence.EntityManager;

import org.apache.camel.Exchange;
import org.apache.camel.component.jpa.DeleteHandler;
import org.springframework.stereotype.Component;

import sb.app.bo.Order;

//@Component -- do not use it c@component, use @bean
/**
 * @bean returns a customizable instance of spring bean, 
 * while @component defines a class that MAY be later instanciated 
 * by spring IoC engine when needed.
 **/
public class OrderProcessedHandler implements DeleteHandler<Order>{
    @Override
    public void deleteObject(EntityManager arg0, Object arg1, Exchange arg2) {
        ((Order) arg1).setProcessed(true);
    }
}
