package com.ankith.Quizapp.Service;

import com.ankith.Quizapp.Model.Question;
import com.ankith.Quizapp.Model.QuestionWrapper;
import com.ankith.Quizapp.Model.Quiz;
import com.ankith.Quizapp.Model.Response;
import com.ankith.Quizapp.dao.QuestionDao;
import com.ankith.Quizapp.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();

        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionText(), q.getOption_A(), q.getOption_B(), q.getOption_C(), q.getOption_D());
            questionsForUsers.add(qw);
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int right = 0;

        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getCorrectOption())){
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
