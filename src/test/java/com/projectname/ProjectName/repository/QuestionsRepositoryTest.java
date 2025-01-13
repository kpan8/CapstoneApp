package com.projectname.ProjectName.repository;

import com.projectname.ProjectName.models.Questions;
import com.projectname.ProjectName.models.Quiz;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
public class QuestionsRepositoryTest {

    @Mock
    private QuestionsRepository questionsRepository;

    @Mock
    private Quiz quiz;

    private Questions question1;
    private Questions question2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // We will test it with this data (Questions)
        question1 = new Questions();
        question1.setId(1);
        question1.setImagePath("path/to/image1");
        question1.setQuiz(quiz);

        question2 = new Questions();
        question2.setId(2);
        question2.setImagePath("path/to/image2");
        question2.setQuiz(quiz);

        when(quiz.getId()).thenReturn(101);
    }

    @Test
    void testFindQuestionsByQuizId() {

        when(questionsRepository.findQuestionsByQuizId(101)).thenReturn(Arrays.asList(question1, question2));


        List<Questions> questions = questionsRepository.findQuestionsByQuizId(101);

        assertEquals(2, questions.size(), "There should be two questions for quizId 101");
    }

    @Test
    void testFindQuestionsByQuizIdEmpty() {
        when(questionsRepository.findQuestionsByQuizId(888)).thenReturn(Arrays.asList());

        List<Questions> questions = questionsRepository.findQuestionsByQuizId(888);

        assertEquals(0, questions.size(), "There should be no questions for quizId 888");
    }
}
