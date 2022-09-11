package com.courseapp.demo.courseapp.controller.dto;

import com.courseapp.demo.courseapp.core.entites.Course;

import lombok.Data;

@Data
public class BookingDTO {

	private Integer id;
	private String customer;
	private Course course;
}
