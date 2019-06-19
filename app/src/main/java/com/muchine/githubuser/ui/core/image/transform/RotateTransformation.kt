package com.muchine.githubuser.ui.core.image.transform

import android.graphics.Bitmap
import android.graphics.Matrix
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * 비트맵 이미지를 회전하기 위한 Transformation
 * rotationAngle: 이미지를 회전시킬 각도
 */
class RotateTransformation(val degree: Int) : BitmapTransformation() {

    override fun transform(bitmapPool: BitmapPool, bitmap: Bitmap, i: Int, i1: Int): Bitmap {
        if (degree == 0) return bitmap

        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update("rotate$degree".toByteArray())
    }

}
