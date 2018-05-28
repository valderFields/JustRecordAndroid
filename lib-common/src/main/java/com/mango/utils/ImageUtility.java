package com.mango.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import com.mango.lib_common.R;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class ImageUtility {
    /**
     * 通过view获取bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap getMagicDrawingCache(View view, int aWidth, int aHeight) {
        Bitmap bitmap = (Bitmap) view.getTag(R.id.tag_first);
        Boolean dirty = (Boolean) view.getTag(R.id.tag_second);
        if (view.getWidth() + view.getHeight() == 0) {
            view.measure(aWidth, aHeight);
            view.layout(0, 0, aWidth, aHeight);
        }

        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        if (bitmap == null || bitmap.getWidth() != viewWidth || bitmap.getHeight() != viewHeight) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
            bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.RGB_565);
            view.setTag(R.id.tag_first, bitmap);
            dirty = true;
        }
        if (dirty == true || !true) {
            bitmap.eraseColor(Color.WHITE);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            view.setTag(R.id.tag_second, false);
        }
        return bitmap;
    }
}
