package com.example.funemojipacks.shareFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;
import com.example.funemojipacks.ShareFragment;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ShareImageResultActivity extends AppCompatActivity implements ResultImageAdapter.OnClickListener{
    RecyclerView mRecyclerView;
    ResultImageAdapter myAdapter;
    ArrayList<ImageModel> selectedImages = new ArrayList<>();
    int position;
    TextView shareText;
    ImageView image;
    DatabaseHelper memeDb;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_image_result);
        memeDb = new DatabaseHelper(this);

        selectedImages = (ArrayList<ImageModel>) getIntent().getSerializableExtra("selectedImages");
        mRecyclerView = findViewById(R.id.share_result_recyclerView);
        // Set its Properties using GridLayout with 4 column
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // Set RecyclerView Adapter
        myAdapter = new ResultImageAdapter(this,this, selectedImages);
        mRecyclerView.setAdapter(myAdapter);
        setPadding(selectedImages.size());

        //默认设置第一张显示
        final String imagePath = selectedImages.get(0).getPath();
        image = (ImageView)findViewById(R.id.image_result);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        bitmap = myAdapter.changeBitmapSize(bitmap);
        image.setImageBitmap(bitmap);

        shareText = (TextView)findViewById(R.id.share_Text);
        shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                //这里处理分享图片逻辑
                for(ImageModel image: selectedImages){
                    String imagePath = image.getPath();

                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    bitmap = myAdapter.changeBitmapSize(bitmap);

                    //转换成byte
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageByte = baos.toByteArray();
                    bitmap.recycle();

                    memeDb.insertPic(imageByte, "0");
                    memeDb.insertShare(String.valueOf(MainActivity.userID), String.valueOf(memeDb.getJustAddedPicID()));
                    Toast.makeText(getApplicationContext(), R.string.share_complete, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


    }

    //根据list的元素多少设定padding。当少元素的时候，显示可以达到居中效果
    public void setPadding(int listSize){
        if(listSize == 1){
            mRecyclerView.setPadding(380,200,0,0);
        }else if(listSize == 2){
            mRecyclerView.setPadding(200,200,0,0);
        }else if(listSize == 3){
            mRecyclerView.setPadding(30,200,0,0);
        }else {
            mRecyclerView.setPadding(0,200,0,0);
        }
    }


    //处理ucrop处理完的相片
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case UCrop.REQUEST_CROP:
                if(resultCode == RESULT_OK){
                    Uri resultUri = UCrop.getOutput(data);
                    String path = resultUri.getPath();
                    //send a broadcast to android device indicating there is a new image
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(resultUri);
                    this.sendBroadcast(intent);

                    //将图片加入到相册中
                    ImageModel imageModel = new ImageModel(path,path);
                    ShareFragment.storageImages.add(0, imageModel);

                    //替换selectedImages中的元素
                    System.out.println(position);
                    selectedImages.remove(position);
                    selectedImages.add(0,imageModel);
                    myAdapter = new ResultImageAdapter(this,this, selectedImages);
                    mRecyclerView.setAdapter(myAdapter);
                    setPadding(selectedImages.size());

                    //更改显示ImageView 图片
                    final String imagePath = selectedImages.get(0).getPath();
                    image = (ImageView)findViewById(R.id.image_result);
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    bitmap = myAdapter.changeBitmapSize(bitmap);
                    image.setImageBitmap(bitmap);
                }
        }
    }


    @Override
    public void setSelectedPosition(int position){
        this.position = position;
    }



}
