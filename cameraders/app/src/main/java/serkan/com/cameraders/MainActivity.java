package serkan.com.cameraders;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView Resim;
    public static final int CAMERA_REQUEST=1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resim=(ImageView)findViewById(R.id.imageView);
    }

    public void cameraStart(View view){

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){

            final Bitmap photo=(Bitmap)data.getExtras().get("data");
            Resim.setImageBitmap(photo);

            AlertDialog.Builder builder=new AlertDialog.Builder(this);

            builder.setTitle("Onayla");
            builder.setMessage("Duvar Kağıdı Yapmak İstiyormusunuz?");


            builder.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        WallpaperManager mypaper = WallpaperManager.getInstance(getApplicationContext());
                        mypaper.setBitmap(photo);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    dialog.dismiss();

                }
            });

            builder.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });

            AlertDialog alert=builder.create();
            alert.show();



            }


        }

    }

