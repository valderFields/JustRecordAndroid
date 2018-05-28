package com.mango.mobile.paper.common.utils;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

import com.mango.utils.Helper;
import com.mango.utils.ImageUtility;
import com.mango.utils.PermissionUtility;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class ShareUtils {


    private static UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Helper.showToast("成功了");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Helper.showToast("失败了");
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Helper.showToast("取消了");
        }
    };
    /**
     * 分享用view合成的image
     *
     * @param platform
     * @param aty
     * @param shareView
     */
    public static void shareMixImage(final SHARE_MEDIA platform, final Activity aty, final View shareView) {
        PermissionUtility.getRxPermission(aty)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE) //申请读写权限
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            Bitmap bitmap = ImageUtility.getMagicDrawingCache(shareView, shareView.getWidth(), shareView.getHeight());
                            Bitmap bitmapThumb = ImageUtility.getMagicDrawingCache(shareView, Helper.dip2px(125), Helper.dip2px(222));

                            UMImage umImage = new UMImage(aty, bitmap);
                            UMImage thumb = new UMImage(aty, bitmapThumb);

                            //umImage.compressStyle = UMImage.CompressStyle.QUALITY;
                            umImage.setThumb(thumb);

                            new ShareAction(aty)
                                    .setPlatform(platform)
                                    .withText("")
                                    .withMedia(umImage)
                                    .setCallback(shareListener)
                                    .share();
                        } else {
                            Helper.showToast("请开启读写权限");
                        }
                    }
                });
    }
}
