package kr.co.rememberapp.ui.photo.image

import android.graphics.Bitmap
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.muchine.githubuser.ui.core.image.transform.RotateTransformation

class ImageOption {

    internal var rotationAngle = 0
    internal var size: Size? = null
    internal var onResourceReady: (bitmap: Bitmap) -> Unit = {}
    internal var isRounded = false

    internal fun build(builder: RequestBuilder<Bitmap>) {
        applyRotation(builder)
        applySize(builder)
        applyRounded(builder)
    }

    private fun applyRotation(builder: RequestBuilder<Bitmap>) {
        // 비트맵에 아무 영향을 주지 않는 Transformation이라 하더라도(예: rotation angle이 0) 성능에 영향을 줄 수 있다.
        // 따라서, 꼭 필요한 경우에만 적용하도록 한다.
        if (rotationAngle == 0) return
        builder.transform(RotateTransformation(rotationAngle))
    }

    private fun applySize(builder: RequestBuilder<Bitmap>) {
        val size = this.size ?: return
        builder.apply(RequestOptions().override(size.width, size.height))
    }

    private fun applyRounded(builder: RequestBuilder<Bitmap>) {
        builder.apply(RequestOptions.circleCropTransform())
    }

    class Size(var width: Int, var height: Int)

}
