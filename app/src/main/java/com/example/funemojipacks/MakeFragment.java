package com.example.funemojipacks;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.funemojipacks.make.MakeAdapter;
import com.example.funemojipacks.make.make_appear;
import com.example.funemojipacks.make.make_face;
import com.example.funemojipacks.make.make_text;
import com.google.android.material.tabs.TabLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MakeFragment extends Fragment{
    private static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_PERMISSION = 2;

    //权限
    private static String[] PERMISSIONS_REQ = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private Button likeButton;//点赞按钮
    private Button downButton;//下载按钮
    private Button shareButton;//分享按钮

    //多点触控缩放
    private int finger = 0;
    private float distance;
    private float predistance;
    private PointF mid = new PointF();
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    private RelativeLayout relativeLayout;//用Relativelayout 替换 imageview
    private Bitmap bitmap1;//第一个背景图
    private Bitmap bitmap2;//表情图
    private String text;//表情包内文字
    TabLayout tabLayout;//滑动子页面
    ViewPager viewPager;//
    private View view;
    private Rectangle forFace;
    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> stringList = new ArrayList<>();
    private final int[] appears = {R.drawable.make1, R.drawable.make2, R.drawable.make3,
            R.drawable.make4, R.drawable.make5, R.drawable.makejj, R.drawable.make6};
    private final int[] faces = {R.drawable.face1, R.drawable.face2, R.drawable.face3, R.drawable.face4};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.make_page, container, false);
        relativeLayout =  view.findViewById(R.id.lll);
        relativeLayout.addView(new Rectangle(view.getContext()));
        bitmap1 = BitmapFactory.decodeResource(getResources(), appears[0]);
        initView();
        return view;
    }

    /**
     * 初始化
     */
    private void initView(){
        MakeFragment.this.requestPermissions(PERMISSIONS_REQ,1);
        forFace = new Rectangle(view.getContext());
//        setImage(0);
//        relativeLayout.addView(new Rectangle(view.getContext()));
        likeButton = (Button) view.findViewById(R.id.likeBtn);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        downButton = (Button) view.findViewById(R.id.downBtn);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertViewToBitmap();
            }
        });
        shareButton = (Button) view.findViewById(R.id.shareBtn);
        viewPager = (ViewPager) view.findViewById(R.id.makeviewPager);
        list.add(new make_appear());
        list.add(new make_face());
        list.add(new make_text());

        stringList.add("Appearance");
        stringList.add("Face");
        stringList.add("Text");

        tabLayout = (TabLayout) view.findViewById(R.id.make_tab_layout);

        for(String str: stringList){
            tabLayout.addTab(tabLayout.newTab().setText(str));
        }
        MakeAdapter adapter = new MakeAdapter(getChildFragmentManager(), list,stringList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 编辑文字
     * @param str
     */
    public void setMakeText(String str){
        int count = relativeLayout.getChildCount();
        while(count > 2){
            relativeLayout.removeViewAt(count - 1);
            count--;
        }
        this.text = str;
        relativeLayout.addView(new MakeForString(view.getContext()));
    }

    /**
     * 编辑背景 从make_appear传入参数
     * @param position
     */
    public void setImage(int position){
        if(relativeLayout.getChildAt(0) != null){
            bitmap1 = BitmapFactory.decodeResource(getResources(), appears[position]);
            relativeLayout.getChildAt(0).invalidate();
        }
        else{
            bitmap1 = BitmapFactory.decodeResource(getResources(), appears[position]);
            relativeLayout.addView(forFace);
            relativeLayout.getChildAt(0).invalidate();
        }
    }

    /**
     * 编辑脸部表情 从make_face传入参数
     * @param position
     */
    public void setFaceImage(final int position){
        bitmap2 = BitmapFactory.decodeResource(getResources(), faces[position]);
        relativeLayout.getChildAt(0).invalidate();
    }

    /**
     * 继承View以获得画布实现 画背景和表情
     */
    private class Rectangle extends View{
        float x = 0;
        float y = 0;
        public Rectangle(Context context){
            super(context);
        }

        @Override
        protected void onDraw(final Canvas canvas) {
//            System.out.println("hi");
            canvas.drawBitmap(bitmap1, 0, 0, null);
            if(bitmap2 != null){
                canvas.drawBitmap(bitmap2, x, y, null);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                x = event.getX();
                y = event.getY();
                // 重绘
                invalidate();
            }

            if (event.getAction() == MotionEvent.ACTION_POINTER_DOWN){
                predistance = getDistance(event);
                if(predistance > 10f){
                    mid = getMid(event);
                    savedMatrix.set(matrix);
                    finger = 2;
                }
            }
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                if(finger == 2){
                    distance = getDistance(event);
                    if(distance > 10f){
                        matrix.set(savedMatrix);
                        float scale = distance / predistance;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                int width = bitmap2.getWidth();
                int height = bitmap2.getHeight();
                bitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix, false);
                bitmap2.recycle();
            }

            return true;
        }

    }

    /**
     * 写字
     */
    private class MakeForString extends View{
        Paint paint = new Paint();
        float x = 10;
        float y = 100;
        public MakeForString(Context context){
            super(context);
        }

        @Override
        protected void onDraw(final Canvas canvas) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            canvas.drawText(text, x, y, paint);
        }

        public boolean onTouchEvent(MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                x = event.getX();
                y = event.getY();
                // 重绘
                invalidate();
            }
            return true;
        }
    }

    /**
     * 保存
     */
    public void convertViewToBitmap(){
        relativeLayout.setDrawingCacheEnabled(true);
        final Bitmap mmmbitmap = relativeLayout.getDrawingCache();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = timestamp.toString() + ".jpg";
        savePicture(mmmbitmap, filename);//设置文件名 - 时间戳
        relativeLayout.destroyDrawingCache();
    }

    public void savePicture(Bitmap bitmap, String filename){
        if(bitmap == null || filename == null)
            return;
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File( sdCard.getAbsoluteFile() + "/fun");
        directory.mkdirs();
        File myCaptureFile = new File(directory, filename);
        try{
            FileOutputStream fOut = new FileOutputStream(myCaptureFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            MediaStore.Images.Media.insertImage(view.getContext().getContentResolver(), myCaptureFile.getAbsolutePath(), filename, null);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        view.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(myCaptureFile.getPath()))));
        Toast.makeText(view.getContext(), "Success! Please check your photo album.", Toast.LENGTH_SHORT).show();
    }

    /*获取两指之间的距离*/
    private float getDistance(MotionEvent event) {
        float x = event.getX(1) - event.getX(0);
        float y = event.getY(1) - event.getY(0);
        float distance = (float) Math.sqrt(x * x + y * y);//两点间的距离
        return distance;
    }

    /*取两指的中心点坐标*/
    public static PointF getMid(MotionEvent event) {
        float midX = (event.getX(1) + event.getX(0)) / 2;
        float midY = (event.getY(1) + event.getY(0)) / 2;
        return new PointF(midX, midY);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}




