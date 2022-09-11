package com.courseapp.demo.courseapp.core.repositories;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.courseapp.demo.courseapp.core.entites.Course;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, Integer> {

	Page<Course> findAll(Example<Course> example, Pageable pageable);
}
