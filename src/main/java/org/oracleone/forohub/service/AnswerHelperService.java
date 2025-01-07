package org.oracleone.forohub.service;

import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerHelperService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerHelperService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    public List<Answer> getByAuthor(String author){
        return this.answerRepository.findByName(author).orElseThrow(()->null);
    }
}
