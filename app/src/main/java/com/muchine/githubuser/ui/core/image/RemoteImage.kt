package kr.co.rememberapp.ui.photo.image

import android.graphics.Bitmap
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

/**
 * path: Preview 이미지 경로(HTTP / Local)
 * error: 에러 발생 시 표시할 이미지 리소스 ID
 */
open class RemoteImage(
    path: String,
    private val error: Int,
    private val preview: String? = null,
    private val option: ImageOption? = null
) : SimpleImage(path) {

    override fun createRenderRequest(requests: RequestManager): RequestBuilder<Bitmap> {
        val request = super.createRenderRequest(requests)
                .apply(RequestOptions().error(this.error))

        return preview?.let { p ->
            val preview = requests.asBitmap().load(p)
            option?.build(preview)
            request.thumbnail(preview)
        } ?: request
    }

}
