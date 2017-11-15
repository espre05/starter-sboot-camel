package sb.app.rest;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import sb.app.MyappException;
import sb.app.bo.CalifHouseholdHealthPlan;
import sb.app.bo.Order;
import sb.app.repo.HealthplanRepository;
import sb.app.repo.OrderRepository;
import sb.housekeep.Slf4j;
@Slf4j
@RestController()
@RequestMapping(value = "/api", headers = { "Accept=application/json,application/xml" }, produces = {
        "application/json" })
public class OrderController {
    Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private HealthplanRepository healthplanRepo;
    @ApiOperation(value = "To get an order", nickname = "getOrder", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Order.class),
            @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
    @RequestMapping(value="orders", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Order>> getOrders() throws MyappException {

        log.info("Get master data {}", "Orders");
        log.error("Something else is wrong here");
        try {
            return ResponseEntity.ok(orderRepo.findAll());
        } catch (MyappException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @RequestMapping(value="healthplans", method = RequestMethod.GET)
    public ResponseEntity<Iterable<CalifHouseholdHealthPlan>> getHealthPlans() throws MyappException {

        log.info("Get master data {}", "Orders");
        try {
            return ResponseEntity.ok(healthplanRepo.findAll());
        } catch (MyappException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Autowired
    private ProducerTemplate producer;
    
    @Autowired
    private CamelContext camelContext;

    
    @EndpointInject(uri = "process-householdHealth")
    private FluentProducerTemplate fluentProducer;
    
    @PostMapping(value = "/healthplans", consumes={MediaType.TEXT_PLAIN_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public ResponseEntity<?> sendOrderForBackendProcessing(final HttpServletRequest request, @RequestBody String requestBody) {
        final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody(requestBody).build();
        final Exchange responseExchange = producer.send("process-householdHealth", requestExchange);
//      final String responseBody = responseExchange.getOut().getBody(String.class);
        final String responseBody = fluentProducer.request(String.class);
        final int responseCode = responseExchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class).intValue();
        return ResponseEntity.status(responseCode).body(responseBody);
    }

}
