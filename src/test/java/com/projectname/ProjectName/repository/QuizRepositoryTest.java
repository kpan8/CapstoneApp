package com.projectname.ProjectName.repository;

import com.projectname.ProjectName.models.Quiz;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
public class QuizRepositoryTest {

    @Autowired
    private QuizRepository quizRepository;

    @Test
    public void testFindQuizByName() {

        Quiz quiz = new Quiz();
        quiz.setName("Easy Food Quiz 2");
        quiz.setLevel("Easy");
        quiz.setImagePath("path/to/food-image2");

        // Save the quiz
        quizRepository.save(quiz);

        //Use findQuizByName method
        Quiz foundQuiz = quizRepository.findQuizByName("Easy Food Quiz 2");

        // Was it retrieved correctly?
        assertNotNull(foundQuiz, "Quiz should not be null");
        assertEquals("Easy Food Quiz 2", foundQuiz.getName(), "Quiz name should match");
        assertEquals("Easy", foundQuiz.getLevel(), "Quiz level should match");
        assertEquals("path/to/food-image2", foundQuiz.getImagePath(), "Quiz image path should match");
    }
}
