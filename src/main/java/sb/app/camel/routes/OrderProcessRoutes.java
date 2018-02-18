package sb.app.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
class OrderProcessRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // A first route generates some orders and queue them in DB
        from("timer:new-order?delay=1s&period={{example.generateOrderPeriod:10s}}")
            .routeId("generate-order") // name
            .bean("orderService", "constructOrder") // create new Order
            .to("jpa:sb.app.domain.Order") //insert
            .log("Inserted new order ${body.id}");

        // A second route polls the DB for new orders and processes them
        from("jpa:sb.app.domain.Order"  //select * from orders where consumed = false
            + "?consumer.namedQuery=query_new_orders"
            + "&consumer.delay={{example.processOrderPeriod:5s}}"
            + "&consumeDelete=false"
            + "&deleteHandler=#orderProcessedMarker")
            .routeId("process-order") //«${body.book.description}» 
            .log("Processed order #id ${body.id} with ${body.amount} copies of the book");
    }
}