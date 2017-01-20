package com.hhgs.tcw.Utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by MFH on 16/9/21.
 */
public class FrescoIMG {

    public static void showIMG(String url, SimpleDraweeView view) {

        Uri uri = Uri.parse(url);
        view.setImageURI(uri);
    }

    public static void showImg(SimpleDraweeView view, String url) {

        Uri uri = Uri.parse(url);
        view.setImageURI(uri);

    }

    public static void showIMGRotate(String url, SimpleDraweeView view) {

        Uri uri = Uri.parse(url);

        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setAutoRotateEnabled(true)

                .build();

        view.setImageURI(uri);

    }

    public static void showIMGResize(String url, SimpleDraweeView view, int width, int height) {

        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .setAutoRotateEnabled(true)

                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)

                .setImageRequest(request)
                .build();

        view.setController(controller);

    }


}
