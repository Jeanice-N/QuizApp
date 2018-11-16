package com.sample.multiplechoicequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xcode on 2018-04-09.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static String DB_QUESTION = "questionBank2.db";
    //database version
    private static final int DB_VERSION = 1;
    //database table name
    private static final String TABLE_NAME = "QuestionBank";
    //database fields
    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";
    private static final String CHOICE4 = "choice4";
    private static final String ANSWER = "answer";

    //Create table statement
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT," + CHOICE1 + " TEXT," +
            CHOICE2 + " TEXT," + CHOICE3 + " TEXT," + CHOICE4 + " TEXT," + ANSWER + " TEXT);";

    public MyDatabaseHelper(Context context) {
        super(context, DB_QUESTION, null, DB_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create table
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String dropQuery = "DROP TABLE IF EXISTS " + CREATE_TABLE;
        //drop table if exists
        sqLiteDatabase.execSQL(dropQuery);
        onCreate(sqLiteDatabase);
    }

    //add question details
    public long addQuestionDetails(Question question){

        SQLiteDatabase db = this.getWritableDatabase();

        //create values
        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(CHOICE1, question.getOption(0));
        values.put(CHOICE2, question.getOption(1));
        values.put(CHOICE3, question.getOption(2));
        values.put(CHOICE4, question.getOption(3));
        values.put(ANSWER, question.getAnswer());

        //insert row in table
        long rowInsert = db.insert(TABLE_NAME, null, values);
        return rowInsert;
    }

    //extract data from db to ArrayList
    public List<Question> getQuestions(){

        List<Question> questionList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //loop thru records and add to list
        if(cursor.moveToFirst()){
            do {
                Question q = new Question();

                String qText = cursor.getString(cursor.getColumnIndex(QUESTION));
                q.setQuestion(qText);

                String opt1Text = cursor.getString(cursor.getColumnIndex(CHOICE1));
                q.setOption(0, opt1Text);

                String opt2Text = cursor.getString(cursor.getColumnIndex(CHOICE2));
                q.setOption(1, opt2Text);

                String opt3Text = cursor.getString(cursor.getColumnIndex(CHOICE3));
                q.setOption(2, opt3Text);

                String opt4Text = cursor.getString(cursor.getColumnIndex(CHOICE4));
                q.setOption(3, opt4Text);

                String aText = cursor.getString(cursor.getColumnIndex(ANSWER));
                q.setAnswer(aText);

                //add to list
                questionList.add(q);
            } while(cursor.moveToNext());

         
        }
            return questionList;

    }


}
