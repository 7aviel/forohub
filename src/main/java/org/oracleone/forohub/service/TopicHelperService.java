package org.oracleone.forohub.service;

import jakarta.persistence.EntityNotFoundException;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicHelperService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicHelperService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic getByTitle(String title){
        return this.topicRepository.findTopicByTitle(title)
                .orElseThrow(()->new EntityNotFoundException("Topic not found"));
    }

    public Topic getById(Long id){
        return this.topicRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No topic found"));
    }
}
