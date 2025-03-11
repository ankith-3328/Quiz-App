package com.ankith.Quizapp.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String questionText;
    private String option_A;
    private String option_B;
    private String option_C;
    private String option_D;

    private char correctOption;
    private String category;
    private String difficultyLevel;
}
