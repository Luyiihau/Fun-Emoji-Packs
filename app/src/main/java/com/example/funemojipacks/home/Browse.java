package com.example.funemojipacks.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funemojipacks.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class Browse extends AppCompatActivity {
    //    Context context;
    private ImageView imageView;
    private Button downBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_page);

        imageView = findViewById(R.id.image_view);
//        downBtn = findViewById(R.id.downBtn);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final int image = bundle.getInt("image");
        imageView.setImageResource(image);

    }

    public void onClickSave(View v) {
        imageView.setDrawingCacheEnabled(true);
        final Bitmap mmmbitmap = imageView.getDrawingCache();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = timestamp.toString() + ".jpg";
        savePicture(mmmbitmap, filename);//设置文件名 - 时间戳
        imageView.destroyDrawingCache();
    }

    public void onClickLike(View v){

    }

    public void savePicture(Bitmap bitmap, String filename) {
        if (bitmap == null || filename == null)
            return;
        String gallery = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/DCIM/" + filename;
        File myCaptureFile = new File(gallery);
        try {
            FileOutputStream fOut = new FileOutputStream(myCaptureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaStore.Images.Media.insertImage(this.getContentResolver(), bitmap, filename, null);
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(myCaptureFile)));
        Toast.makeText(this, "Success! Please check your photo album.", Toast.LENGTH_SHORT).show();
    }
}
