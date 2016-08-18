package com.example.hs.com.puzzleexample.utils.entity;

import android.graphics.Bitmap;

/**
 * 拼图Item逻辑实体类：封装逻辑相关代码
 * Created by huha on 2016/8/18.
 */
public class ImageItemBean {
    private int mItemId;
    private int mBitmapId;
    private Bitmap mBitmap;

    public ImageItemBean(){

    }

    public ImageItemBean(int mItemId,int mBitmapId,Bitmap mBitmap){
        this.mItemId = mItemId;
        this.mBitmapId = mBitmapId;
        this.mBitmap = mBitmap;
    }

    public int getmItemId() {
        return mItemId;
    }

    public void setmItemId(int mItemId) {
        this.mItemId = mItemId;
    }

    public int getmBitmapId() {
        return mBitmapId;
    }

    public void setmBitmapId(int mBitmapId) {
        this.mBitmapId = mBitmapId;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
