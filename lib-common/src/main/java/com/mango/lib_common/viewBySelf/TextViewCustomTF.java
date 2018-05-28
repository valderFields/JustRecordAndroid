package com.mango.lib_common.viewBySelf;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class TextViewCustomTF extends AppCompatTextView {
    public TextViewCustomTF(Context context) {
        super(context);
        init();
    }

    public TextViewCustomTF(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewCustomTF(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public TextViewCustomTF(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        init();
//    }

    public void init(){
        if( !isInEditMode() ){
//			setTypeface(ResManager.getTypefaceTextFont(getContext()));
        }else {
//			try {
//				setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/方正舒体.ttf"));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
        }
    }
}
