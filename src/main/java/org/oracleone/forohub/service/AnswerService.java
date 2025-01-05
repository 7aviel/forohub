package org.oracleone.forohub.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService implements EntityConverter<AnswerDTO, Answer> {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Transactional
    public Answer saveNewAnswer(AnswerDTO answerDTO){
        Answer newAnswer = new Answer();/*
        Optional.ofNullable(answerDTO.topicDTO())
                .orElseThrow(() -> new IllegalArgumentException("Topic cannot be null. Create a new Topic in DB if it does not exist."));
        newAnswer.setTopic(this.topicService.getByTitle(answerDTO.topicDTO().title()));
        Optional.ofNullable(answerDTO.author())
                .orElseThrow(() -> new IllegalArgumentException("Author cannot be null. Create a new User in DB if it does not exist."));
        newAnswer.setAuthor(this.userService.getByNameAndEmail(answerDTO.author().name(), answerDTO.author().email()));*/
        newAnswer.setMessage(answerDTO.message());
        newAnswer.setCreationDate(answerDTO.creationDate());
        newAnswer.setSolution(answerDTO.solution());
        return this.answerRepository.save(newAnswer);
    }

    @Override
    public AnswerDTO EntityToDTO(Answer answer) {
        return null;
    }

    @Override
    public Answer DTOtoEntity(AnswerDTO answerDTO) {
        return null;
    }

    public Answer findById(Long id){
        return this.answerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No answer found"));
    }

    public List<AnswerDTO> getAllAnswers(){
        List<Answer> answers = this.answerRepository.findAll();
        if (answers.isEmpty()){
            throw new EntityNotFoundException("No answers found");
        }
        return answers.stream().map(
                this::EntityToDTO
        ).collect(Collectors.toList());
    }

    public List<Answer> getByAuthor(String author){
        return this.answerRepository.findByName(author).orElseThrow(()->null);
    }

}
