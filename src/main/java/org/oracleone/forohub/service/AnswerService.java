package org.oracleone.forohub.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.AnswerDTO;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.RegisterAnswerDTO;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.UpdateAnswerDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.entities.User;
import org.oracleone.forohub.persistence.repositories.AnswerRepository;
import org.oracleone.forohub.utils.AnswerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerConverter answerConverter;
    private final TopicHelperService topicService;

    private final UserService userService;

    @Autowired
    public AnswerService(AnswerRepository answerRepository, AnswerConverter answerConverter, TopicHelperService topicService, UserService userService) {
        this.answerRepository = answerRepository;
        this.answerConverter = answerConverter;
        this.topicService = topicService;
        this.userService = userService;
    }

    @Transactional
    public Answer saveNewAnswer(RegisterAnswerDTO registerAnswerDTO,
                                HttpSession session){
        Answer newAnswer = new Answer();
        Optional.ofNullable(registerAnswerDTO.topicId())
                .orElseThrow(() -> new IllegalArgumentException("Topic cannot be null. Create a new Topic in DB if it does not exist."));
        newAnswer.setTopic(this.topicService.getById(registerAnswerDTO.topicId()));
        newAnswer.setMessage(registerAnswerDTO.message());
        LocalDate sessionDate = (LocalDate) session.getAttribute("sessionDate");
        if (sessionDate == null) {
            throw new IllegalStateException("Session date not found");
        }
        newAnswer.setCreationDate(sessionDate);
        newAnswer.setSolution(registerAnswerDTO.solution());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = this.userService.findByEmail(userDetails.getUsername());
        newAnswer.setAuthor(author);
        return this.answerRepository.save(newAnswer);
    }

    public Answer findById(Long id){
        return this.answerRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No answer found"));
    }

    public Page<AnswerDTO> getAllAnswers(Pageable pageable){
        Page<Answer> answers = this.answerRepository.findAll(pageable);
        if (answers.isEmpty()){
            throw new EntityNotFoundException("No answers found");
        }
        return answers.map(
                this.answerConverter::EntityToDTO
        );
    }

    public AnswerDTO convertToDTO(Answer answer){
        return this.answerConverter.EntityToDTO(answer);
    }

    @Transactional
    public void deleteAnswer(Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Answer not found"));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUser = userService.findByEmail(userDetails.getUsername());

        if (!answer.getAuthor().equals(authenticatedUser)) {
            throw new AccessDeniedException("You are not authorized to delete this answer");
        }else{
            answerRepository.delete(answer);
        }
    }

    @Transactional
    public AnswerDTO updateAnswer(@Valid UpdateAnswerDTO updateAnswerDTO, Long id) {
        Answer answer = this.findById(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User authenticatedUser = userService.findByEmail(userDetails.getUsername());

        if (!answer.getAuthor().equals(authenticatedUser)) {
            throw new AccessDeniedException("You are not authorized to update this answer");
        }else {
            Optional.ofNullable(updateAnswerDTO.message()).ifPresent(answer::setMessage);
            Optional.ofNullable(updateAnswerDTO.solution()).ifPresent(answer::setSolution);
        }
        return this.answerConverter.EntityToDTO(this.answerRepository.save(answer));
    }
}
