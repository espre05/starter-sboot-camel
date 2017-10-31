package sb.app.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import sb.app.beans.StringTransformer;

@Component
public class TimeTriggeredRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:hello?period={{timer.period}}").routeId("timePingStream")
                .transform().simple("Ping at ${date:now:yyyy-MM-dd HH:mm:ss}")
                .transform().method(StringTransformer.class, "toUpper")
                .filter(simple("${body} contains 'foo'"))
                    .to("log:foo")
                .end()
                .to("stream:out");
    }

}