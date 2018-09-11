package com.example.administrator.llc_p.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.llc_p.R;
import com.example.administrator.llc_p.utils.ImageUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationActivity extends BaseActivity {

    private ImageView im_card_front,im_card_reverse;
    private File file;
    private int  i = 0;
    // 拍照回传码
    public final static int CAMERA_REQUEST_CODE = 0;
    // 相册选择回传码
    public final static int GALLERY_REQUEST_CODE = 1;
    private Uri imageUri7,imageUri;
    private final static int  CHOOSE_PHOTO=3;
    private final static int  TAKE_PHOTO=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        RelativeLayout status = findViewById(R.id.status);
        setStatusBarHeight(status);
        im_card_front = findViewById(R.id.im_card_front);
        im_card_reverse = findViewById(R.id.im_card_reverse);
        im_card_front.setOnClickListener(this);
        im_card_reverse.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.im_card_front:
                showPopueWindow();
                 i = 1;
                break;
            case R.id.im_card_reverse:
                showPopueWindow();
                i=2;
                break;
        }
    }

    private void showPopueWindow(){
        View popView = View.inflate(this,R.layout.popuwindow,null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
  //      popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册
                getPermission(CHOOSE_PHOTO,GALLERY_REQUEST_CODE);
                popupWindow.dismiss();


            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //打开相机
                getPermission(TAKE_PHOTO,CAMERA_REQUEST_CODE);
                popupWindow.dismiss();


            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);

    }




    /**
     * 使用相机
     */
    private void useCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //将拍摄的照片存储到apc_photo文件夹
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/apc_photo/" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
            imageUri7 = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
            //添加权限
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri7);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }else {
            Intent getPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //将拍摄的照片存储到apc_photo文件夹
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/apc_photo/" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
             imageUri = Uri.fromFile(file);
            getPhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//根据uri保存照片
            startActivityForResult(getPhoto,CAMERA_REQUEST_CODE);//启动相机拍照
        }
    }

    /**
     * 使用手机本地图片
     */
    private void choosePhoto(){
        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/png");
        startActivityForResult(intentToPickPic, GALLERY_REQUEST_CODE);
    }

    public void getPermission(int type,int pType){
        PackageManager pm = getPackageManager();
        boolean b = (PackageManager.PERMISSION_GRANTED==pm.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE",getPackageName()));
        boolean camera = (PackageManager.PERMISSION_GRANTED==pm.checkPermission("android.permission.CAMERA",getPackageName()));
         if (b&&camera){
            //权限已经被授予，在这里直接写要执行的相应方法即可
            if (type==CHOOSE_PHOTO){
                choosePhoto();

            }if (type==TAKE_PHOTO){
                useCamera();
            }

        }
        else {
            String [] permission= new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(AuthenticationActivity.this,permission,pType);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == GALLERY_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //权限获取后操作

                choosePhoto();
               // Toast.makeText(AuthenticationActivity.this, "Permission is open", Toast.LENGTH_SHORT).show();
            } else
            {
                // Permission Denied
                Toast.makeText(AuthenticationActivity.this, "已拒绝权限，请重新申请", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //权限获取后操作

                useCamera();
            } else
            {
                // Permission Denied
                Toast.makeText(AuthenticationActivity.this, "已拒绝相机权限，请重新申请", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (resultCode==AuthenticationActivity.RESULT_OK){
                switch (requestCode){
                    //拍照后的照片
                    case CAMERA_REQUEST_CODE:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            try {
                                Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri7));
                                String s = bitmapToString(bit);
                                Log.d("BITMAP", "图片地址: "+s);

                                if (i==1){
                                    im_card_front.setImageBitmap(bit);

                                }else {
                                    im_card_reverse.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                                if (i==1){
                                    im_card_front.setImageBitmap(bit);

                                }else {
                                    im_card_reverse.setImageBitmap(bit);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                        break;
                        //相册选择的照片
                    case GALLERY_REQUEST_CODE:
                        Uri data1 = data.getData();
                        if (data1!=null){
                            try {
                                String realFilePath = getRealFilePath(this, data1);
                                Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(data1));
                                String s = bitmapToString(bit);
                                if (i==1){
                                    im_card_front.setImageBitmap(bit);

                                }else {
                                    im_card_reverse.setImageBitmap(bit);
                                }

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public  File saveBitmapFile(Bitmap bitmap, String filepath){
        File file=new File(filepath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    //把bitmap转换成字符串
    public static String bitmapToString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream btString = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, btString);
        byte[] bytes = btString.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }


    public  String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


}
