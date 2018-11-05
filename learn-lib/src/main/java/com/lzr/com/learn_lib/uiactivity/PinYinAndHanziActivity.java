package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.PingYinAndHanziView;
import com.lzr.com.learn_lib.view.PingYinView;

public class PinYinAndHanziActivity extends AppCompatActivity {


    private String chinese = "池塘里有一群小蝌蚪池塘里有一群小蝌蚪池塘里有一群小蝌蚪池塘里有一群小蝌蚪";
    private String pinyin = "chí#táng#lǐ#yǒu#yì#qún#xiǎo#kē#dǒu#chí#táng#lǐ#yǒu#yì#qún#xiǎo#kē#dǒu#chí#táng#lǐ#yǒu#yì#qún#xiǎo#kē#dǒu#chí#táng#lǐ#yǒu#yì#qún#xiǎo#kē#dǒu";

    private PingYinAndHanziView mPinying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_ying);
        initView();
    }

    private void initView() {
        mPinying = findViewById(R.id.pinying);
        mPinying.setText(chinese);
        mPinying.setYinBiao(pinyin, "#");
    }


}
