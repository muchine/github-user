package kr.co.rememberapp.ui.photo.image

import android.graphics.Bitmap
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.muchine.githubuser.ui.core.image.AbstractImage

/**
 * resource: 로컬 이미지 리소스 ID
 */
class LocalResourceImage
@JvmOverloads constructor(private val resource: Int, option: ImageOption? = null)
    : AbstractImage(option) {

    override fun createLoadRequest(requests: RequestManager): RequestBuilder<Bitmap> {
        return requests.asBitmap().load(this.resource)
    }

    override fun createRenderRequest(requests: RequestManager): RequestBuilder<Bitmap> {
        return createLoadRequest(requests)
    }
}
