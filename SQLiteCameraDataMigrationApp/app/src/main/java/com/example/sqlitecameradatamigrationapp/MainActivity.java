package com.example.sqlitecameradatamigrationapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.graphics.Color.rgb;
import static android.media.MediaRecorder.VideoSource.CAMERA;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    static final int IMAGE_RESULT = 2;
    static final int MY_PERMISSION_READ_EXTERNAL_STORAGE = 3;

    String path = "";

    public void setImagePath(String url) {
        path = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        MaterialButton submitBtn = (MaterialButton) findViewById(R.id.submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextInputEditText name = (TextInputEditText) findViewById(R.id.name);
                final TextInputEditText phone = (TextInputEditText) findViewById(R.id.phone);
                final TextInputEditText email = (TextInputEditText) findViewById(R.id.email);
                final TextInputEditText address = (TextInputEditText) findViewById(R.id.address);
                ImageView imageView = (ImageView) findViewById(R.id.iv);

                if (name.getText().toString().equals("")) {

                    name.setFocusable(true);
                    name.setHint("Enter your name");
                    name.setHintTextColor(rgb(255, 0, 0));
                } else if (phone.getText().toString().equals("") || phone.length() != 10) {
                    phone.setFocusable(true);
                    phone.setHint("Enter valid phone number");
                    phone.setHintTextColor(rgb(255, 0, 0));
                } else if (email.getText().toString().equals("")) {
                    phone.setFocusable(true);
                    phone.setHint("Enter valid email");
                    phone.setHintTextColor(rgb(255, 0, 0));
                } else if (address.getText().toString().equals("")) {
                    phone.setFocusable(true);
                    phone.setHint("Enter Address");
                    phone.setHintTextColor(rgb(255, 0, 0));
                } else {
                    Intent intent=new Intent(MainActivity.this,ViewAllUsers.class);
                    String myName = name.getText().toString();
                    String myPhone = phone.getText().toString();
                    String myEmail = email.getText().toString();
                    String myAddress = address.getText().toString();
                    Log.d("MMM","\t"+path);
                    String myImage = path;
                    intent.putExtra("NAME", myName);
                    intent.putExtra("NUMBER", myPhone);
                    intent.putExtra("EMAIL", myEmail);
                    intent.putExtra("ADDRESS", myAddress);
                    intent.putExtra("IMAGE", myImage);
                    Log.d("after intent","\t"+myImage);
                    FeedEntryDao feedDao = new FeedEntryDao();
                    feedDao.insert(myName, myPhone, myEmail, myAddress,myImage);
                    startActivity(intent);
                }

            }
        });
        MaterialButton viewBtn = (MaterialButton) findViewById(R.id.viewlist);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView imageView = (ImageView) findViewById(R.id.iv);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialogue();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    photoFromGallery();
                } else {
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            ImageView imageView = (ImageView) findViewById(R.id.iv);
            imageView.setImageURI(Uri.parse(new File(path).getAbsolutePath()));

        } else if (requestCode == IMAGE_RESULT && resultCode == RESULT_OK) {
            Uri URI = data.getData();
            String[] FILE = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(FILE[0]);
            String ImageDecoder = cursor.getString(columnIndex);

            ImageView imageView = (ImageView) findViewById(R.id.iv);
            cursor.close();
            imageView.setImageBitmap(BitmapFactory.decodeFile(ImageDecoder));

        } else {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void showOptionDialogue() {

        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select option to upload the picture");
        String[] pictureDialogItems = {"Gallery", "Camera"};
        pictureDialog.setItems(pictureDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        photoFromGallery();
                        break;
                    case 1:
                        try {
                            photoFromCamera();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }

            }
        });
        pictureDialog.show();
    }

    public void photoFromGallery() {

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission not granted", Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_READ_EXTERNAL_STORAGE);

        } else {
            ImageView imageView = (ImageView) findViewById(R.id.iv);
            Intent choosePictureIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(choosePictureIntent, IMAGE_RESULT);

        }
    }

    public void photoFromCamera() throws IOException {


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (photoFile != null) {

                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.sqlitecameradatamigrationapp.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }

    }

//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
//        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//
//        if (!wallpaperDirectory.exists()) {
//            wallpaperDirectory.mkdirs();
//        }
//        try {
//            File f = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(byteArrayOutputStream.toByteArray());
//            MediaScannerConnection.scanFile(this, new String[]{f.getPath()}, new String[]{"image/jpeg"}, null);
//            fo.close();
//            setImagePath(f.getAbsolutePath());
//            return f.getAbsolutePath();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", dir);
        path = image.getAbsolutePath();
        Log.d("MMM2","\t"+path);
        setImagePath(path);
        return image;
    }

}
