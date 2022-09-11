package com.courseapp.demo.courseapp.controller;

import java.awt.print.Pageable;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.courseapp.demo.courseapp.controller.dto.BookingDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class BookingController {

	@RequestMapping(path = "/course/{id}/booking", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
	@Operation(summary = "Übersicht aller Buchungen für eine Schulung")
	public ResponseEntity<PagedModel<BookingDTO>> findCourses(
			@ParameterObject @PageableDefault(size = 10, sort = "id") Pageable pageable,
			@Parameter(description = "Course ID") @PathVariable(value = "courseId") Integer id) {
		//TODO implement find bookings by course id
		return ResponseEntity
				.status(HttpStatus.OK)
				.build();
	}

	@PostMapping(path = "/course/{id}/booking")
	@Operation(summary = "Buchen einer bestimmten Schulung")
	public ResponseEntity<BookingDTO> insert(
			@Parameter(description = "Course ID") @PathVariable(value = "courseId") Integer id,
			@RequestBody(description = "Details der neuen Buchung") BookingDTO bookingDTO) {
		// TODO implement save booking
		return ResponseEntity
				.created(null)
				.build();
	}
}
