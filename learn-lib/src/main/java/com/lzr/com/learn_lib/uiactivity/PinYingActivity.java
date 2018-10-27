package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.PingYingView;

import java.util.ArrayList;
import java.util.List;

public class PinYingActivity extends AppCompatActivity {


    private String chinese = "池塘里有一群小蝌蚪，大大的脑袋，黑灰色的身子，甩着长长的尾巴，" +
            "欢快地游来游去。啊啊啊啊成成成成成成小学三年级课文，小学三年级课文，小学三年级课文，" +
            "小学三年级课文，小学三年级课文，小学三年级课文，今天天气真好！今天天气真好！今天天气真好！" +
            "今天天气真好！今天天气真好！池塘里有一群小蝌蚪，大大的脑袋，黑灰色的身子，甩着长长的尾巴 " +
            "欢快地游来游去。啊啊啊啊成成成成成成小学三年级课文，小学三年级课文，小学三年级课文" +
            "小学三年级课文，小学三年级课文，小学三年级课文，今天天气真好！今天天气真好！今天天气真好！" +
            "今天天气真好！今天天气真好！池塘里有一群小蝌蚪，大大的脑袋，黑灰色的身子，甩着长长的尾巴，" +
            "欢快地游来游去。啊啊啊啊成成成成成成小学三年级课文，小学三年级课文，小学三年级课文，" +
            "小学三年级课文，小学三年级课文，小学三年级课文，今天天气真好！今天天气真好！今天天气真好！" +
            "今天天气真好！今天天气真好！签名";
    private String pinyin = "chí#táng#lǐ#yǒu#yì#qún#xiǎo#kē#dǒu# #dà#dà#de#nǎo#dài# #hēi#huī#sè#de#shēn#zī# #shuǎi#zhe#cháng#cháng#de#wěi#ba# " +
            "#huài#huó#de#yóu#lái#yóu#qù# #á#á#á#á#chéng#chéng#chéng#chéng#chéng#chéng#xiǎo#xué#sān#nián#jí#kè#wén# " +
            "#xiǎo#xué#sān#nián#jí#kè#wén# #xiǎo#xué#sān#nián#jí#kè#wéw# #xiǎoi#xué#sān#nián#jí#kè#wén# #xiǎo#xué#sān#nián#jí#kè#wén#" +
            " #xiǎo#xué#sān#nián#jí#kè#wén# #jīn#tiān#tiān#qì#zhēn#hǎo# #jīn#tiān#tiān#qì#zhēn#hǎo# #jīn#tiān#tiān#qì#zhēn#hǎo# #jīn#tiān#tiān#qì#zhēn#hǎo# #jīn#tiān#tiān#qì#zhēn#hǎo# ";
    private PingYingView mPinying;

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
