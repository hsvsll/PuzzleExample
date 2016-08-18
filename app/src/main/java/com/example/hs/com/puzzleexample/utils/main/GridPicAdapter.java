package com.example.hs.com.puzzleexample.utils.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.hs.com.puzzleexample.utils.utils.AppUtils;

import java.util.List;

/**
 * Created by huha on 2016/8/18.
 */
public class GridPicAdapter extends BaseAdapter{

    private List<Bitmap> mPicList;
    private Context mContext;
    public GridPicAdapter(Context context,List<Bitmap> picList){
        this.mContext = context;
        this.mPicList = picList;
    }

    @Override
    public int getCount() {
        return mPicList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPicList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv_pic_item = null;
        int destity = (int) AppUtils.getDeviceDensity(mContext);
        if(convertView == null){
            iv_pic_item = new ImageView(mContext);
            //设置布局图片
            iv_pic_item.setLayoutParams(new GridView.LayoutParams(80*destity,100*destity));
            //设置显示比例
            iv_pic_item.setScaleType(ImageView.ScaleType.FIT_XY);

        }else{
            iv_pic_item = (ImageView) convertView;
        }
        iv_pic_item.setBackgroundColor(Color.BLACK);
        iv_pic_item.setImageBitmap(mPicList.get(position));
        return iv_pic_item;
    }
}
