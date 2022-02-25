package com.example.countrynews.utils;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.example.countrynews.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class KeyboardView extends FrameLayout implements View.OnClickListener {

    private EditText mPasswordField;
    private  int[] tileList={0,1,2,3,4,5,6,7,8,9};


    public KeyboardView(Context context) {
        super(context);

        init();
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void scramble() {

        int index;
        String temp;
        Random random = new Random();

//        int  shuffle =random.nextInt(1000);
//        tileList[shuffle];

//        for (int i = 0; i <=9; i++) {
//            index = random.nextInt(i+1);
//            temp = tileList[index];
//            tileList[i] = tileList[index];
//            tileList[i] = temp;
//        }
    }

    private void init() {
        inflate(getContext(), R.layout.keyboard, this);
        shuffleArray(tileList);
        try{
            for (int i = 0; i < tileList.length; i++)
            {
                System.out.print(tileList[i] + " ");
                TextView txtView;
                Log.e("Text is =>",String.valueOf(tileList[i]));
                if (i == 0) {
                    txtView = (TextView) findViewById(R.id.t9_key_0);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 1) {
                    txtView = (TextView) findViewById(R.id.t9_key_1);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 2) {
                    txtView = (TextView) findViewById(R.id.t9_key_2);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 3) {
                    txtView = (TextView) findViewById(R.id.t9_key_3);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 4) {
                    txtView = (TextView) findViewById(R.id.t9_key_4);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 5) {
                    txtView = (TextView) findViewById(R.id.t9_key_5);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 6) {
                    txtView = (TextView) findViewById(R.id.t9_key_6);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 7) {
                    txtView = (TextView) findViewById(R.id.t9_key_7);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 8) {
                    txtView = (TextView) findViewById(R.id.t9_key_8);
                    txtView.setText(String.valueOf(tileList[i]));
                } else if (i == 9) {
                    txtView = (TextView) findViewById(R.id.t9_key_9);
                    txtView.setText(String.valueOf(tileList[i]));
                }
            }

        }catch (Exception e){
            Log.e("Shuffle error==>",e.getMessage());
        }


//        TextView txtView = (TextView) findViewById(R.id.t9_key_0);
//        txtView.setText(String.valueOf(22));
        initViews();
    }

    static void shuffleArray(int[] ar)
    {
        Random random =new Random();
//        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = random.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private void initViews() {
        mPasswordField = $(R.id.password_field);
        $(R.id.t9_key_0).setOnClickListener(this);
        $(R.id.t9_key_1).setOnClickListener(this);
        $(R.id.t9_key_2).setOnClickListener(this);
        $(R.id.t9_key_3).setOnClickListener(this);
        $(R.id.t9_key_4).setOnClickListener(this);
        $(R.id.t9_key_5).setOnClickListener(this);
        $(R.id.t9_key_6).setOnClickListener(this);
        $(R.id.t9_key_7).setOnClickListener(this);
        $(R.id.t9_key_8).setOnClickListener(this);
        $(R.id.t9_key_9).setOnClickListener(this);
        $(R.id.t9_key_clear).setOnClickListener(this);
        $(R.id.t9_key_backspace).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // handle number button click
        if (v.getTag() != null && "number_button".equals(v.getTag())) {
            mPasswordField.append(((TextView) v).getText());
            return;
        }
        switch (v.getId()) {
            case R.id.t9_key_clear: { // handle clear button
                mPasswordField.setText(null);
            }
            break;
            case R.id.t9_key_backspace: { // handle backspace button
                // delete one character
                Editable editable = mPasswordField.getText();
                int charCount = editable.length();
                if (charCount > 0) {
                    editable.delete(charCount - 1, charCount);
                }
            }
            break;
        }
    }

    public String getInputText() {
        return mPasswordField.getText().toString();
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) super.findViewById(id);
    }
}
