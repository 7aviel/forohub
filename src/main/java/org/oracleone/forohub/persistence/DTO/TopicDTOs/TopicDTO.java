package org.oracleone.forohub.persistence.DTO.TopicDTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Status;
import org.oracleone.forohub.persistence.DTO.CourseDTO;
import org.oracleone.forohub.persistence.DTO.UserDTO;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.AnswerDTO;

import java.time.LocalDate;
import java.util.List;

public record TopicDTO(
        Long id,
        @NotBlank
        String title,
        @NotNull
        LocalDate creationDate,
        @NotNull
        Status status,
        @NotNull
        @Valid
        UserDTO user,
        @NotNull
        @Valid
        CourseDTO course,
        List<AnswerDTO> answers
) {

}
