package org.oracleone.forohub.service;
import jakarta.persistence.EntityNotFoundException;
import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.repositories.TopicRepository;
import org.oracleone.forohub.utils.TopicConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService{

    private final CourseService courseService;
    private final TopicRepository topicRepository;
    private final UserService userService;
    private final TopicConverter topicConverter;

    @Autowired
    public TopicService(UserService userService, CourseService courseService, TopicRepository topicRepository, TopicConverter topicConverter) {
        this.courseService = courseService;
        this.topicRepository = topicRepository;
        this.userService = userService;
        this.topicConverter = topicConverter;
    }

    public Topic createNewTopic(TopicDTO topicDTO){
        Topic newTopic = new Topic();
        Optional.ofNullable(topicDTO.user())
                .orElseThrow(() -> new IllegalArgumentException("User CANNOT be null. Add a new User in DB if does not already exists"));
            newTopic.setUser(this.userService.getByNameAndEmail(topicDTO.user().name(),topicDTO.user().email()));
        Optional.ofNullable(topicDTO.course())
                        .orElseThrow(() -> new EntityNotFoundException("Course cannot be null. Create a new Course in DB if does not exists"));
            newTopic.setCourse(this.courseService.getByName(topicDTO.course().name()));
/*      MARKED TO DELETE
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
        newTopic.setTitle(topicDTO.title());
        newTopic.setCreationDate(topicDTO.creationDate());
        newTopic.setStatus(topicDTO.status());
        return this.topicRepository.save(newTopic);
    }

    public Topic getById(Long id){
        return this.topicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not found"));
    }

    public TopicDTO convertTopicToDTO(Topic topic){
        return this.topicConverter.EntityToDTO(topic);
    }

    public Page<TopicDTO> getAllTopics(Pageable pageable){
        Page<Topic> topics = this.topicRepository.findAll(pageable);
        if(topics.isEmpty()){
            throw new EntityNotFoundException("Not found");
        }
        return topics.map(this.topicConverter::EntityToDTO);
    }

}
