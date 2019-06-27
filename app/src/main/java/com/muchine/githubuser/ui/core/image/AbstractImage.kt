package com.muchine.githubuser.ui.core.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kr.co.rememberapp.ui.photo.image.ImageOption
import java.util.concurrent.ExecutionException

abstract class AbstractImage(private var option: ImageOption? = null) {

    fun asBitmap(context: Context): Bitmap? {
        val request = createLoadRequest(Glide.with(context))
        option?.build(request)
        val target = request.submit()

        try {
            return target.get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

        return null
    }

    fun load(context: Context) {
        val request = createLoadRequest(Glide.with(context))
        option?.build(request)
        request.into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {

            }

            override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                option?.onResourceReady?.invoke(bitmap)
            }
        })
    }

    fun render(view: ImageView) {
        view.setImageBitmap(null)

        val request = createRenderRequest(Glide.with(view))
        option?.build(request)
        request
            .downsample(DownsampleStrategy.FIT_CENTER)
            .into(object : BitmapImageViewTarget(view) {
                override fun onResourceReady(bitmap: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(bitmap, transition)
                    option?.onResourceReady?.invoke(bitmap)
                }
            })
    }

    fun clear(view: ImageView) {
        Glide.with(view).clear(view)
    }

    protected abstract fun createLoadRequest(requests: RequestManager): RequestBuilder<Bitmap>

    protected abstract fun createRenderRequest(requests: RequestManager): RequestBuilder<Bitmap>

}