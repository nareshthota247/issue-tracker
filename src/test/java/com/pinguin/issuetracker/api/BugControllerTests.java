package com.pinguin.issuetracker.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinguin.issuetracker.dto.BugDto;

@SpringBootTest
@AutoConfigureMockMvc
class BugControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void bugCreate() throws Exception {
		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/bug").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new BugDto("bugTitle", "bugDesc", Integer.valueOf(1), "CRITICAL", "NEW", "BUG"))))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void bugUpdate() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/bug").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new BugDto("bugTitle", "bugDesc", Integer.valueOf(1), "CRITICAL", "NEW", "BUG"))))
				.andExpect(status().isOk()).andReturn();

		mockMvc.perform(put("/api/bug").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(
				new BugDto(Integer.valueOf(2), "bugTitle", "bugDesc", Integer.valueOf(1), "CRITICAL", "NEW", "BUG"))))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void bugRetrive() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/bug").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new BugDto("bugTitle", "bugDesc", Integer.valueOf(1), "CRITICAL", "NEW", "BUG"))))
				.andExpect(status().isOk()).andReturn();

		mockMvc.perform(get("/api/bug/2")).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void bugDelete() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/bug").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new BugDto("bugTitle", "bugDesc", Integer.valueOf(1), "CRITICAL", "NEW", "BUG"))))
				.andExpect(status().isOk()).andReturn();
		
		mockMvc.perform(delete("/api/bug/2")).andExpect(status().isOk()).andReturn();
	}

}
