package org.oracleone.forohub.service;

import jakarta.persistence.EntityNotFoundException;
import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;
import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService implements EntityConverter<TopicDTO,Topic> {

    private final UserService userService;
    private final CourseService courseService;
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(UserService userService, CourseService courseService, AnswerService answerService, UserService userService1, CourseService courseService1, TopicRepository topicRepository) {
        this.userService = userService1;
        this.courseService = courseService1;
        this.topicRepository = topicRepository;
    }

    public Topic createNewTopic(TopicDTO topicDTO){
        Topic newTopic = new Topic();
        Optional.ofNullable(topicDTO.user())
                .orElseThrow(() -> new IllegalArgumentException("User CANNOT be null. Add a new User in DB if does not already exists"));
            newTopic.setUser(this.userService.getByNameAndEmail(topicDTO.user().name(),topicDTO.user().email()));
        Optional.ofNullable(topicDTO.course())
                        .orElseThrow(() -> new EntityNotFoundException("Course cannot be null. Create a new Course in DB if does not exists"));
            newTopic.setCourse(this.courseService.getByName(topicDTO.course().name()));
/*
        Optional.ofNullable(topicDTO.answers())
                .filter(answers -> !answers.isEmpty())
                .ifPresentOrElse( answers -> {
                    List<Answer> answerList = answers
                            .stream()
                            .flatMap(answerDTO -> this.answerService
                                    .getByAuthor(answerDTO.author().name()).stream())
                            .collect(Collectors.toList());
                    newTopic.setAnswers(answerList);
                    },
                        () -> newTopic.setAnswers(null) );*/
        newTopic.setAnswers(null);
        newTopic.setTitle(topicDTO.title().toLowerCase());
        newTopic.setCreationDate(topicDTO.creationDate());
        newTopic.setStatus(topicDTO.status());
        return this.topicRepository.save(newTopic);
    }

    @Override
    public TopicDTO EntityToDTO(Topic topic) {
        return null;
    }

    @Override
    public Topic DTOtoEntity(TopicDTO topicDTO) {
        return null;

    }

    public List<AnswerDTO> convertToAnswerListDTO(List<Answer> answer) {
        return answer.stream().map(a -> new AnswerDTO(a.getMessage(),EntityToDTO(a.getTopic()),
                a.getCreationDate(),userService.EntityToDTO(a.getAuthor()),a.getSolution()))
                .collect(Collectors.toList());
    }

    public List<Answer> convertToAnswerList(List<AnswerDTO> answerDTO) {
        return answerDTO.stream().map(a -> new Answer(a.message(),DTOtoEntity(a.topicDTO()),
                a.creationDate(),userService.DTOtoEntity(a.author()),a.solution()))
                .collect(Collectors.toList());
    }

    public Topic getByTitle(String title){
        return this.topicRepository.getTopicByTitle(title)
                .orElseThrow(()->new EntityNotFoundException("Topic not found"));
    }

    public Topic getById(Long id){
        return this.topicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not found"));
    }

}
