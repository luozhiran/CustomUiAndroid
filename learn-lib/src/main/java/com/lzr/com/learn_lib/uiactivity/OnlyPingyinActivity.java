package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.view.PingYinView;

public class OnlyPingyinActivity extends AppCompatActivity {

    private PingYinView mPinying;
    private String pinyin = "chí#táng#lǐ#yǒu#yì#qún#xiǎo#kē#dǒu#dà#dà#de#nǎo#dài#hēi#huī#sè#de#shēn#zī#shuǎi#zhe#cháng#cháng#de#wěi#ba#" +
            "huài#huó#de#yóu#lái#yóu#qù#  á#á#á#á#chéng#chéng#chéng#chéng#chéng#chéng#xiǎo#xué#sān#nián#jí#kè#wén#" +
            "xiǎo#xué#sān#nián#jí#kè#wén#xiǎo#xué#sān#nián#jí#kè#wéw#xiǎoi#xué#sān#nián#jí#kè#wén#xiǎo#xué#sān#nián#jí#kè#wén#" +
            "xiǎo#xué#sān#nián#jí#kè#wén#jīn#tiān#tiān#qì#zhēn#hǎo#jīn#tiān#tiān#qì#zhēn#hǎo#jīn#tiān#tiān#qì#zhēn#hǎo#jīn#tiān#tiān#qì#zhēn#hǎo#jīn#tiān#tiān#qì#zhēn#hǎo#";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_pingyin);
        initView();
    }

    private void initView() {
        mPinying = (PingYinView) findViewById(R.id.pinying);
        mPinying.setYinBiao(pinyin, "#");
    }
}
