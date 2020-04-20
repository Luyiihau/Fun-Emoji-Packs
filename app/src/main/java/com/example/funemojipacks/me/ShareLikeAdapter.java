package com.example.funemojipacks.me;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.funemojipacks.R;

import java.util.ArrayList;

public class ShareLikeAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Bitmap> images;
    private ImageView mImageView;

    public ShareLikeAdapter(Context context, ArrayList<Bitmap> images){
        super();
        this.context = context;
        this.images = images;
        System.out.println("The length!");
        System.out.println(images.size());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // mImageView = (ImageView) findViewById(R.id.meTabLayout);

        System.out.println("What Happened Here!");
        System.out.println(images.get(0));

        //构建静态ViewHolder 复制后面的静态类再用 否则会调用RecyclerView中的ViewHolder
        ShareLikeAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.me_img_item, null);
            holder = new ShareLikeAdapter.ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.me_show_image_item);
            convertView.setTag(holder);// 如果convertView为空就 把holder赋值进去
        } else {
            holder = (ShareLikeAdapter.ViewHolder) convertView.getTag();
        }

        //用Glide读取并加载图片 https://www.youtube.com/watch?v=xMyfY02Bs_M
        //Fragment 嵌套 GridView https://www.jianshu.com/p/56c17ed179fa
        Glide
                .with(context)
                .load(images.get(position))
                .into(holder.iv);

        return convertView;
    }

    static class ViewHolder{
        ImageView iv;
    }


}
