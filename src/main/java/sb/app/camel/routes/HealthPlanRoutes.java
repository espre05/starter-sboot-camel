package sb.app.camel.routes;

import org.apache.camel.builder.RouteBuilder;

import sb.app.beans.StringTransformer;

public class HealthPlanRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:firstRoute")
        .routeId("process-householdHealth")
        .log("Camel body: ${body}") //for reach 3 secs?
        
        .to("stream:out"); //send to system out
    }

}