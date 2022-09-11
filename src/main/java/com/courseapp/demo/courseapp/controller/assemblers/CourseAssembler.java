package com.courseapp.demo.courseapp.controller.assemblers;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.courseapp.demo.courseapp.controller.assemblers.mapper.CourseMapper;
import com.courseapp.demo.courseapp.controller.dto.CourseDTO;
import com.courseapp.demo.courseapp.core.entites.Course;

@Component
public class CourseAssembler implements RepresentationModelAssembler<Course, CourseDTO> {

	private final CourseMapper courseMapper;

	public CourseAssembler(CourseMapper courseMapper) {
		this.courseMapper = courseMapper;
	}

	@Override
	public CourseDTO toModel(Course entity) {
		return courseMapper.toCourseDTO(entity);
	}
}
