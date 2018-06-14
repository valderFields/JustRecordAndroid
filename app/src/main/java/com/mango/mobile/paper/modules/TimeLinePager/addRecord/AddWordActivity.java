package com.mango.mobile.paper.modules.TimeLinePager.addRecord;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
import com.mango.viewBySelf.ScrollEditText;
import com.mango.viewBySelf.style.ClickSpan;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class AddWordActivity extends BaseMvpActivity<AddWordPresenter, AddWordModel> implements AddWordContract.View {

    @BindView(R.id.et_title)
    EditTextCustomTF etTitle;
    @BindView(R.id.et_content)
    MoreResourceEditText etContent;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_look)
    Button btnLook;
    @BindView(R.id.scrollview)
    ScrollView scrollView;

    private int editTextHeight;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_add_word;
    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ButterKnife.bind(this);

        etContent.setEnabled(true);
        //etTitle.setFocusable(true);//可以通过键盘得到焦点
        etContent.setFocusableInTouchMode(true);//可以通过触摸得到焦点

        initEvent();

    }


    private void initEvent() {

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

        btnLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.showToast(etContent.getText().toString());
            }
        });


        SoftKeyBoardListener.setListener(this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                editTextHeight = height;
                RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                layoutParams1.height = height + Helper.dip2px(50);
                scrollView.setLayoutParams(layoutParams1);
                Helper.showToast(height + "");

            }

            @Override
            public void keyBoardHide(int height) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
                layoutParams.height = Helper.getDisplayHeight(AddWordActivity.this);
                scrollView.setLayoutParams(layoutParams);

            }
        });


        etContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
                if ((view.getId() == R.id.et_title && canVerticalScroll(etContent))) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);//告诉父view，我的事件自己处理
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);//告诉父view，你可以处理了
                    }
                }
                return false;
            }
        });

    }


    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - editTextHeight;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
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
                etContent.insertBitmap(path);
                break;
        }
    }
}



