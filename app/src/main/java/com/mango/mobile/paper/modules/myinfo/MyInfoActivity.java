package com.mango.mobile.paper.modules.myinfo;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.zhouwei.library.CustomPopWindow;
import com.mango.lib_common.base.BaseActivity;
import com.mango.lib_common.entity.User;
import com.mango.lib_common.utils.GalleryUtil;
import com.mango.lib_common.utils.Helper;
import com.mango.lib_common.utils.ImageUtil;
import com.mango.lib_common.utils.PermissionUtility;
import com.mango.lib_common.viewBySelf.ItemBar;
import com.mango.lib_common.viewBySelf.TextViewCustomTF;
import com.mango.lib_common.viewBySelf.TopBar;
import com.mango.mobile.paper.BuildConfig;
import com.mango.mobile.paper.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.topbar)
    TopBar topBar;
    @BindView(R.id.iv_head)
    ItemBar ivHead;
    @BindView(R.id.iv_nick)
    ItemBar ivNick;
    @BindView(R.id.iv_sex)
    ItemBar ivSex;
    @BindView(R.id.iv_phone)
    ItemBar ivPhone;
    @BindView(R.id.iv_email)
    ItemBar ivEmail;
    //    @BindView(R.id.iv_address)
//    ItemBar ivAddress;
    @BindView(R.id.iv_card)
    ItemBar ivCard;
    @BindView(R.id.iv_identity)
    ItemBar ivIdentity;
    @BindView(R.id.iv_pay)
    ItemBar ivPay;
    @BindView(R.id.ll_layout)
    LinearLayout llLayout;

    //头像popwindow
    View contentViewPhoto, contentViewSex;
    TextViewCustomTF tvTakePhoto;
    TextViewCustomTF tvGallery;
    TextViewCustomTF tvCancel;
    TextViewCustomTF tvMan;
    TextViewCustomTF tvWoman;
    TextViewCustomTF tvCancel1;

    private CustomPopWindow popPhoto;
    private CustomPopWindow popSex;
    private Uri imageUri;

    private User user = new User();

    public static final int REQUEST_ALBUM = 100;
    public static final int REQUEST_CAPTURE = 200;
    public static final int REQUEST_NICK= 300;
    public static final int REQUEST_PHONE = 400;
    public static final int REQUEST_EMAIL = 500;

    public static final int TYPE_NICK = 0;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_EMAIL = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        topBar.setTitle("我的信息");
        topBar.setOnTopClickListener(new TopBar.OnTopClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
    }


    @OnClick({R.id.iv_head, R.id.iv_nick, R.id.iv_sex, R.id.iv_phone, R.id.iv_email, R.id.iv_card, R.id.iv_identity, R.id.iv_pay})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                setHeadImg();
                break;
            case R.id.iv_nick:
                changeNick();
                break;
            case R.id.iv_sex:
                setSex();
                break;
            case R.id.iv_phone:
                setPhone();
                break;
            case R.id.iv_email:
                setEmail();
                break;
            case R.id.iv_card:
                startActivity(new Intent(this, CardActivity.class));
                break;
            case R.id.iv_identity:
                startActivity(new Intent(this, IdentityActivity.class));
                break;
            case R.id.iv_pay:
                break;
        }
    }


    private void changeNick() {
        Intent intent = new Intent(MyInfoActivity.this, ChangeDetailsActivity.class);
        intent.putExtra("nick", "哈士奇");
        intent.putExtra("type", TYPE_NICK);
        startActivityForResult(intent,REQUEST_NICK);
    }

    private void setSex() {
        if (contentViewSex == null) {
            contentViewSex = LayoutInflater.from(this).inflate(R.layout.pop_sex_menu, null);
            tvMan = contentViewSex.findViewById(R.id.tv_man);
            tvWoman = contentViewSex.findViewById(R.id.tv_woman);
            tvCancel1 = contentViewSex.findViewById(R.id.tv_cancel);
        }

        popSex = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentViewSex)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .enableBackgroundDark(true)
                .create()
                .showAtLocation(llLayout, Gravity.BOTTOM, 0, 0);

        tvMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSex.dissmiss();
                ivSex.setRightText("男");
                Helper.showToast("性别修改成功");
            }
        });

        tvWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSex.dissmiss();
                ivSex.setRightText("女");
                Helper.showToast("性别修改成功");
            }
        });

        tvCancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popSex.dissmiss();
            }
        });
    }

    private void setPhone() {
        Intent intent = new Intent(this, ChangeDetailsActivity.class);
        intent.putExtra("type", TYPE_PHONE);
        startActivity(intent);
    }

    private void setEmail() {
        Intent intent = new Intent(MyInfoActivity.this, ChangeDetailsActivity.class);
        intent.putExtra("email", "哈士奇");
        intent.putExtra("type", TYPE_EMAIL);
        startActivityForResult(intent,REQUEST_EMAIL);
    }

    private void setHeadImg() {
        if (contentViewPhoto == null) {
            contentViewPhoto = LayoutInflater.from(this).inflate(R.layout.pop_photo_menu, null);
            tvTakePhoto = contentViewPhoto.findViewById(R.id.tv_take_photo);
            tvGallery = contentViewPhoto.findViewById(R.id.tv_gallery);
            tvCancel = contentViewPhoto.findViewById(R.id.tv_cancel);
        }

        popPhoto = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentViewPhoto)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setOutsideTouchable(true)
                .enableBackgroundDark(true)
                .create()
                .showAtLocation(llLayout, Gravity.BOTTOM, 0, 0);

        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePictureFromCamera();
                popPhoto.dissmiss();
            }
        });

        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtility.getRxPermission(MyInfoActivity.this)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE) //申请读权限
                        .subscribe(new Consumer<Boolean>() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (granted) {
                                    takePictureFromAlum();
                                    popPhoto.dissmiss();
                                } else {
                                    Helper.showToast("请开读写权限");
                                }
                            }
                        });
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popPhoto.dissmiss();
            }
        });
    }


    /**
     * 使用隐式意图打开系统相册
     */
    private void takePictureFromAlum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            startActivityForResult(intent, REQUEST_ALBUM);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_CAPTURE: // 拍照
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    ivHead.setRightIcon(new BitmapDrawable(bitmap));
                    saveHeadImage(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case REQUEST_ALBUM:  //相册
                String path = GalleryUtil.getPath(MyInfoActivity.this, data.getData());
                Bitmap bitmap=BitmapFactory.decodeFile(path);
                ivHead.setRightIcon(new BitmapDrawable(bitmap));
                saveHeadImage(bitmap);
                break;
            case REQUEST_NICK:
                String nick = data.getStringExtra("nick");
                ivNick.setRightText(nick);
                user.setNick(nick);
                break;
            case REQUEST_EMAIL:
                String email = data.getStringExtra("email");
                ivEmail.setRightText(email);
                user.setNick(email);
                break;
            case REQUEST_PHONE:
                String phone = data.getStringExtra("phone");
                ivPhone.setRightText(phone);
                user.setNick(phone);
                break;
        }
    }


    private void saveHeadImage(final Bitmap bitmap) {
        PermissionUtility.getRxPermission(MyInfoActivity.this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE) //申请拍照权限
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            String imgPath = ImageUtil.saveImage(bitmap, "head_image");
                            if (!imgPath.equals("")) {
                                user.setPortrait(imgPath);
                            }
                        } else {
                            Helper.showToast("请开启读写权限");
                        }
                    }
                });
    }


    /**
     * 拍照获取图片
     */
    private void takePictureFromCamera() {
        String pictureName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date()) + "-" + System.currentTimeMillis() + ".jpg";
        File mOutputImage = new File(getExternalCacheDir(), pictureName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", mOutputImage);
            Log.e("zhou", imageUri.getPath());
        } else {
            imageUri = Uri.fromFile(mOutputImage);
        }
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            PermissionUtility.getRxPermission(MyInfoActivity.this)
                    .request(Manifest.permission.CAMERA) //申请拍照权限
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (granted) {
                                startActivityForResult(intent, REQUEST_CAPTURE);
                            } else {
                                Helper.showToast("请开启拍照权限");
                            }
                        }
                    });
        }
    }


    /**
     * 更新数据库和服务器数据
     */
    private void upData(){
        //      DbManager.getDaoSession(this).getUserDao().update(user);
    }


}
