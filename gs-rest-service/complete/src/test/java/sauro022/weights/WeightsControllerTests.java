package sauro022.weights;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeightsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void callWithValidURLReturnsOkAndHasExpectedData () throws Exception {
    	assertTrue(this.mockMvc.perform(get("/weights").param("stringToParse", "ant boat row;;ANT@car ant die£ant BOat row ant"))
    		    .andExpect(status().isOk())
    		    .andReturn()
    		    .getResponse()
    		    .getContentAsString()
    		    .contains("00-10-010-00-11-00-011-00-10-010-00"));
    }
    
    @Test
    public void callWithInvalidURLReturns400 () throws Exception {
    	this.mockMvc.perform(get("/weights").param("url", "http:://news.bbc.co.uk"))
    		    .andExpect(status().isBadRequest());
    }
}
