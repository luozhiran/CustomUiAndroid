package com.lzr.com.customuiandroid;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestActivity extends AppCompatActivity {


    private RecyclerView mRecycel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mRecycel = (RecyclerView) findViewById(R.id.recycel);
        mRecycel.setLayoutManager(new LinearLayoutManager(this));
        mRecycel.setAdapter(new TAdapter(this, new ArrayList<>(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecycel.removeView(v);

                v.setScaleY(0.3f);
            }
        }));
    }


    public static class TAdapter extends RecyclerView.Adapter {

        private List<String> mlist;
        private Context mcontext;
        private View.OnClickListener onClickListener;

        public TAdapter(Context context, List<String> list, View.OnClickListener l) {
            mlist = list;
            mcontext = context;
            onClickListener = l;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolderx(LayoutInflater.from(mcontext).inflate(R.layout.txt, null, false));
        }



        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          holder.itemView.setOnClickListener(onClickListener);
        }

        @Override
        public int getItemCount() {
            return 50;
        }

    }


    public static class ViewHolderx extends RecyclerView.ViewHolder {
        private ImageView mImg;

        public ViewHolderx(View itemView) {
            super(itemView);
        }
        private void initView(@NonNull final View itemView) {
            mImg = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
                                                                        boolean includeStatusBar, @Nullable Pair... otherParticipants) {

        List<Pair> participants = new ArrayList<>(3);
        if (otherParticipants != null && !(otherParticipants.length == 1
                && otherParticipants[0] == null)) {
            participants.addAll(Arrays.asList(otherParticipants));
        }
        return participants.toArray(new Pair[participants.size()]);
    }

}
