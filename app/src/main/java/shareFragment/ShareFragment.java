package shareFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funemojipacks.MainActivity;
import com.example.funemojipacks.R;
import com.yalantis.ucrop.UCrop;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ShareFragment extends Fragment {
    public static ArrayList<ImageModel> storageImages;
    public RecyclerView mRecyclerView;
    public ImageAdapter myAdapter;
    TextView continueText;
    ArrayList<ImageModel> selectedImages = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.share_page, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedState){
        super.onActivityCreated(savedState);
        continueText = getView().findViewById(R.id.share_Continue);
        continueText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedImages.size() > 0){
                    Intent intent = new Intent(getContext(), ShareImageResultActivity.class);
                    intent.putExtra("selectedImages",selectedImages);
                    startActivity(intent);
                    unCheckAllCheckBox();
                    selectedImages.clear();
                }else{
                    openAlertDialog();
                }
            }
        });

        loadImages();

    }

    @Override
    public void onStart(){
        super.onStart();
    }



    @Override
    public void onResume(){
        super.onResume();
        System.out.println("RESUME");
        myAdapter = new ImageAdapter(this.getContext(),this, storageImages);
        mRecyclerView.setAdapter(myAdapter);
    }


    //将系统相册的图片导入到recyclerview
    public void loadImages(){
        storageImages = getImages(this.getContext());
        mRecyclerView = getView().findViewById(R.id.share_recyclerView);
        // Set its Properties using GridLayout with 4 column
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 4));
        // Set RecyclerView Adapter
        myAdapter = new ImageAdapter(this.getContext(),this, storageImages);
        mRecyclerView.setAdapter(myAdapter);
    }

    //将所有checkbox设置为false
    public void unCheckAllCheckBox(){
        for(ImageModel image : storageImages){
            image.setIsChecked(false);
        }
    }


    public static ArrayList<ImageModel> getImages(Context context){
        ArrayList<ImageModel> list = new ArrayList<ImageModel>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,};
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " desc";
        Cursor cursor = contentResolver.query(uri, projection, null, null, sortOrder);
        int iId = cursor.getColumnIndex(MediaStore.Images.Media._ID);
        int iPath = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(iId);
            String path = cursor.getString(iPath);
            ImageModel imageModel = new ImageModel(id,path);
            list.add(imageModel);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
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
                    this.getContext().sendBroadcast(intent);

                    //将edit完的图片新增在gallery里面
                    addImage(new ImageModel(path,path));
                }
        }
    }


    public void addImage(ImageModel imageModel){
        storageImages.add(0, imageModel);
        myAdapter = new ImageAdapter(this.getContext(),this, storageImages);
        mRecyclerView.setAdapter(myAdapter);
    }

    public void setSelectedImages(ArrayList<ImageModel> selectedImages){
        this.selectedImages = selectedImages;
    }

    public void openAlertDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Alert")
                .setMessage("Please select at least one image for sharing")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialoginterface, int i) {
                            }
                        }).show();
    }



}
