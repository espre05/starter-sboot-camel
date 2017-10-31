package sb.app.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import sb.app.beans.StringTransformer;

@Component
public class TimeTriggeredRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}")//for reach 3 secs?
            .routeId("timePingStream")
            .transform().simple("Ping at ${date:now:yyyy-MM-dd HH:mm:ss}") //produce this TS string
            .transform().method(StringTransformer.class, "toUpper") //transform to UPPER ase with a bean
            .filter(simple("${body} contains 'foo'"))
                .to("log:foo")// ig it has foo in bod
            .end()
            .to("stream:out"); //send to system out
    }

}