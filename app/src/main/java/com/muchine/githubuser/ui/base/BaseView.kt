package com.muchine.githubuser.ui.base

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

abstract class BaseView : RelativeLayout {

    constructor(context: Context): super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

}