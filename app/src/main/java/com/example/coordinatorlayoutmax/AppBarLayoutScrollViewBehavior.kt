// 包名必须和AppBarLayout.Behavior的包名一致，不然没有权限重写对应方法
package com.google.android.material.appbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginTop
import kotlin.math.min

/**
 * 在某些情况下，需要使用CoordinatorLayout，但是内容的滚动表现要和ScrollView一致，同时需要在appbar下有下拉刷新效果
 */
class AppBarLayoutScrollViewBehavior(context: Context?, attrs: AttributeSet?) : AppBarLayout.Behavior(context, attrs) {
    // 限制性滑动，表现和ScrollView一致
    var scrollConstraint = true
    private var mRelativeView: View? = null
    override fun layoutDependsOn(parent: CoordinatorLayout, child: AppBarLayout, dependency: View): Boolean {
        (dependency.layoutParams as? CoordinatorLayout.LayoutParams)?.let {
            if (it.behavior != null) {
                mRelativeView = dependency
            }
        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun setHeaderTopBottomOffset(parent: CoordinatorLayout, header: AppBarLayout, newOffset: Int): Int {
        return setHeaderTopBottomOffset(parent, header, newOffset, -header.height - header.marginTop, 0)
    }

    override fun setHeaderTopBottomOffset(coordinatorLayout: CoordinatorLayout, appBarLayout: AppBarLayout, newOffset: Int, minOffset: Int, maxOffset: Int): Int {
        val relativeView = mRelativeView
        val newMinOffset = if (scrollConstraint && relativeView != null && minOffset < 0) {
            val validHeight = coordinatorLayout.height - coordinatorLayout.paddingTop - coordinatorLayout.paddingBottom
            val realHeight = relativeView.height + relativeView.marginTop + relativeView.marginBottom
            min(realHeight - validHeight, minOffset)
            minOffset + validHeight - realHeight
        } else {
            minOffset
        }
        return super.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, newOffset, newMinOffset, maxOffset)
    }
}