package org.oracleone.forohub.service;

import jakarta.transaction.Transactional;
import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService implements EntityConverter<AnswerDTO, Answer> {

    private final AnswerRepository answerRepository;
    private final TopicService topicService;
    private final UserService userService;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, TopicService topicService, UserService userService) {
        this.answerRepository = answerRepository;
        this.topicService = topicService;
        this.userService = userService;
    }

    @Transactional
    public Answer saveNewAnswer(AnswerDTO answerDTO){
        return this.answerRepository.save(DTOtoEntity(answerDTO));
    }

    @Override
    public AnswerDTO EntityToDTO(Answer answer) {
        return new AnswerDTO(
                answer.getMessage(),
                topicService.EntityToDTO(answer.getTopic()),
                answer.getCreationDate(),
                userService.EntityToDTO(answer.getAuthor()),
                answer.getSolution()
        );
    }

    @Override
    public Answer DTOtoEntity(AnswerDTO answerDTO) {
        return new Answer(answerDTO.message(),
                topicService.DTOtoEntity(answerDTO.topicDTO()),
                answerDTO.creationDate(),
                userService.DTOtoEntity(answerDTO.author()),
                answerDTO.solution());
    }
}
