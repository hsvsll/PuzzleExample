package com.example.hs.com.puzzleexample.utils.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.hs.com.puzzleexample.R;

/**
 * Created by huha on 2016/8/18.
 */
public class CustomPicDialogFragment extends DialogFragment{
    private Context mContext;
    private SelectedPicWayListener listener;
    public CustomPicDialogFragment(Context context){
        this.mContext = context;
        this.listener = (SelectedPicWayListener) context;
    }

    public interface SelectedPicWayListener{
        void onSelectedPicTakePhoto();
        void onSelectedPicAlbum();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_fragment, null);
        view.findViewById(R.id.bt_photo_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectedPicTakePhoto();
            }
        });
        view.findViewById(R.id.bt_album_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectedPicAlbum();
            }
        });
        builder.setView(view)
                .setNegativeButton("取消",null);
        return builder.create();
    }
}
