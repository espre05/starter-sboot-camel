package sb.app.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import sb.app.repo.BookRepository;
import sb.app.repo.Db;
import org.springframework.stereotype.Component;

//@Component //TODO Find options: disabled, conflicting with springboot-web and camel-servlet
public class RestApi extends RouteBuilder {

	@Override
	public void configure() {
		restConfiguration()//
				.contextPath("/camel-rest-jpa").apiContextPath("/api-doc")//
				.apiProperty("api.title", "Camel REST API")//
				.apiProperty("api.version", "1.0")//
				.apiProperty("cors", "true")//
				.apiContextRouteId("doc-api")//
				.bindingMode(RestBindingMode.json);

		rest("/books").description("Books REST service")//
				.get("/").description("The list of all the books")//
				.route().routeId("books-api")//
				.bean(BookRepository.class, "findAll")//check if it works -- if not revert to Db.class
				.endRest().get("order/{id}").description("Details of an order by id")//
				.route().routeId("order-api")//
				.bean(Db.class, "findOrder(${header.id})");
	}
}