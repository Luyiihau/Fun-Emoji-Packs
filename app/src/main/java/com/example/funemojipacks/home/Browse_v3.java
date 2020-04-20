package com.example.funemojipacks.home;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.funemojipacks.DatabaseHelper;
import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;
import com.example.funemojipacks.shareFragment.LoginActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class Browse_v3 extends AppCompatActivity {
    //    Context context;
    private ImageView imageView;
    private byte[] image;
    private DatabaseHelper memeDb;
    private int pic_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_page);
        memeDb = new DatabaseHelper(this);

        imageView = findViewById(R.id.image_view);
//        downBtn = findViewById(R.id.downBtn);


        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        image = bundle.getByteArray("image");
        assert image != null;
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bmp);
        pic_id = bundle.getInt("pic_id");
    }

    public void onClickSave(View v) {
        imageView.setDrawingCacheEnabled(true);
        final Bitmap mmmbitmap = imageView.getDrawingCache();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = timestamp.toString() + ".jpg";
        savePicture(mmmbitmap, filename);//设置文件名 - 时间戳
        imageView.destroyDrawingCache();
    }

    public void onClickLike(View v) {
        if (!MainActivity.isLogin) {
            System.out.println("Not login2");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
//        else
//        {
//            memeDb.updateLike()
//        }

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
