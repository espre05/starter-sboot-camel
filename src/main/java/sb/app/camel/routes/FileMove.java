package sb.app.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import sb.app.beans.StringTransformer;
import sb.app.camel.process.LoggingProcessor;

@Component
public class FileMove extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:/tmp/camelapp/input?move=./done")//
				.process(new LoggingProcessor())//
				.transform().method(StringTransformer.class, "toUpper")//
				.process(new LoggingProcessor())//
				.to("file:/tmp/output");
	}

}
