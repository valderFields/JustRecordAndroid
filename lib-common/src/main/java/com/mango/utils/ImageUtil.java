package com.mango.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class ImageUtil {

    /**
     * 保存头像到本地文件夹
     * @param bitmap
     * @param imageName  图片名称
     * @return
     */
    public static String saveImage(Bitmap bitmap, String imageName) {
        String sdcardPath = Environment.getExternalStorageDirectory().toString();
        File imageDir = new File(sdcardPath + File.separator + "zhong" + File.separator + "image");
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        File document = new File(imageDir, imageName + ".jpg");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(document);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (out != null) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document.toString();
    }
}
