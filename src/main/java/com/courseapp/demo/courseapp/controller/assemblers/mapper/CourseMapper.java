package com.courseapp.demo.courseapp.controller.assemblers.mapper;

import org.mapstruct.Mapper;
import com.courseapp.demo.courseapp.controller.dto.CourseDTO;
import com.courseapp.demo.courseapp.core.entites.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseDTO toCourseDTO(Course course);

	Course toCourse(CourseDTO courseDTO);
}
