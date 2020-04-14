package shareFragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.funemojipacks.R;

public class ResultImageHolder extends RecyclerView.ViewHolder {
    RelativeLayout imageRelative;
    ImageView image;
    public ResultImageHolder(View itemView) {
        super(itemView);
        this.imageRelative = itemView.findViewById(R.id.image_result_relative);
        this.image = itemView.findViewById((R.id.card_result_image));
    }

}
