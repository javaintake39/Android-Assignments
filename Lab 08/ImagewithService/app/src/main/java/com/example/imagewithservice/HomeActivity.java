package com.example.imagewithservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.imagewithservice.Model.KeyTags;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HomeActivity extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmapImage=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imageView=(ImageView) findViewById(R.id.myimage);
        String state = getIntent().getExtras().getString(KeyTags.receivedkey);
        bitmapImage=loadImageFromStorage(state);
        imageView.setImageBitmap(bitmapImage);
    }
    private Bitmap loadImageFromStorage(String path)
    {
        Bitmap bitmap=null;
        try {
            File file=new File(path, "profile.jpg");
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
          return bitmap;
    }
}
