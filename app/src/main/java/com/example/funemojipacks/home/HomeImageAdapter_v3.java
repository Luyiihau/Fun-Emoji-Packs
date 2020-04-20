package com.example.funemojipacks.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.funemojipacks.R;

import java.util.ArrayList;

public class HomeImageAdapter_v3 extends BaseAdapter {
    private Context context;
    private ArrayList<byte[]> imageUrls;//图片路径

    public HomeImageAdapter_v3(Context context, ArrayList<byte[]> imageUrls) {
        super();
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //构建静态ViewHolder 复制后面的静态类再用 否则会调用RecyclerView中的ViewHolder
        HomeImageAdapter_v3.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.home_item_layout, null);
            holder = new HomeImageAdapter_v3.ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.home_image_item);
            convertView.setTag(holder);// 如果convertView为空就 把holder赋值进去
        } else {
            holder = (HomeImageAdapter_v3.ViewHolder) convertView.getTag();// 如果convertView不为空，那么就在convertView中getTag()拿出来
        }

//        用Glide读取并加载图片 https://www.youtube.com/watch?v=xMyfY02Bs_M
//        Fragment 嵌套 GridView https://www.jianshu.com/p/56c17ed179fa
        Glide.with(context)
                .load(imageUrls.get(position))
                .into(holder.iv);//改成把图片放进imageview里
        return convertView;
    }


    static class ViewHolder {
        ImageView iv;
    }
}
