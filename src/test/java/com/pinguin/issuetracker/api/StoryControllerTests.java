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
import com.pinguin.issuetracker.dto.StoryDto;

@SpringBootTest
@AutoConfigureMockMvc
class StoryControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void storyCreate() throws Exception {
		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/story").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new StoryDto("storyTitle", "storyDesc", Integer.valueOf(1), "ESTIMATED", Integer.valueOf(5),"STORY"))))
				.andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void storyUpdate() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/story").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new StoryDto("storyTitle", "storyDesc", Integer.valueOf(1), "ESTIMATED", Integer.valueOf(5),"STORY"))))
				.andExpect(status().isOk()).andReturn();

		mockMvc.perform(put("/api/story").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(
				new StoryDto(Integer.valueOf(2),"storyTitle", "storyDesc", Integer.valueOf(1), "ESTIMATED", Integer.valueOf(5),"STORY"))))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void storyRetrive() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/story").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new StoryDto("storyTitle", "storyDesc", Integer.valueOf(1), "ESTIMATED", Integer.valueOf(5),"STORY"))))
				.andExpect(status().isOk()).andReturn();

		mockMvc.perform(get("/api/story/2")).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void storyDelete() throws Exception {

		mockMvc.perform(post("/api/developer/dev1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.developerName").value("dev1")).andReturn();

		mockMvc.perform(post("/api/story").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(
						new StoryDto("storyTitle", "storyDesc", Integer.valueOf(1), "ESTIMATED", Integer.valueOf(5),"STORY"))))
				.andExpect(status().isOk()).andReturn();
		
		mockMvc.perform(delete("/api/story/2")).andExpect(status().isOk()).andReturn();
	}

}

