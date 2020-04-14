package com.example.funemojipacks.shareFragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.funemojipacks.R;

public class ImageHolder extends RecyclerView.ViewHolder{

    //Views
    RelativeLayout imageRelative;
    ImageView image;
    CheckBox checkBox;
    public ImageHolder(View itemView) {
        super(itemView);
        this.imageRelative = itemView.findViewById(R.id.image_relative);
        this.image = itemView.findViewById((R.id.card_image));
        this.checkBox = itemView.findViewById(R.id.card_image_check);
    }

}
