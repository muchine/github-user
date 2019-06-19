package com.muchine.githubuser.ui.core.image.option

import android.graphics.Bitmap
import kr.co.rememberapp.ui.photo.image.ImageOption

class ImageOptionBuilder {

    private val option = ImageOption()

    fun setRotation(degree: Int): ImageOptionBuilder {
        option.rotationAngle = degree
        return this
    }

    fun setSize(width: Int, height: Int): ImageOptionBuilder {
        option.size = ImageOption.Size(width, height)
        return this
    }

    fun setOnResourceReadyListener(onResourceReady: (Bitmap) -> Unit): ImageOptionBuilder {
        option.onResourceReady = onResourceReady
        return this
    }

    fun setRounded(isRounded: Boolean): ImageOptionBuilder {
        option.isRounded = isRounded
        return this
    }

    fun build(): ImageOption {
        return option
    }

}