package com.project.egzaminai2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ViewInflater {

    fun inflate(parent: ViewGroup, layoutId: Int): View =
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

}
