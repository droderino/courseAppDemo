package com.courseapp.demo.courseapp.core.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {

	@Id
	private Integer id;
	private String name;
	private String description;
	private String lecturer;
	private BigDecimal price;
	private LocalDateTime periodStart;
	private LocalDateTime periodEnd;

}
