package com.ankith.Quizapp.Model;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionWrapper {

    private Integer id;

    private String questionText;
    private String option_A;
    private String option_B;
    private String option_C;
    private String option_D;

}
