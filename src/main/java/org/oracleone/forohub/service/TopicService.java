package org.oracleone.forohub.service;

import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;
import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService implements EntityConverter<TopicDTO,Topic> {

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public TopicService(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }
/*


    public void saveTopic(TopicDTO topicDTO){
        this.topicRepository.save(new Topic(topicDTO));
    }*/

    @Override
    public TopicDTO EntityToDTO(Topic topic) {
        return new TopicDTO(topic.getTitle(),
                topic.getCreationDate(),
                topic.getStatus(),
                userService.EntityToDTO(topic.getUser()),
                courseService.EntityToDTO(topic.getCourse()),
                convertToAnswerListDTO(topic.getAnswers()));
    }

    @Override
    public Topic DTOtoEntity(TopicDTO topicDTO) {
        return new Topic(
                topicDTO.title(),
                topicDTO.creationDate(),
                topicDTO.status(),
                userService.DTOtoEntity(topicDTO.user()),
                courseService.DTOtoEntity(topicDTO.course()),
                convertToAnswerList(topicDTO.answers())
        );
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

}
