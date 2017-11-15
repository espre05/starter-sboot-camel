package sb.app;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import sb.app.bo.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class RestEndpointTest {
    public static final String URL = "http://localhost";

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void restCorrectUrlOK() throws Exception {
        mockMvc.perform(get("/books")).andExpect(status().is2xxSuccessful());
        mockMvc.perform(get("/orders")).andExpect(status().is2xxSuccessful());

    };

    @Test
    public void restIncorrectUrlMustFail() throws Exception {
        mockMvc.perform(get("/books1")).andExpect(status().is4xxClientError());
    };

    @Test
    public void post_book_retrieve_and_check_ok() throws Exception {
        Book book = new Book();
        book.setDescription("Prem's book");
        book.setItem("Whatever");
        mockMvc.perform(post("/books", book).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(book))).andExpect(status().is2xxSuccessful())
                .andExpect(redirectedUrl(URL + "/books/3"));
        
        MvcResult result = mockMvc.perform(get("/books/3").contentType(MediaType.APPLICATION_JSON)) //
            .andDo(System.out::println)
            .andExpect(status().is2xxSuccessful())
            .andReturn();
        String bookJasonString = result.getResponse().getContentAsString();
//        Book book3 = null;
//        try {
//            book3 = new ObjectMapper().readValue(bookJasonString.getBytes(), Book.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }   
//       assertEquals(book, book3);
       
    }
    
}