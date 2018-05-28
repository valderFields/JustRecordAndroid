package com.mango.viewBySelf;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.lib_common.R;


/**
 * Created by DN on 2018/03/12.
 */

public class SearchView extends LinearLayout{
    private EditText edSearch;
    private TextView tvSearch;
    private ImageView ivLeftImg;

    private OnSearchEventListener listener;

    private Context context;
    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
        initEvent();

    }

    private void initView() {
       // LayoutInflater.from(context).inflate(R.layout.view_search, null, false);
        View.inflate(context, R.layout.view_search, this);
        edSearch = findViewById(R.id.et_search);
        ivLeftImg = findViewById(R.id.iv_search);
    }

    private void initEvent(){
        ivLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onLeftClick();
                }
            }
        });
    }



    public interface OnSearchEventListener{
        void onLeftClick();

        void onWordChange(String content);
    }


    public void setOnSearchEventListener(OnSearchEventListener listener){
        this.listener = listener;
    }


}
