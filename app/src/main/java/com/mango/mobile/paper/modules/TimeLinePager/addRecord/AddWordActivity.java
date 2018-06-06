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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

import com.mango.base.BaseMvpActivity;
import com.mango.mobile.paper.R;
import com.mango.mobile.paper.common.utils.SoftKeyBoardListener;
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
    @BindView(R.id.sv_record)
    ScrollView svRecord;
    @BindView(R.id.ll_record)
    RelativeLayout llRecord;

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

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) svRecord.getLayoutParams();
        layoutParams.height =Helper.getDisplayHeight(this);
        svRecord.setLayoutParams(layoutParams);
        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) llRecord.getLayoutParams();
        layoutParams1.height = Helper.getDisplayHeight(this) - Helper.dip2px(110) - Helper.getStatusBarHeight();
       // layoutParams1.gravity = Gravity.BOTTOM;
        llRecord.setLayoutParams(layoutParams1);
        initEvent();

    }


    private void initEvent(){


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



        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Helper.showToast(height + "");
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) svRecord.getLayoutParams();
                layoutParams.height = height + Helper.dip2px(50);
                svRecord.setLayoutParams(layoutParams);

                FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) llRecord.getLayoutParams();
                layoutParams1.height = height;
                llRecord.setLayoutParams(layoutParams1);


                //TODO  scrollview 与 editText 的滑动事件冲突
            }

            @Override
            public void keyBoardHide(int height) {
                Helper.showToast(height + "隐藏");
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) svRecord.getLayoutParams();
                layoutParams.height =Helper.getDisplayHeight(AddWordActivity.this);
                svRecord.setLayoutParams(layoutParams);
                FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) llRecord.getLayoutParams();
                layoutParams1.height = Helper.getDisplayHeight(AddWordActivity.this) - Helper.dip2px(110) - Helper.getStatusBarHeight();
                llRecord.setLayoutParams(layoutParams1);
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
