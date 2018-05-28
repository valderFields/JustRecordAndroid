package com.mango.lib_common.viewBySelf;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class EditTextCustomTF extends EditText {
    public EditTextCustomTF(Context context) {
        super(context);
        init();
    }

    public EditTextCustomTF(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextCustomTF(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditTextCustomTF(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

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
