package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

   //success test for operators//
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));
    }

    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1","111").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("1000110"));
    }


    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("10"));
    }

    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1","111").param("operand2","1010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("1111"));
    }

    //fail cases//

    //non-binary input should return 0//
    @Test
    public void nonBinaryValue() throws Exception {
        this.mvc.perform(get("/and").param("operand1","567").param("operand2","234"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }   

    //non number input should return 0//
    @Test
    public void nonNumericValue() throws Exception {
        this.mvc.perform(get("/and").param("operand1","abc").param("operand2","1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    //missing 1 operand//
    @Test
    public void missingOperand() throws Exception {
        this.mvc.perform(get("/and").param("operand1","1011").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

    //no operand//
    @Test
    public void noOperand() throws Exception {
        this.mvc.perform(get("/and").param("operand1","").param("operand2",""))
            .andExpect(status().isOk())
            .andExpect(content().string("0")); 
    }

    //invalid operator//
    @Test
    public void invalidOperator() throws Exception {
        this.mvc.perform(get("/andd").param("operand1","1011").param("operand2",""))
            .andExpect(status().isNotFound());
    }
}
