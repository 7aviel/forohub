package org.oracleone.forohub.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.repositories.AnswerRepository;
import org.oracleone.forohub.utils.AnswerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Answer saveNewAnswer(AnswerDTO answerDTO){
        Answer newAnswer = new Answer();
        Optional.ofNullable(answerDTO.topic())
                .orElseThrow(() -> new IllegalArgumentException("Topic cannot be null. Create a new Topic in DB if it does not exist."));
        newAnswer.setTopic(this.topicService.getByTitle(answerDTO.topic().title()));
        Optional.ofNullable(answerDTO.author())
                .orElseThrow(() -> new IllegalArgumentException("Author cannot be null. Create a new User in DB if it does not exist."));
        newAnswer.setAuthor(this.userService.getByNameAndEmail(answerDTO.author().name(), answerDTO.author().email()));
        newAnswer.setMessage(answerDTO.message());
        newAnswer.setCreationDate(answerDTO.creationDate());
        newAnswer.setSolution(answerDTO.solution());
        return this.answerRepository.save(newAnswer);
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
                this.answerConverter::EntityToDTO
        ).collect(Collectors.toList());
    }

    public AnswerDTO convertToDTO(Answer answer){
        return this.answerConverter.EntityToDTO(answer);
    }

}
