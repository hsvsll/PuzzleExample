package com.example.hs.com.puzzleexample.utils.main;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hs.com.puzzleexample.R;
import com.example.hs.com.puzzleexample.utils.custom.CustomPicDialogFragment;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,CustomPicDialogFragment.SelectedPicWayListener{
    @Bind(R.id.tv_puzzle_main_type_selected)
    TextView mTypeSelected;
    @Bind(R.id.gv_puzzle_main_pic_list)
    GridView mGvPicList;

    private ArrayList mPicList;
    private int[] mResPicId = new int[]{R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.e,R.mipmap.f,R.mipmap.g,R.mipmap.l,R.mipmap.ic_launcher};
    private Bitmap[] mBitmaps;

    //返回码 ：本地图库
    private static final int RESULT_IMAGE = 100;
    //返回码 ：相机
    private static final int RESULT_CAMERA = 200;
    //IMAGE TYPE
    private static final String IMAGE_TYPE = "image/*";

    //Temp照片路径
    public static String TEMP_IMAGE_PATH;

    // 游戏类型N*N
    private int mType = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        mGvPicList.setOnItemClickListener(this);
        mBitmaps =  new Bitmap[mResPicId.length];
        mPicList = new ArrayList<>();
        for(int i = 0;i<mResPicId.length;i++ ){
            mBitmaps[i] = BitmapFactory.decodeResource(getResources(),mResPicId[i]);
            mPicList.add(mBitmaps[i]);
        }
    }

    private void initData() {
        mGvPicList.setAdapter(new GridPicAdapter(MainActivity.this,mPicList));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == mBitmaps.length-1){
            //选择本地图库、相机
            showDialogCustom();
        }else{
            //选择默认图片
            Intent intent = new Intent(MainActivity.this,PuzzleMain.class);
            intent.putExtra("picSelectedID",mResPicId[position]);
            intent.putExtra("mType",IMAGE_TYPE);
            startActivity(intent);
        }
    }

    private void showDialogCustom() {
        CustomPicDialogFragment picDialogFragment = new CustomPicDialogFragment(MainActivity.this);
        picDialogFragment.show(getSupportFragmentManager(), "picDialog");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == RESULT_IMAGE && data != null){
                //相册
                Cursor cursor = this.getContentResolver().query(data.getData(),null,null,null,null);
                cursor.moveToFirst();
                String imagePath = cursor.getString(cursor.getColumnIndex("_data"));
                Intent intent = new Intent(MainActivity.this,PuzzleMain.class);
                intent.putExtra("mType",mType);
                intent.putExtra("mPicPath",imagePath);
                cursor.close();
                startActivity(intent);
            }
            else if(requestCode == RESULT_CAMERA){
                //相机
                Intent intent = new Intent(MainActivity.this,PuzzleMain.class);
                intent.putExtra("mPicPath",TEMP_IMAGE_PATH);
                intent.putExtra("mType",mType);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onSelectedPicTakePhoto() {
        TEMP_IMAGE_PATH = Environment.getExternalStorageDirectory().getPath()+"/temp.png";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoUri = Uri.fromFile(new File(TEMP_IMAGE_PATH));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        startActivityForResult(intent,RESULT_CAMERA);
    }

    @Override
    public void onSelectedPicAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK,null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,IMAGE_TYPE);
        startActivityForResult(intent,RESULT_IMAGE);
    }
}
