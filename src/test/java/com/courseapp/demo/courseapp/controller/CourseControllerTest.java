package com.courseapp.demo.courseapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.courseapp.demo.courseapp.controller.assemblers.CourseAssembler;
import com.courseapp.demo.courseapp.controller.assemblers.mapper.CourseMapperImpl;
import com.courseapp.demo.courseapp.core.entites.Course;
import com.courseapp.demo.courseapp.core.repositories.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CourseController.class)
@Import({ CourseAssembler.class, CourseMapperImpl.class })
class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CourseRepository courseRepository;

	@Test
	void testFindCourses() throws Exception {
		Course course = Course.builder().id(1).name("test").build();

		when(courseRepository.findAll(any(Example.class), any(Pageable.class)))
				.thenReturn(new PageImpl<>(List.of(course), PageRequest.of(0, 1), 3));

		mockMvc.perform(MockMvcRequestBuilders.get("/course?page=0&size=10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
				.andExpect(jsonPath("$._embedded.courseDTOList[0].id").value(course.getId()))
				.andExpect(jsonPath("$._links.first.href").value("http://localhost/course?page=0&size=1"))
				.andExpect(jsonPath("$._links.self.href").value("http://localhost/course?page=0&size=1"))
				.andExpect(jsonPath("$._links.next.href").value("http://localhost/course?page=1&size=1"))
				.andExpect(jsonPath("$._links.last.href").value("http://localhost/course?page=2&size=1"));
	}

	@Test
	void testFindById() throws Exception {
		Course course = Course.builder().id(1).name("test").build();

		when(courseRepository.findById(any())).thenReturn(Optional.of(course));

		mockMvc.perform(MockMvcRequestBuilders.get("/course/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaTypes.HAL_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(course.getId()));
	}

	@Test
	void testInsert() throws Exception {
		Course course = Course.builder().id(1).name("test").build();
		when(courseRepository.save(any())).thenReturn(course);

		mockMvc.perform(MockMvcRequestBuilders.post("/course")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(course)))
				.andExpect(status().isCreated())
				.andExpect(header()
						.string("Location",
								"http://localhost/course/" + course.getId()));
	}

	@Test
	void testUpdate() throws Exception {
		Course course = Course.builder().id(1).name("test").build();
		when(courseRepository.existsById(any())).thenReturn(true);
		when(courseRepository.save(any())).thenReturn(course);

		mockMvc.perform(MockMvcRequestBuilders.put("/course")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(course)))
				.andExpect(status().isOk());
	}
}