package org.oracleone.forohub.service;

import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public void saveTopic(TopicDTO topicDTO){
        this.topicRepository.save(new Topic(topicDTO));
    }

}
