package com.mango.mobile.paper.modules.TimeLinePager.addRecord;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;

import com.mango.base.BaseMvpActivity;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.modules.myinfo.MyInfoActivity;
import com.mango.utils.GalleryUtil;
import com.mango.utils.Helper;
import com.mango.utils.PermissionUtility;
import com.mango.viewBySelf.EditTextCustomTF;
import com.mango.viewBySelf.MoreResourceEditText;
import com.mango.viewBySelf.style.ClickSpan;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class AddWordActivity extends BaseMvpActivity<AddWordPresenter, AddWordModel> implements AddWordContract.View {

    @BindView(R.id.et_title)
    MoreResourceEditText etTitle;
    @BindView(R.id.btn_add)
    Button btnAdd;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_add_word;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        etTitle.setEnabled(true);
        //etTitle.setFocusable(true);//可以通过键盘得到焦点
        etTitle.setFocusableInTouchMode(true);//可以通过触摸得到焦点
//        SpannableString span = new SpannableString(etTitle.getContentList().toString());
//        ClickSpan clickSpan = new ClickSpan(path);
//        span.setSpan(clickSpan, "",
//                txt.indexOf(to) + to.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        etTitle.setText(span);


        etTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PermissionUtility.getRxPermission(AddWordActivity.this)
                        .request(Manifest.permission.READ_EXTERNAL_STORAGE) //申请读权限
                        .subscribe(new Consumer<Boolean>() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void accept(Boolean granted) throws Exception {
                                if (granted) {
                                    takePictureFromAlum();
                                } else {
                                    Helper.showToast("请开读写权限");
                                }
                            }
                        });
            }
        });



    }


    @Override
    public void upDate() {

    }

    /**
     * 使用隐式意图打开系统相册
     */
    private void takePictureFromAlum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        if (componentName != null) {
            startActivityForResult(intent, 500);
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
            case 500:  //相册
                String path = GalleryUtil.getPath(this, data.getData());
               etTitle.insertBitmap(path);
                break;
        }
    }
}
