package com.example.funemojipacks.shareFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funemojipacks.R;
import com.example.funemojipacks.ShareFragment;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {
    Context c;
    Fragment f;
    ArrayList<ImageModel> images;
    public ArrayList<ImageModel> selectedImages = new ArrayList<>();

    public ImageAdapter(Context c, Fragment f, ArrayList<ImageModel> images) {
        this.c = c;
        this.f = f;
        this.images = images;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // convert xml to view obj
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_image_layout, null);
        return new ImageHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final ImageHolder holder, final int position) {
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        String cameraScalePath = outFile.getAbsolutePath();
        final Uri destinationUri = Uri.fromFile(outFile);
        final String imagePath = images.get(position).getPath();
        final Uri imageURL = Uri.fromFile(new File(imagePath));
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        holder.image.setImageBitmap(bitmap);
        // load image Animation
        Animation animation = AnimationUtils.loadAnimation(c, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);

        //设置CheckBox和背景
        boolean isChecked = images.get(position).getIsChecked();
        holder.checkBox.setChecked(isChecked);
        if(isChecked == true)
            holder.imageRelative.setAlpha(0.6f);
        else
            holder.imageRelative.setAlpha(1f);

        //处理短按选择图片上传
        holder.imageRelative.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                boolean isChecked = images.get(position).getIsChecked();
                if(isChecked == false){
                    isChecked = true;
                    images.get(position).setIsChecked(true);
                    holder.imageRelative.setAlpha(0.6f);
                    //添加到arraylist
                    selectedImages.add(images.get(position));
                }else {
                    isChecked = false;
                    images.get(position).setIsChecked(false);
                    holder.imageRelative.setAlpha(1f);
                    //从arraylist里面删除
                    selectedImages.remove(images.get(position));
                }
                ShareFragment sf = (ShareFragment) f;
                sf.setSelectedImages(selectedImages);
                holder.checkBox.setChecked(isChecked);

            }
        });

        //长按调用Ucrop进行图片裁剪
        holder.imageRelative.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                UCrop.of(imageURL, destinationUri)
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(240, 240)
                    .start(c,f);


                return false;
            }
        });


    }
    @Override
    public int getItemCount() {
        return images.size();
    }


}
