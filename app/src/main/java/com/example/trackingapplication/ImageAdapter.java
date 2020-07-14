package com.example.trackingapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
public class ImageAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> photoList;
    private TextView name;
    private ImageView img;

    public ImageAdapter(Context context, ArrayList<String> photoList) {
        this.context = context;
        this.photoList = photoList;
    }
    @Override
    public int getCount() {
        return photoList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        name = convertView.findViewById(R.id.text_view_name);
        img=convertView.findViewById(R.id.image_view_upload);
        Bitmap bm = StringToBitMap(photoList.get(position));
       // img.setImageBitmap(bm);   //MyPhoto is image control.
        Glide.with(context).load(bm).into(img);
        return convertView;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}