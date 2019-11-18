package com.pinguin.issuetracker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinguin.issuetracker.dto.DeveloperDto;

@SpringBootTest
@AutoConfigureMockMvc
class IssueTrackerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void developerCreate() throws Exception {
		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();
	}

	@Test
	public void developerUpdate() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(put("/api/developer").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(new DeveloperDto(1, "dev2")))).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev2")).andReturn();
	}
	
	@Test
	public void developerRetrive() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(get("/api/developer/1"))
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();
	}
	
	@Test
	public void developerDelete() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(delete("/api/developer/1"))
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();
	}

}
