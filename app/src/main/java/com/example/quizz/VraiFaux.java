package com.example.quizz;

public class VraiFaux {
    private int mQuestion;
    private boolean mQuestionVraie;

    public VraiFaux(int maQuestion, boolean maQuestionVraie) {
        this.mQuestion = maQuestion;
        this.mQuestionVraie = maQuestionVraie;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean ismQuestionVraie() {
        return mQuestionVraie;
    }

    public void setmQuestionVraie(boolean mQuestionVraie) {
        this.mQuestionVraie = mQuestionVraie;
    }
}
