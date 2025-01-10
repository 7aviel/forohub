package org.oracleone.forohub.controller;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.CourseDTO;
import org.oracleone.forohub.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> saveNewCourse(@RequestBody @Valid CourseDTO courseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.saveCourse(courseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.courseService.convertToDTO(
                        this.courseService.getCourseById(id)
                )
        );
    }

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> getCourses(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
            this.courseService.getAllCourses(pageable)
        );
    }
}
