package com.projectname.ProjectName.repository;

import com.projectname.ProjectName.models.Quiz;
import com.projectname.ProjectName.models.Result;
import com.projectname.ProjectName.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
@Transactional
public class ResultRepositoryTest {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Test
    public void testSaveAndFindResult() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUserName("test");
        user.setPassword("test");
        userRepository.save(user);

        Quiz quiz = new Quiz();
        quiz.setName("Test Quiz");
        quiz.setLevel("Difficult");
        quiz.setImagePath("path/to/image");
        quizRepository.save(quiz);

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(4);
        resultRepository.save(result);

        Result foundresult = resultRepository.findById(result.getId()).orElse(null);

        assertNotNull(foundresult, "Result should not be null");
        assertEquals(4, foundresult.getScore(), "Score should be 4");
        assertNotNull(foundresult.getQuiz(), "Quiz should not be null");
        assertEquals("Test Quiz",foundresult.getQuiz().getName(),"Quiz name should be 'Test Quiz");
        assertNotNull(foundresult.getScore(), "Score should not be null");
        assertNotNull(foundresult.getUser(), "User should not be null");
        assertEquals("test@gmail.com", foundresult.getUser().getEmail(), "User email should match");

    }

    @ParameterizedTest
    @ValueSource(strings = {"test@gmail.com", "other@gmail.com"})
    public void testFindResultsByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        user.setUserName("test");
        user.setPassword("test");
        userRepository.save(user);

        Quiz quiz = new Quiz();
        quiz.setName("Test Quiz");
        quiz.setLevel("Difficult");
        quiz.setImagePath("path/to/image");
        quizRepository.save(quiz);

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(4);
        resultRepository.save(result);

        Iterable<Result> results = resultRepository.findResultsByUserEmail(email);

        assertNotNull(results, "Result should not be null");
        results.forEach(r -> assertEquals(email,r.getUser().getEmail(),  "User email should match"));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void testFindResultsByQuizId(int quizId) {

        Quiz quiz = new Quiz();
        quiz.setName("Test Quiz");
        quiz.setLevel("Difficult");
        quiz.setImagePath("path/to/image");
        quizRepository.save(quiz);

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setUserName("test");
        user.setPassword("test");
        userRepository.save(user);

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(4);
        resultRepository.save(result);

        Iterable<Result> results = resultRepository.findResultsByQuizId(quizId);

        assertNotNull(results, "Result should not be null");
        results.forEach(r -> assertEquals(quizId,r.getQuiz().getId(), "Quiz id should match"));
    }
}
