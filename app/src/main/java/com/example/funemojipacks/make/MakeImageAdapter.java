package com.example.funemojipacks.make;

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

public class MakeImageAdapter extends BaseAdapter {

    private Context context;
    private int[] imageUrls;

    public MakeImageAdapter(Context context, int[] imageUrls){
        super();
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.length;
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
        MakeImageAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.make_item_layout, null);
            holder = new MakeImageAdapter.ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.make_face_image_item);
            convertView.setTag(holder);// 如果convertView为空就 把holder赋值进去
        } else {
            holder = (MakeImageAdapter.ViewHolder) convertView.getTag();// 如果convertView不为空，那么就在convertView中getTag()拿出来
        }

        //用Glide读取并加载图片 https://www.youtube.com/watch?v=xMyfY02Bs_M
        //Fragment 嵌套 GridView https://www.jianshu.com/p/56c17ed179fa
        Glide.with(context)
                .load(imageUrls[position])
                .into(holder.iv);//改成把图片放进imageview里

        return convertView;
    }

    static class ViewHolder{
        ImageView iv;
    }
}
