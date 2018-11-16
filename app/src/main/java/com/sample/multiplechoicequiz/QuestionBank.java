package com.sample.multiplechoicequiz;

// This file contains questions from QuestionBank

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    List<Question> list = new ArrayList<>();
    MyDatabaseHelper db;

    // method returns number of questions
    public int getLength(){
        return list.size();
    }

    // method returns question from array textQuestions[] based on array index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on array index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4 as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getOption(num-1);
    }

    //  method returns correct answer for the question based on array index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }


    public void initAllQuestions(Context context) {
        db = new MyDatabaseHelper(context);
        list = db.getQuestions();

        if (list.isEmpty()) {
            db.addQuestionDetails(new Question("1. When did Google acquire Android ?",
                    new String[]{"2001", "2003", "2004", "2005"}, "2005"));
            db.addQuestionDetails(new Question("2. What is the name of build toolkit for Android Studio?",
                    new String[]{"JVM", "Gradle", "Dalvik", "HAXM"}, "Gradle"));
            db.addQuestionDetails(new Question("3. What widget can replace any use of radio buttons?",
                    new String[]{"Toggle Button", "Spinner", "Switch Button", "ImageButton"}, "Spinner"));
            db.addQuestionDetails(new Question("4. What is the most recent Android OS?",
                    new String[]{"Oreo", "Nougat", "Marshmallow", "Octopus"}, "Oreo"));

            list = db.getQuestions(); //get list from db
        }
    }
}