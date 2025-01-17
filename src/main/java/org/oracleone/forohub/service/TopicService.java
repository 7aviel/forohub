package org.oracleone.forohub.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.oracleone.forohub.persistence.DTO.TopicDTOs.RegisterTopicDTO;
import org.oracleone.forohub.persistence.DTO.TopicDTOs.TopicDTO;
import org.oracleone.forohub.persistence.DTO.UpdateTopicDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.entities.User;
import org.oracleone.forohub.persistence.repositories.TopicRepository;
import org.oracleone.forohub.utils.AnswerConverter;
import org.oracleone.forohub.utils.TopicConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService{

    private final CourseService courseService;
    private final TopicRepository topicRepository;
    private final UserService userService;
    private final TopicConverter topicConverter;
    private final AnswerConverter answerConverter;

    @Autowired
    public TopicService(UserService userService, CourseService courseService, TopicRepository topicRepository, TopicConverter topicConverter, AnswerConverter answerConverter) {
        this.courseService = courseService;
        this.topicRepository = topicRepository;
        this.userService = userService;
        this.topicConverter = topicConverter;
        this.answerConverter = answerConverter;
    }

    @Transactional
    public Topic createNewTopic(RegisterTopicDTO registerTopicDTO, HttpSession session){
        Topic newTopic = new Topic();
        Optional.ofNullable(registerTopicDTO.course())
                        .orElseThrow(() -> new EntityNotFoundException("Course cannot be null. Create a new Course in DB if does not exists"));
            newTopic.setCourse(this.courseService.getByName(registerTopicDTO.course().name()));
        newTopic.setTitle(registerTopicDTO.title());
        LocalDate sessionDate = (LocalDate) session.getAttribute("sessionDate");
        if (sessionDate == null) {
            throw new IllegalStateException("Session date not found");
        }
        newTopic.setCreationDate(sessionDate);
        newTopic.setStatus(registerTopicDTO.status());
        // Get the authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = this.userService.findByEmail(userDetails.getUsername());
        newTopic.setUser(author);
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

    @Transactional
    public void deleteTopic(Long id){
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUser = userService.findByEmail(userDetails.getUsername());

        if (!topic.getUser().equals(authenticatedUser)) {
            throw new AccessDeniedException("You are not authorized to delete this topic");
        }else {
            this.topicRepository.deleteById(id);
        }

    }

    @Transactional
    public Topic updateTopic(UpdateTopicDTO updateTopicDTO, Long id){

        Topic updateTopic = this.topicRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUser = userService.findByEmail(userDetails.getUsername());

        if (!updateTopic.getUser().equals(authenticatedUser)) {
            throw new AccessDeniedException("You are not authorized to update this topic");
        }else {
            Optional.ofNullable(updateTopicDTO.title())
                    .ifPresent(updateTopic::setTitle);
            Optional.ofNullable(updateTopicDTO.status())
                    .ifPresent(updateTopic::setStatus);
            Optional.ofNullable(updateTopicDTO.answers())
                    .filter(answerDTO -> !answerDTO.isEmpty())
                    .ifPresent(answers -> {
                        List<Answer> answerList = answers.stream()
                                .map(this.answerConverter::DTOtoEntity)
                                .toList();
                        updateTopic.setAnswers(answerList);
                    });
            this.topicRepository.save(updateTopic);
            return updateTopic;
        }
    }

}
