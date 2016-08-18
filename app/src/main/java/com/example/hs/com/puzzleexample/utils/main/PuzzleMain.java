package com.example.hs.com.puzzleexample.utils.main;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hs.com.puzzleexample.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huha on 2016/8/18.
 */
public class PuzzleMain extends AppCompatActivity {
    @Bind(R.id.gv_puzzle_main_detail)
    GridView mGvPicDetail;
    @Bind(R.id.tv_puzzle_main_counts)
    TextView mPuzzleCounts;
    @Bind(R.id.tv_puzzle_main_time)
    TextView mPuzzleTime;
    @Bind(R.id.btn_puzzle_main_img)
    Button mPuzzleImg;
    @Bind(R.id.btn_puzzle_main_restart)
    Button mPuzzleRestart;
    @Bind(R.id.btn_puzzle_main_back)
    Button mPuzzleBack;

    // 拼图完成时显示的最后一个图片
    public static Bitmap mLastBitmap;
    // 设置为N*N显示
    public static int TYPE = 2;
    // 步数显示
    public static int COUNT_INDEX = 0;
    // 计时显示
    public static int TIMER_INDEX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle_game);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.btn_puzzle_main_back)
    public void puzzleGoBack(){

    }
}
