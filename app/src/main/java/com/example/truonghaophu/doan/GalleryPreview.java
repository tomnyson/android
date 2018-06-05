package com.example.truonghaophu.doan;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class GalleryPreview extends AppCompatActivity {
    Toolbar toolbar;

    ImageView GalleryPreviewImg;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_preview);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        GalleryPreviewImg = (ImageView) findViewById(R.id.GalleryPreviewImg);
        Glide.with(GalleryPreview.this)
                .load(new File(path)) // Uri of the picture
                .into(GalleryPreviewImg);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu1) {
           shareImage(path);

            return true;
        }
        if(id == R.id.menu2) {

            Intent intent= new Intent(getApplicationContext(), slideshowActivity.class);
            //intent.putExtra("data", value); //pass data
            Toast.makeText(getApplicationContext(),"Show image",Toast.LENGTH_SHORT).show();
            startActivity(intent);


            return true;
        }
        if(id == R.id.menu3) {

            Intent intent= new Intent(getApplicationContext(), BasicMapDemoActivity.class);
            //intent.putExtra("data", value); //pass data
            Toast.makeText(getApplicationContext(),"show map",Toast.LENGTH_SHORT).show();
            startActivity(intent);


            return true;
        }
        if(id == R.id.menu4) {


            Bitmap bitmap = BitmapFactory.decodeFile(path);
            setWallPaper(bitmap);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareImage(String imagePath) {
        Intent share = new Intent(Intent.ACTION_SEND);

        // If you want to share a png image only, you can do:
        // setType("image/png"); OR for jpeg: setType("image/jpeg");
        share.setType("image/*");

        // Make sure you put example png image named myImage.png in your
        // directory

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);

        startActivity(Intent.createChooser(share, "Share Image!"));
    }

    public  void setWallPaper(Bitmap bmpImg) {
        WallpaperManager wallManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            wallManager.setBitmap(bmpImg);
            Toast.makeText(getApplicationContext(), "Wallpaper Set Successfully!!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Setting WallPaper Failed!!", Toast.LENGTH_SHORT).show();
        }
    }
}
