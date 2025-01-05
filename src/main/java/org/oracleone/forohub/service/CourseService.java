package org.oracleone.forohub.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.CourseDTO;
import org.oracleone.forohub.persistence.entities.Course;
import org.oracleone.forohub.persistence.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements EntityConverter<CourseDTO, Course> {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course saveCourse(CourseDTO courseDTO){
        return this.courseRepository.save(DTOtoEntity(courseDTO));
    }

    public Course getCourseById(Long id){
        return this.courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
    }



    public List<CourseDTO> getAllCourses(){
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            throw new EntityNotFoundException("No courses found");
        }
        return courses.stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO EntityToDTO(Course course) {
        return new CourseDTO(course.getName(), course.getCategoryEnum());
    }

    @Override
    public Course DTOtoEntity(CourseDTO courseDTO) {
        return new Course(
                courseDTO.name(),
                courseDTO.category()
        );
    }

    public Course getByName(String name){
        return this.courseRepository.findByName(name).orElseThrow(()->null);
    }

}
