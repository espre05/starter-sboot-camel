package sb.app.conf;

import org.apache.camel.CamelContext;
import org.apache.camel.component.rest.RestComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCamelConfig implements CamelContextConfiguration{
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
}
