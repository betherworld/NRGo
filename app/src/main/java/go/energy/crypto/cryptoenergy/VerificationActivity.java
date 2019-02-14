package go.energy.crypto.cryptoenergy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

public class VerificationActivity extends AppCompatActivity {
    static final int RESULT_LOAD_IMAGE = 1;
    static final int PERMISSIONS_REQUEST_READ_STORAGE = 2;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_camera);
        Button cam_button = (Button)findViewById(R.id.camera_button);
        cam_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        mImageView = (ImageView)findViewById(R.id.picture_taken);
    }

    private void dispatchTakePictureIntent() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.READ_EXTERNAL_STORAGE );
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_STORAGE);
            }
        } else {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Intent chooser = new Intent(Intent.ACTION_CHOOSER);
            chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent);
            chooser.putExtra(Intent.EXTRA_TITLE, getString(R.string.choose_camera_or_file));
            Intent[] intentArray = {cameraIntent};
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
            startActivityForResult(chooser, RESULT_LOAD_IMAGE);
        }
    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // todo use appropriate resultCode in your case
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == FragmentActivity.RESULT_OK) {
            if (data.getData() != null) {
                // this case will occur in case of picking image from the Gallery,
                // but not when taking picture with a camera
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    // do whatever you want with the Bitmap ....

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // this case will occur when taking a picture with a camera
                Bitmap bitmap = null;
                Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED,
                                MediaStore.Images.ImageColumns.ORIENTATION}, MediaStore.Images.Media.DATE_ADDED,
                        null, "date_added DESC");
                if (cursor != null && cursor.moveToFirst()) {
                    Uri uri = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                    String photoPath = uri.toString();
                    cursor.close();
                    if (photoPath != null) {
                        bitmap = BitmapFactory.decodeFile(photoPath);
                    }
                }

                if (bitmap == null) {
                    // for safety reasons you can
                    // use thumbnail if not retrieved full sized image
                    bitmap = (Bitmap) data.getExtras().get("data");
                }
                mImageView.setImageBitmap(bitmap);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
