package org.oracleone.forohub.utils;

import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.TopicDTOs.TopicDTO;
import org.oracleone.forohub.persistence.entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TopicConverter implements EntityConverter<TopicDTO, Topic> {

    private final UserConverter userConverter;
    private final CourseConverter courseConverter;
    private final AnswerConverter answerConverter;

    @Autowired
    public TopicConverter(UserConverter userConverter, CourseConverter courseConverter, AnswerConverter answerConverter) {
        this.userConverter = userConverter;
        this.courseConverter = courseConverter;
        this.answerConverter = answerConverter;
    }

    @Override
    public Topic DTOtoEntity(TopicDTO topicDTO) {
        return new Topic(
                topicDTO.title(),
                topicDTO.creationDate(),
                topicDTO.status(),
                userConverter.DTOtoEntity(topicDTO.user()),
                courseConverter.DTOtoEntity(topicDTO.course()),
                topicDTO.answers().stream()
                        .map(answerConverter::DTOtoEntity)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public TopicDTO EntityToDTO(Topic topic) {
        return new TopicDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getCreationDate(),
                topic.getStatus(),
                userConverter.EntityToDTO(topic.getUser()),
                courseConverter.EntityToDTO(topic.getCourse()),
                topic.getAnswers().stream()
                        .map(this.answerConverter::EntityToDTO)
                        .collect(Collectors.toList())
        );
    }
}
