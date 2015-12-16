package com.nice.httpapi.fresco.factory;

import android.net.Uri;
import android.view.View;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by Administrator on 2015/8/4.
 */
public class QSImageRequestFactory {

    public static ImageRequestBuilder createBuilder(String uri) {
        ImageRequestBuilder builder =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setLocalThumbnailPreviewsEnabled(true)
                .setProgressiveRenderingEnabled(true);
        return builder;
    }


    public static ImageRequestBuilder createBuilder(String uri, View view) {
        ImageRequestBuilder builder =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                        .setResizeOptions(
                                new ResizeOptions(view.getLayoutParams().width, view.getLayoutParams().height));
        return builder;
    }

    public static ImageRequestBuilder createBuilder(String uri, int width, int height) {
        ImageRequestBuilder builder =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                        .setResizeOptions(
                                new ResizeOptions(width, height));
        return builder;
    }

}
