package com.sample.multiplechoicequiz;

/**
 * Created by xcode on 2018-04-09.
 */

public class Question {

    private String question;
    private String[] option = new String[4];
    private String answer;

    public Question(){

    }

    public Question(String question, String[] options, String answer){
        this.question = question;
        this.option[0] = options[0];
        this.option[1] = options[1];
        this.option[2] = options[2];
        this.option[3] = options[3];
        this.answer = answer;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public void setOption(int o, String option){
        this.option[o] = option;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getOption(int o){
        return option[o];
    }

    public String getAnswer(){
        return answer;
    }





}
