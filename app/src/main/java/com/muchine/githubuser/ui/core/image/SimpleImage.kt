package kr.co.rememberapp.ui.photo.image

import android.graphics.Bitmap
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.muchine.githubuser.ui.core.image.AbstractImage

/**
 * 가장 단순하게 이미지를 렌더링하는 클래스
 * path: 이미지의 HTTP URL 또는 File 경로
 */
open class SimpleImage(
    private val path: String,
    option: ImageOption? = null
) : AbstractImage(option) {

    override fun createLoadRequest(requests: RequestManager): RequestBuilder<Bitmap> {
        return requests.asBitmap().load(this.path)
    }

    override fun createRenderRequest(requests: RequestManager): RequestBuilder<Bitmap> {
        return createLoadRequest(requests)
    }
}
