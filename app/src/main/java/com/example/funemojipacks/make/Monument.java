package com.example.funemojipacks.make;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.funemojipacks.MakeFragment;
import com.example.funemojipacks.R;
import com.example.funemojipacks.make.MakeAdapter;
import com.example.funemojipacks.make.make_appear;
import com.example.funemojipacks.make.make_face;
import com.example.funemojipacks.make.make_text;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Monument {
//    protected int screenWidth;
//    protected int screenHeight;
//    protected int lastX;
//    protected int lastY;
//    private int oriLeft;
//    private int oriRight;
//    private int oriTop;
//    private int oriBottom;
//    private int dragDirection;
//    private static final int TOP = 0x15;
//    private static final int LEFT = 0x16;
//    private static final int BOTTOM = 0x17;
//    private static final int RIGHT = 0x18;
//    private static final int LEFT_TOP = 0x11;
//    private static final int RIGHT_TOP = 0x12;
//    private static final int LEFT_BOTTOM = 0x13;
//    private static final int RIGHT_BOTTOM = 0x14;
//    private static final int TOUCH_TWO = 0x21;
//    private static final int CENTER = 0x19;
//    private int offset = 0; //可超出其父控件的偏移量
//    protected Paint paint = new Paint();
//    private static final int touchDistance = 80; //触摸边界的有效距离
//
//    public int myleft = 0;
//    public int myright = 0;
//
//    private static final int REQUEST_CODE = 1;
//    private static final int REQUEST_CODE_PERMISSION = 2;
//
//    private static String[] PERMISSIONS_REQ = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//    };
//
//    // 初始的两个手指按下的触摸点的距离
//    private float oriDis = 1f;
//
//    Button likeButton;//点赞按钮
//    Button downButton;//下载按钮
//    Button shareButton;//分享按钮
//    //    private ImageView emojiImage;//表情图片（按钮左边的ImageView）
//    private RelativeLayout relativeLayout;//用Relativelayout 替换 imageview
//    private RelativeLayout relativeLayout2;//face
//    private Bitmap bitmap1;//第一个背景图
//    private Bitmap bitmap2;//表情图
//    private Bitmap bitmap3;//文字（备用）
//    private String text;//表情包内文字
//    private View faceView;
//    //    private Rect mSrc;
//    TabLayout tabLayout;//滑动子页面
//    ViewPager viewPager;//
//    //    private ConstraintLayout constraintLayout2;
////    Bitmap bitmap;
//    private View view;
//    private MakeFragment.Rectangle forFace;
//    private List<Fragment> list = new ArrayList<Fragment>();
//    private List<String> stringList = new ArrayList<>();
//    final int[] appears = {R.drawable.make1, R.drawable.make2,
//            R.drawable.make3, R.drawable.make4,
//            R.drawable.make5,R.drawable.make6};
//    final int[] faces = {R.drawable.face1, R.drawable.face2, R.drawable.face3, R.drawable.face4};
//    Canvas canvas;
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.make_page, container, false);
//        relativeLayout =  view.findViewById(R.id.lll);
//        relativeLayout.addView(new MakeFragment.Rectangle(view.getContext()));
//        bitmap1 = BitmapFactory.decodeResource(getResources(), appears[5]);
//        initView();
//        return view;
//    }
//
//    public void convertViewToBitmap(){
//        relativeLayout.setDrawingCacheEnabled(true);
//        final Bitmap mmmbitmap = relativeLayout.getDrawingCache();
//        savePicture(mmmbitmap, "test.jpg");
//        relativeLayout.destroyDrawingCache();
//    }
//
//    public void savePicture(Bitmap bitmap, String filename){
//        if(bitmap == null || filename == null)
//            return;
////        System.out.println(bitmap);
//        File sdCard = Environment.getExternalStorageDirectory();
//        File directory = new File( sdCard.getAbsoluteFile() + "/fun");
//        directory.mkdirs();
//        File myCaptureFile = new File(directory, filename);
//        try{
//            FileOutputStream fOut = new FileOutputStream(myCaptureFile);
////            OutputStreamWriter osw = new OutputStreamWriter(fOut);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
//            fOut.flush();
//            fOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try{
//            MediaStore.Images.Media.insertImage(view.getContext().getContentResolver(), myCaptureFile.getAbsolutePath(), filename, null);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        view.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(myCaptureFile.getPath()))));
//
//        Toast.makeText(view.getContext(), "保存成功!", Toast.LENGTH_SHORT).show();
//    }
//
//    private void initView(){
//        MakeFragment.this.requestPermissions(PERMISSIONS_REQ,1);
//        forFace = new MakeFragment.Rectangle(view.getContext());
//        likeButton = (Button) view.findViewById(R.id.likeBtn);
//        likeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        downButton = (Button) view.findViewById(R.id.downBtn);
//        downButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                convertViewToBitmap();
//            }
//        });
//        shareButton = (Button) view.findViewById(R.id.shareBtn);
////        emojiImage = (ImageView) view.findViewById(R.id.emojiImage);
////        canvas = new Canvas(emojiImage);
//        viewPager = (ViewPager) view.findViewById(R.id.makeviewPager);
//        list.add(new make_appear());
//        list.add(new make_face());
//        list.add(new make_text());
//
//        stringList.add("Appearance");
//        stringList.add("Face");
//        stringList.add("Text");
//
//        tabLayout = (TabLayout) view.findViewById(R.id.make_tab_layout);
//
//        for(String str: stringList){
//            tabLayout.addTab(tabLayout.newTab().setText(str));
//        }
//        MakeAdapter adapter = new MakeAdapter(getChildFragmentManager(), list,stringList);
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//    }
//
//    public void setMakeText(String str){
////        relativeLayout.LayoutParams ll =
////        relativeLayout.getChildAt(1).invalidate();
////        faceView.invalidate();
//        int count = relativeLayout.getChildCount();
//        System.out.println("现在还有" + count);
//        while(count > 2){
//            relativeLayout.removeViewAt(count - 1);
//            count--;
//        }
//
//
//        this.text = str;
////        relativeLayout.getChildAt(0).invalidate();
//        relativeLayout.addView(new MakeFragment.MakeForString(view.getContext()));
////        System.out.println("现在" + relativeLayout.getChildCount());
////        relativeLayout.getChildAt(3).setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                int action = event.getAction() & MotionEvent.ACTION_MASK;
////                switch (event.getAction()){
////                    case MotionEvent.ACTION_DOWN:
////                        oriLeft = v.getLeft();
////                        oriRight = v.getRight();
////                        oriTop = v.getTop();
////                        oriBottom = v.getBottom();
////                        lastY = (int) event.getRawY();
////                        lastX = (int) event.getRawX();
////                        dragDirection = getDirection(v, (int) event.getX(),
////                                (int) event.getY());
////
//////                        relativeLayout.addView(new Rectangle2(view.getContext()));
//////                        relativeLayout.addView(new);
//////                        relativeLayout
//////                        faceView.scrollTo(lastX, lastY);
//////                        faceView.invalidate();
////                        break;
////
////                    case MotionEvent.ACTION_POINTER_DOWN:
////                        oriLeft = v.getLeft();
////                        oriRight = v.getRight();
////                        oriTop = v.getTop();
////                        oriBottom = v.getBottom();
////                        lastY = (int) event.getRawY();
////                        lastX = (int) event.getRawX();
////                        dragDirection = TOUCH_TWO;
////                        oriDis = distance(event);
//////                        faceView.scrollTo(lastX, lastY);
//////                        faceView.invalidate();
////                        break;
////
////                    case MotionEvent.ACTION_UP:
//////                        myleft = (int) event.getY();
//////                        myright = (int) event.getX();
//////                        faceView.scrollTo(lastX, lastY);
//////                        setFaceImage(position);
//////                        faceView.invalidate();
//////                        relativeLayout.getChildAt(1).invalidate();
//////                        break;
////
////                }
////                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getChildAt(1).getLayoutParams();
////                layoutParams.leftMargin = lastX;
////                layoutParams.topMargin = lastY;
////                relativeLayout.getChildAt(2).setLayoutParams(layoutParams);
////                relativeLayout.getChildAt(2).invalidate();
////                // 处理拖动事件
//////                faceView.scrollTo(lastX, lastY);
//////                faceView.setTranslationX(faceView.getTranslationX());
//////                relativeLayout.getChildAt(1).setLeft((int) event.getX());
//////                relativeLayout.getChildAt(1).setTranslationY(lastY);
//////
//////                myleft = (int) event.getX();
//////                myright = (int) event.getY();
//////                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(myleft, myright);
//////                faceView.setLayoutParams(params);
////
//////                relativeLayout.removeViewAt(1);
//////                faceView = relativeLayout.getChildAt(1);
////                delDrag(v, event, action);
//////                faceView.invalidate();
//////                relativeLayout.getChildAt(1).invalidate();
////                return true;
////            }
////        });
//    }
//
//    public void setImage(int position){
////        int count = relativeLayout.getChildCount();
////        if(count >= 1)
////        relativeLayout.removeAllViews();
//        if(relativeLayout.getChildAt(0) != null){
////            ArrayList<RelativeLayout> insList = new ArrayList<>();
////            for(int i = 0;i < 3;i++){
////
////            }
//            bitmap1 = BitmapFactory.decodeResource(getResources(), appears[position]);
//            relativeLayout.getChildAt(0).invalidate();
//        }
////        System.out.println("the count is" + count);
////        System.out.println("this is" + position);
//        else{
//            bitmap1 = BitmapFactory.decodeResource(getResources(), appears[position]);
//            relativeLayout.addView(forFace);
//        }
//
////        System.out.println(relativeLayout.getChildAt(2));
//    }
//
//    public void setFaceImage(final int position){
////        System.out.println("there are" + relativeLayout.getChildCount());
////        if(relativeLayout.getChildAt(1) != null && relativeLayout.getChildAt(2) != null){
////
////        }
////        if(relativeLayout.getChildAt(1) != null)
////            relativeLayout.removeViewAt(1);
////        if(relativeLayout.getChildAt(1))
//        bitmap2 = BitmapFactory.decodeResource(getResources(), faces[position]);
////        forFace.invalidate();
//        relativeLayout.getChildAt(0).invalidate();
////        forFace.onDraw(canvas);
////        Rectangle rectangle = new Rectangle(view.getContext());
////        relativeLayout.addView(new Rectangle2(view.getContext()));//加入脸部表情
////        faceView = relativeLayout.getChildAt(1);
////        relativeLayout.getChildAt(1).setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                int action = event.getAction() & MotionEvent.ACTION_MASK;
////                switch (event.getAction()){
////                    case MotionEvent.ACTION_DOWN:
////                        oriLeft = v.getLeft();
////                        oriRight = v.getRight();
////                        oriTop = v.getTop();
////                        oriBottom = v.getBottom();
////                        lastY = (int) event.getRawY();
////                        lastX = (int) event.getRawX();
////                        dragDirection = getDirection(v, (int) event.getX(),
////                                (int) event.getY());
////
//////                        relativeLayout.addView(new Rectangle2(view.getContext()));
//////                        relativeLayout.addView(new);
//////                        relativeLayout
//////                        faceView.scrollTo(lastX, lastY);
//////                        faceView.invalidate();
////                        break;
////
////                    case MotionEvent.ACTION_POINTER_DOWN:
////                        oriLeft = v.getLeft();
////                        oriRight = v.getRight();
////                        oriTop = v.getTop();
////                        oriBottom = v.getBottom();
////                        lastY = (int) event.getRawY();
////                        lastX = (int) event.getRawX();
////                        dragDirection = TOUCH_TWO;
////                        oriDis = distance(event);
//////                        faceView.scrollTo(lastX, lastY);
//////                        faceView.invalidate();
////                        break;
////
////                    case MotionEvent.ACTION_UP:
//////                        myleft = (int) event.getY();
//////                        myright = (int) event.getX();
//////                        faceView.scrollTo(lastX, lastY);
//////                        setFaceImage(position);
//////                        faceView.invalidate();
//////                        relativeLayout.getChildAt(1).invalidate();
//////                        break;
////
////                }
////                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) relativeLayout.getChildAt(1).getLayoutParams();
////                layoutParams.leftMargin = lastX;
////                layoutParams.topMargin = lastY;
////                relativeLayout.getChildAt(1).setLayoutParams(layoutParams);
////                relativeLayout.getChildAt(1).invalidate();
////                // 处理拖动事件
//////                faceView.scrollTo(lastX, lastY);
//////                faceView.setTranslationX(faceView.getTranslationX());
//////                relativeLayout.getChildAt(1).setLeft((int) event.getX());
//////                relativeLayout.getChildAt(1).setTranslationY(lastY);
//////
//////                myleft = (int) event.getX();
//////                myright = (int) event.getY();
//////                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(myleft, myright);
//////                faceView.setLayoutParams(params);
////
//////                relativeLayout.removeViewAt(1);
//////                faceView = relativeLayout.getChildAt(1);
////                delDrag(v, event, action);
//////                faceView.invalidate();
//////                relativeLayout.getChildAt(1).invalidate();
////                return true;
////            }
////        });
//    }
//
//    private class Rectangle extends View{
//        Paint paint = new Paint();
//        Paint textPaint = new Paint();//画笔
//        float x = 0;
//        float y = 0;
//        float textx = 10;
//        float texty = 100;
//        public Rectangle(Context context){
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(final Canvas canvas) {
//            Matrix matrix = new Matrix();
//            matrix.postScale(0.3f, 0.3f);
//            System.out.println("我这里运行了");
//            canvas.drawBitmap(bitmap1, 0, 0, null);
//            if(bitmap2 != null){
//                canvas.drawBitmap(bitmap2, x, y, null);
//            }
////            if(text != null){
////                textPaint.setColor(Color.BLACK);
////                textPaint.setTextSize(50);
////                canvas.drawText(text, textx, texty, textPaint);
////
////            }
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_MOVE)
//            {
//
//                x = event.getX();
//                y = event.getY();
////                System.out.println("宽度" + bgWidth);
////                System.out.println("高度" + bgHeight);
////                System.out.println(x);
////                System.out.println(y);
////                if(x + bitmap2.getWidth() / 2 > bgWidth){
////                    x = ;
////                }
////                if(y + bitmap2.getHeight() / 2 > bgHeight){
////                    y = ;
////                }
////                System.out.println(event.getDownTime());
////                textx = event.getX();
////                texty = event.getY();
//                // 重绘
//                invalidate();
//            }
//            return true;
//        }
//
//    }
//
//    private class Rectangle2 extends View{
//        Paint paint = new Paint();
//        float x = 0;
//        float y = 0;
//        float left = 0;
//        float top = 0;
//        float bgWidth = bitmap1.getWidth();
//        float bgHeight = bitmap1.getHeight();
//        public Rectangle2(Context context){
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(final Canvas canvas) {
//            Matrix matrix = new Matrix();
//            matrix.postScale(0.3f, 0.3f);
////            System.out.println("我运行了");
//            canvas.drawBitmap(bitmap2, x, y, null);
//
//
//        }
//
//        @Override
//        public boolean onTouchEvent(MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_MOVE)
//            {
//                x = event.getX();
//                y = event.getY();
////                System.out.println("宽度" + bgWidth);
////                System.out.println("高度" + bgHeight);
////                System.out.println(x);
////                System.out.println(y);
////                if(x + bitmap2.getWidth() / 2 > bgWidth){
////                    x = ;
////                }
////                if(y + bitmap2.getHeight() / 2 > bgHeight){
////                    y = ;
////                }
//                // 重绘
//                invalidate();
//            }
//            return true;
//        }
//    }
//
//    private class MakeForString extends View{
//        Paint paint = new Paint();
//        float x = 10;
//        float y = 100;
//        public MakeForString(Context context){
//            super(context);
//        }
//
//        @Override
//        protected void onDraw(final Canvas canvas) {
//            paint.setColor(Color.BLACK);
////            System.out.println("text length is" + textwidth);
//            paint.setTextSize(50);
////            bitmap3 = getTextImage(bitmap1, text);
//            canvas.drawText(text, x, y, paint);
//        }
//
//        public boolean onTouchEvent(MotionEvent event) {
//            if (event.getAction() == MotionEvent.ACTION_MOVE)
//            {
//                x = event.getX();
//                y = event.getY();
//                // 重绘
//                invalidate();
////                System.out.println("现在有" + relativeLayout.getChildCount());
//            }
//            return true;
//        }
//    }
//
//    //    public static Bitmap getTextImage(Bitmap bitmap, String text){
////        Paint paint = new Paint();
////        paint.setColor(Color.BLACK);
////        paint.setTextSize(20);
////        float bitmapWidth = bitmap.getWidth();
////        float bitmapHeight = bitmap.getHeight();
////
//////        Canvas canvas = new Canvas(bitmap);
//////        canvas.drawBitmap();
////    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
////        View rootView = inflater.inflate(R.layout.make_page, null);
//        super.onActivityCreated(savedInstanceState);
//    }
//
//    /**
//     * 处理拖动事件
//     */
//    protected void delDrag(View v, MotionEvent event, int action) {
//        switch (action) {
//            case MotionEvent.ACTION_MOVE:
//                int dx = (int) event.getRawX() - lastX;
//                int dy = (int) event.getRawY() - lastY;
//                switch (dragDirection) {
//                    case LEFT: // 左边缘
//                        left(v, dx);
//                        break;
//                    case RIGHT: // 右边缘
//                        right(v, dx);
//                        break;
//                    case BOTTOM: // 下边缘
//                        bottom(v, dy);
//                        break;
//                    case TOP: // 上边缘
//                        top(v, dy);
//                        break;
//                    case CENTER: // 点击中心-->>移动
//                        center(v, dx, dy);
//                        break;
//                    case LEFT_BOTTOM: // 左下
//                        left(v, dx);
//                        bottom(v, dy);
//                        break;
//                    case LEFT_TOP: // 左上
//                        left(v, dx);
//                        top(v, dy);
//                        break;
//                    case RIGHT_BOTTOM: // 右下
//                        right(v, dx);
//                        bottom(v, dy);
//                        break;
//                    case RIGHT_TOP: // 右上
//                        right(v, dx);
//                        top(v, dy);
//                        break;
//                    case TOUCH_TWO: //双指操控
//                        float newDist =distance(event);
//                        float scale = newDist / oriDis;
//                        //控制双指缩放的敏感度
//                        int distX = (int) (scale*(oriRight-oriLeft)-(oriRight-oriLeft))/50;
//                        int distY = (int) (scale*(oriBottom-oriTop)-(oriBottom-oriTop))/50;
//                        if (newDist>10f){//当双指的距离大于10时，开始相应处理
//                            left(v, -distX);
//                            top(v, -distY);
//                            right(v, distX);
//                            bottom(v, distY);
//                        }
//                        break;
//
//                }
//                if (dragDirection != CENTER) {
//                    v.layout(oriLeft, oriTop, oriRight, oriBottom);
//                }
//                lastX = (int) event.getRawX();
//                lastY = (int) event.getRawY();
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_POINTER_UP:
//                dragDirection = 0;
//                break;
//        }
//    }
//
//    /**
//     * 触摸点为中心->>移动
//     */
//    private void center(View v, int dx, int dy) {
//        int left = v.getLeft() + dx;
//        int top = v.getTop() + dy;
//        int right = v.getRight() + dx;
//        int bottom = v.getBottom() + dy;
//        if (left < -offset) {
//            left = -offset;
//            right = left + v.getWidth();
//        }
//        if (right > screenWidth + offset) {
//            right = screenWidth + offset;
//            left = right - v.getWidth();
//        }
//        if (top < -offset) {
//            top = -offset;
//            bottom = top + v.getHeight();
//        }
//        if (bottom > screenHeight + offset) {
//            bottom = screenHeight + offset;
//            top = bottom - v.getHeight();
//        }
//        Log.d("raydrag", left+"  "+top+"  "+right+"  "+bottom+"  "+dx);
//        v.layout(left, top, right, bottom);
//    }
//
//    /**
//     * 触摸点为上边缘
//     *
//     * @param v
//     * @param dy
//     */
//    private void top(View v, int dy) {
//        oriTop += dy;
//        if (oriTop < -offset) {
//            //对view边界的处理，如果子view达到父控件的边界，offset代表允许超出父控件多少
//            oriTop = -offset;
//        }
//        if (oriBottom - oriTop - 2 * offset < 200) {
//            oriTop = oriBottom - 2 * offset - 200;
//        }
//    }
//
//    /**
//     * 触摸点为下边缘
//     */
//    private void bottom(View v, int dy) {
//        oriBottom += dy;
//        if (oriBottom > screenHeight + offset) {
//            oriBottom = screenHeight + offset;
//        }
//        if (oriBottom - oriTop - 2 * offset < 200) {
//            oriBottom = 200 + oriTop + 2 * offset;
//        }
//    }
//
//    /**
//     * 触摸点为右边缘
//     */
//    private void right(View v, int dx) {
//        oriRight += dx;
//        if (oriRight > screenWidth + offset) {
//            oriRight = screenWidth + offset;
//        }
//        if (oriRight - oriLeft - 2 * offset < 200) {
//            oriRight = oriLeft + 2 * offset + 200;
//        }
//    }
//
//    /**
//     * 触摸点为左边缘
//     */
//    private void left(View v, int dx) {
//        oriLeft += dx;
//        if (oriLeft < -offset) {
//            oriLeft = -offset;
//        }
//        if (oriRight - oriLeft - 2 * offset < 200) {
//            oriLeft = oriRight - 2 * offset - 200;
//        }
//    }
//
//    /**
//     * 获取触摸点flag
//     */
//    protected int getDirection(View v, int x, int y) {
//        int left = v.getLeft();
//        int right = v.getRight();
//        int bottom = v.getBottom();
//        int top = v.getTop();
//        if (x < touchDistance && y < touchDistance) {
//            return LEFT_TOP;
//        }
//        if (y < touchDistance && right - left - x < touchDistance) {
//            return RIGHT_TOP;
//        }
//        if (x < touchDistance && bottom - top - y < touchDistance) {
//            return LEFT_BOTTOM;
//        }
//        if (right - left - x < touchDistance && bottom - top - y < touchDistance) {
//            return RIGHT_BOTTOM;
//        }
//        if (x < touchDistance) {
//            return LEFT;
//        }
//        if (y < touchDistance) {
//            return TOP;
//        }
//        if (right - left - x < touchDistance) {
//            return RIGHT;
//        }
//        if (bottom - top - y < touchDistance) {
//            return BOTTOM;
//        }
//        return CENTER;
//    }
//
//    /**
//     * 计算两个手指间的距离
//     */
//    private float distance(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return (float) Math.sqrt(x * x + y * y);//两点间距离公式
//    }

}
