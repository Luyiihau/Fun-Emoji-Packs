package com.example.funemojipacks.shareFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funemojipacks.R;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

public class ResultImageAdapter extends RecyclerView.Adapter<ResultImageHolder> {

    Context c;
    AppCompatActivity a;
    ArrayList<ImageModel> images;
    OnClickListener mOnClickListener;


    public ResultImageAdapter(Context c, AppCompatActivity a, ArrayList<ImageModel> images) {
        this.c = c;
        this.a = a;
        this.images = images;
        mOnClickListener = (OnClickListener) c;
    }

    @NonNull
    @Override
    public ResultImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // convert xml to view obj
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_image_result_layout, null);
        return new ResultImageHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull final ResultImageHolder holder, final int position) {
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File outFile = new File(outDir, System.currentTimeMillis() + ".jpg");
        final Uri destinationUri = Uri.fromFile(outFile);
        final String imagePath = images.get(position).getPath();
        final Uri imageURL = Uri.fromFile(new File(imagePath));
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        holder.image.setImageBitmap(bitmap);



        holder.imageRelative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ImageView image = (ImageView)a.findViewById(R.id.image_result);
                final String imagePath = images.get(position).getPath();
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                bitmap = changeBitmapSize(bitmap);
                image.setImageBitmap(bitmap);
            }
        });

        //长按调用Ucrop进行图片裁剪
        holder.imageRelative.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mOnClickListener.setSelectedPosition(position);
                UCrop.of(imageURL, destinationUri)
                        .withAspectRatio(1, 1)
                        .withMaxResultSize(240, 240)
                        .start(a);

                return false;
            }
        });
    }


    public Bitmap changeBitmapSize(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //设置想要的大小
        int newWidth=240;
        int newHeight=240;

        //计算压缩的比率
        float scaleWidth=((float)newWidth)/width;
        float scaleHeight=((float)newHeight)/height;

        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);

        //获取新的bitmap
        bitmap=Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        bitmap.getWidth();
        bitmap.getHeight();
        return bitmap;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public interface OnClickListener {
        public void setSelectedPosition(int position);
    }
}
