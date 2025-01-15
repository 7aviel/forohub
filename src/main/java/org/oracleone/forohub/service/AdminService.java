package org.oracleone.forohub.service;

import jakarta.persistence.EntityNotFoundException;
import org.oracleone.forohub.enums.Role;
import org.oracleone.forohub.persistence.DTO.UpdateCourseDTO;
import org.oracleone.forohub.persistence.DTO.UserRoleDTO;
import org.oracleone.forohub.persistence.entities.Course;
import org.oracleone.forohub.persistence.entities.User;
import org.oracleone.forohub.persistence.repositories.CourseRepository;
import org.oracleone.forohub.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        Optional.ofNullable(updateCourseDTO.name()).ifPresent(course::setName);
        Optional.ofNullable(updateCourseDTO.category()).ifPresent(course::setCategory);
        courseRepository.save(course);
    }

    @Transactional
    public void updateUserRole(Long id, UserRoleDTO userRoleDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Optional.of(userRoleDTO.roles().stream()
                .map(role -> Role.valueOf(role.name())) // Assuming Rol has a constructor that accepts a String
                .collect(Collectors.toSet())).ifPresent(user::setRoles);
        userRepository.save(user);
    }
}