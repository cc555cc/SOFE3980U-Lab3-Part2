package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    //test the default page with no parameters//
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
    //test the default page with parameters//
    //test get request with parameters//
	@Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }

    //test post request with parameters//
	@Test
	    public void postParameter1() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }

    //test post request with parameters of different length//
    @Test
    public void postParameter2() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","11111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "100110"))
            .andExpect(model().attribute("operand1", "111"));
    }

    //test post request with leading zeros//
    @Test
    public void postParameter3() throws Exception{
        this.mvc.perform(post("/").param("operand1","00111").param("operator","+").param("operand2","000111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))
            .andExpect(model().attribute("operand1", "00111"));
    }

    //test post request with carry over//
    @Test
    public void postParameter4() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1110"))
            .andExpect(model().attribute("operand1", "111"));       
    }

    //test post request with multiplication: basic case//
    @Test
    public void postParameterWithMultiplication1() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110001"))
            .andExpect(model().attribute("operand1", "111"));   
    }

    //test post request with multiplication: with leading zeros//
    @Test
    public void postParameterWithMultiplication2() throws Exception{
        this.mvc.perform(post("/").param("operand1","00111").param("operator","*").param("operand2","000111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110001"))
            .andExpect(model().attribute("operand1", "00111"));
    }

    //test post request with multiplication: with carry over//
    @Test
    public void postParameterWithMultiplication3() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "110001"))
            .andExpect(model().attribute("operand1", "111"));           
    }

    //test post request with multiplication: with different length//
    @Test
    public void postParameterWithMultiplication4() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","11111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "11011001"))
            .andExpect(model().attribute("operand1", "111"));
    }

    //test post request with multiplication: by zero//
    @Test
    public void postParameterWithMultiplication5() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","*").param("operand2","0"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "0"))
            .andExpect(model().attribute("operand1", "111"));
    }

    //test post request with intersection: basic case//
    @Test
    public void postParameterWithIntersection1() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","&").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "111")); 
    }

    //test post request with intersection: with leading zeros//
    @Test
    public void postParameterWithIntersection2() throws Exception{
        this.mvc.perform(post("/").param("operand1","00111").param("operator","&").param("operand2","0001010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "00111"));     
    }

    //test post request with intersection: with different length//
    @Test
    public void postParameterWithIntersection3() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","&").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "10"))
            .andExpect(model().attribute("operand1", "111"));   
    }

    //test post request with union: basic case//
    @Test
    public void postParameterWithUnion1() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","|").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "111"));
    }

    //test post request with union: with leading zeros//
    @Test
    public void postParameterWithUnion2() throws Exception{
        this.mvc.perform(post("/").param("operand1","00111").param("operator","|").param("operand2","0001010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "00111")); 
    }

    //test post request with union: with different length//
    @Test
    public void postParameterWithUnion3() throws Exception{
        this.mvc.perform(post("/").param("operand1","111").param("operator","|").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
            .andExpect(model().attribute("result", "1111"))
            .andExpect(model().attribute("operand1", "111"));       
    }

            

}
