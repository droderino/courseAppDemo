package com.courseapp.demo.courseapp.controller;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.time.LocalDateTime;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import com.courseapp.demo.courseapp.controller.assemblers.CourseAssembler;
import com.courseapp.demo.courseapp.controller.assemblers.mapper.CourseMapper;
import com.courseapp.demo.courseapp.controller.dto.CourseDTO;
import com.courseapp.demo.courseapp.core.entites.Course;
import com.courseapp.demo.courseapp.core.repositories.CourseRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/course")
public class CourseController {

	private final CourseRepository courseRepository;
	private final PagedResourcesAssembler<Course> pagedResourcesAssembler;
	private final CourseAssembler courseAssembler;
	private final CourseMapper courseMapper;

	public CourseController(CourseRepository courseRepository,
			PagedResourcesAssembler<Course> pagedResourcesAssembler,
			CourseAssembler courseAssembler, CourseMapper courseMapper) {
		this.courseRepository = courseRepository;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
		this.courseAssembler = courseAssembler;
		this.courseMapper = courseMapper;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
	@Operation(summary = "Übersicht über alle angebotenen Schulungen und anzeigen von Schulung für einen bestimmten Zeitraum")
	public ResponseEntity<PagedModel<CourseDTO>> findCources(
			@RequestParam(value = "von", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime periodStart,
			@RequestParam(value = "bis", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime periodEnd,
			@PageableDefault(size = 20, sort = "id") Pageable pageable) {
		Example<Course> example = Example.of(Course.builder().periodStart(periodStart).periodEnd(periodEnd).build());
		Page<Course> coursePage = courseRepository.findAll(example, pageable);

		PagedModel<CourseDTO> pagedModel = pagedResourcesAssembler.toModel(coursePage, courseAssembler);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(pagedModel);
	}

	@PostMapping
	@Operation(summary = "Hinzufügen einer neuen Schulung")
	public ResponseEntity<CourseDTO> insert(@RequestBody Course course) {
		Course savedCourse = courseRepository.save(course);

		UriComponents uri = fromMethodCall(on(CourseController.class).findById(savedCourse.getId())).build();
		return ResponseEntity
				.created(uri.toUri())
				.body(courseAssembler.toModel(savedCourse));
	}

	@PutMapping
	@Operation(summary = "Updaten einer existierenden Schulung")
	public ResponseEntity<CourseDTO> update(@RequestBody Course course) {
		if (courseRepository.existsById(course.getId())) {
			Course savedCourse = courseRepository.save(course);
			return ResponseEntity
					.ok(courseAssembler.toModel(savedCourse));
		}

		return ResponseEntity
				.notFound()
				.build();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Abfragen einer Schulung")
	public ResponseEntity<CourseDTO> findById(
			@Parameter(description = "Course ID") @PathVariable(value = "id") Integer id) {
		return courseRepository.findById(id)
				.map(u -> courseAssembler.toModel(u))
				.map(u -> ResponseEntity.ok(u))
				.orElse(ResponseEntity.noContent().build());
	}
}
