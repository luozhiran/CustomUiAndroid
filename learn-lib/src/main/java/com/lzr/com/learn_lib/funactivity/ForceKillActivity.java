package com.lzr.com.learn_lib.funactivity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lzr.com.learn_lib.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ForceKillActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mKillProgressBtnForce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_kill);
        initView();
    }

    private void initView() {
        mKillProgressBtnForce = (Button) findViewById(R.id.force_kill_progress_btn);
        mKillProgressBtnForce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.force_kill_progress_btn) {
            forceStopProgress(this,"com.yqtec.model");
        } else {
        }
    }


        public static void forceStopProgress(Context context, String pkgName) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

            try {
                Method forceStopPackage = am.getClass().getDeclaredMethod("forceStopPackage", String.class);
                forceStopPackage.setAccessible(true);
                forceStopPackage.invoke(am, pkgName);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

}
