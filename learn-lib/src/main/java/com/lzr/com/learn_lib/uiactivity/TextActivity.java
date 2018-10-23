package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.HandWritingView;

public class TextActivity extends AppCompatActivity {

    private EditText input;
    private HandWritingView handwriting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);

        this.input = (EditText) findViewById(R.id.input);
        this.handwriting = (HandWritingView) findViewById(R.id.handwrite);
        input.setText("中");
    }

    public void setText(View view) {
        String str = input.getText().toString().trim();
        try {
            handwriting.setText(str.charAt(0));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void op(View view) {

    }
}
