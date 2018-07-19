package com.nwidart.techvideo.integration.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
@Ignore
public class MockVideoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testItReturnsAListOfVideos() throws Exception {
    this.mockMvc.perform(get("/api/v1/videos"))
        .andExpect(status().isOk());
  }
}
