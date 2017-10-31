package sb.app.camel.process;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class LoggingProcessor implements Processor{

	@Override
	public void process(Exchange ex) throws Exception {
		System.out.println("Now processing : " + ex.getIn().getBody(String.class));
	}

}
