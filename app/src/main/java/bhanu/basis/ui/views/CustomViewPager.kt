package bhanu.basis.ui.views

import android.view.View
import androidx.viewpager.widget.ViewPager


/**
 * Created by: Bhanu Prakash
 * Created on:20:31, 12,November,2019
 */
class CustomViewPager:ViewPager.PageTransformer {

    private val MIN_SCALE = 0.85f
    private val MIN_ALPHA = 0.5f

    override fun transformPage(view: View, position: Float) {
        val pageWidth = view.width

        if (position < -1) {
            // This page is way off-screen to the left.
            view.alpha = 0f

        } else if (position <= 0) {
            // Use the default slide transition when moving to the left page
            view.alpha = 1f
            view.translationX = 0f
            view.translationY = 0f
            view.scaleX = 1f
            view.scaleY = 1f
            view.rotation = 90*(position)

        } else if (position < 1) {
            // Fade the page out.
            view.alpha = 1f
            view.rotation = 0f

            // Counteract the default slide transition
            view.translationX = pageWidth * -position
            view.translationY = 50 * position

            view.scaleX = 1f.coerceAtLeast(1 - position)
            view.scaleY = 1f.coerceAtLeast(1 - position)
        } else if (position.toInt()==1) {
            view.alpha = 1f
            view.translationX = pageWidth*-position
            view.translationY = 50*position

            view.scaleX = 1f.coerceAtLeast((1 - position))
            view.scaleY = 1f.coerceAtLeast((1 - position))
        }

        else {
            // This page is way off-screen to the right.
            view.alpha = 1f
        }
    }
}