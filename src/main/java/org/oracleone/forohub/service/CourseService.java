package org.oracleone.forohub.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.CourseDTO;
import org.oracleone.forohub.persistence.entities.Course;
import org.oracleone.forohub.persistence.repositories.CourseRepository;
import org.oracleone.forohub.utils.CourseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseConverter courseConverter;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseConverter courseConverter) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
    }

    @Transactional
    public Course saveCourse(CourseDTO courseDTO){
        return this.courseRepository.save(courseConverter.DTOtoEntity(courseDTO));
    }

    public Course getCourseById(Long id){
        return this.courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }



    public Page<CourseDTO> getAllCourses(Pageable pageable){
        Page<Course> courses = courseRepository.findAll(pageable);
        if (courses.isEmpty()) {
            throw new EntityNotFoundException("No courses found");
        }
        return courses
                .map(this.courseConverter::EntityToDTO);
    }

    public CourseDTO convertToDTO(Course course){
        return this.courseConverter.EntityToDTO(course);
    }


    public Course getByName(String name){
        return this.courseRepository.findByName(name).orElseThrow(()->null);
    }

    @Transactional
    public void deleteCourse(Long id) {
        this.courseRepository.deleteById(id);
    }

    @Transactional
    public CourseDTO updateCourse(@Valid CourseDTO courseDTO, Long id) {
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        return this.courseConverter.EntityToDTO(this.courseRepository.save(course));
    }
}
