package com.lzr.com.learn_lib.uiactivity;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lzr.com.control_lib.HandlerActivity;
import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.interfaces.KnowledgeChangeModeCallback;
import com.lzr.com.learn_lib.view.KnowledgeView;
import com.lzr.com.learn_lib.view.OperationView;
import com.lzr.com.learn_lib.view.WavVoiceView;

import java.util.Random;

public class WavActivity extends HandlerActivity implements View.OnClickListener {

    private WavVoiceView mView;

    private Random random = new Random();
    private Button mStart;
    private KnowledgeView mViewKnowledge;
    private OperationView mViewOperation;
    private TextView mTest;


    @Override
    public int getLayoutId() {
        return R.layout.activity_wav;
    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Override
    public void initView() {
        mView = findViewById(R.id.view);
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mViewKnowledge = (KnowledgeView) findViewById(R.id.knowledge_view);
        mViewOperation = (OperationView) findViewById(R.id.operation_view);
        mTest = (TextView) findViewById(R.id.test);
        mTest.setOnClickListener(this);
        mViewKnowledge.setKnowledgeChangeModeCallback(new KnowledgeChangeModeCallback() {
            @Override
            public void changeMode(int mode) {
                if (mViewKnowledge.getMode() == 0) {
                    mViewKnowledge.startZoomInMic(KnowledgeView.S_T_M);
                } else if (KnowledgeView.S_T_M == mode) {
                    mViewKnowledge.startZoomInMic(KnowledgeView.M_T_S);
                }else if (KnowledgeView.M_T_S == mode){
                    mViewKnowledge.startZoomInMic(KnowledgeView.S_T_P);

                }
            }
        });
    }

    @Override
    public void initData() {
        mView.initAnim();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.start) {
//            mView.startAnim();
            mViewOperation.startImgReciteAnim();
            Log.e("ddd", mViewOperation.getWidth() + "  " + mViewOperation.getHeight());
        } else if (i == R.id.knowledge_view) {

        } else if (i == R.id.test) {
            Toast.makeText(this, "你好", Toast.LENGTH_SHORT).show();
        }
    }
}
