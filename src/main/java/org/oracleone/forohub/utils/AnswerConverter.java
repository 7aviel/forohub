package org.oracleone.forohub.utils;

import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.answerdto.AnswerDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverter implements EntityConverter<AnswerDTO, Answer> {

    private final UserConverter userConverter;

    @Autowired
    public AnswerConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public AnswerDTO EntityToDTO(Answer answer) {
        return new AnswerDTO(
                answer.getMessage(),
                answer.getTopic().getTitle(),
                answer.getCreationDate(),
                userConverter.EntityToDTO(answer.getAuthor()),
                answer.getSolution()
        );
    }

    @Override
    public Answer DTOtoEntity(AnswerDTO answerDTO) {
        return new Answer(
                answerDTO.message(),
                //Not implemented nor needed for now.
                null,
                answerDTO.creationDate(),
                userConverter.DTOtoEntity(answerDTO.author()),
                answerDTO.solution()
        );
    }
}
