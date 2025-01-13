package com.projectname.ProjectName.repository;
import com.projectname.ProjectName.models.Answers;
import com.projectname.ProjectName.models.Questions;
import com.projectname.ProjectName.models.Quiz;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class AnswersRepositoryTest {
    @Autowired
    private AnswersRepository answersRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired private QuestionsRepository questionsRepository;

    @Test
    public void testSaveAndFindAnswerById() {
        Quiz quiz = new Quiz();
        quiz.setName("Medium Test Quiz");
        quiz.setLevel("Medium");
        quiz.setImagePath("path/to/med-image");
        quiz = quizRepository.save(quiz);

        Questions question = new Questions();
        question.setImagePath("path/to/question-image");
        question = questionsRepository.save(question);


        Answers answer = new Answers();
        answer.setAnswer("EuCorrect");
        answer.setCorrect(true);
        answer.setLevel("Medium");
        answer.setQuiz(quiz);
        answer.setQuestion(question);

        Answers savedAnswer = answersRepository.save(answer);

        Answers foundAnswer = answersRepository.findAnswersById(savedAnswer.getId());

        assertNotNull(foundAnswer, "Answer should not be null");
        assertEquals("EuCorrect", foundAnswer.getAnswer(), "Answer should be 'EuCorrect'");
        assertTrue(foundAnswer.isCorrect(), "Answer should be correct");
        assertEquals("Medium", foundAnswer.getLevel(), "Answer level should be 'Medium'");


        assertNotNull(foundAnswer.getQuestion(), "Associated question should not be null");
        assertEquals("path/to/question-image", foundAnswer.getQuestion().getImagePath(), "Question image path should match");
    }

}
