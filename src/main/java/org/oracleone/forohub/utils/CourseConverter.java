package org.oracleone.forohub.utils;

import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.CourseDTO;
import org.oracleone.forohub.persistence.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter implements EntityConverter<CourseDTO, Course> {



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

}
