package com.lzr.com.learn_lib.uiactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.lzr.com.learn_lib.R;
import com.lzr.com.learn_lib.interfaces.OnSelectListener;
import com.lzr.com.learn_lib.view.SelectableTextHelper;

public class SelectTextViewWordActivity extends AppCompatActivity {

    private TextView mTextView;
    private SelectableTextHelper mSelectableTextHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_text_view_word);
        initView();
    }

    private void initView() {
        mTextView =  findViewById(R.id.textView);
        mSelectableTextHelper = new SelectableTextHelper.Builder(mTextView)
                .setSelectedColor(getResources().getColor(R.color.selected_blue))
                .setCursorHandleSizeInDp(20)
                .setCursorHandleColor(getResources().getColor(R.color.cursor_handle_color))
                .build();
        mSelectableTextHelper.setSelectListener(new OnSelectListener() {
            @Override
            public void onTextSelected(CharSequence content) {
                Toast.makeText(SelectTextViewWordActivity.this,content,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
