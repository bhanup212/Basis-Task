package bhanu.basis.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import bhanu.basis.R
import bhanu.basis.ui.adapter.DataAdapter
import bhanu.basis.ui.views.CustomViewPager
import bhanu.basis.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.AlphaAnimation



class MainActivity : AppCompatActivity(), DataAdapter.OnCardTouchListener {
    override fun onActionUp() {
        viewPager.currentItem++
    }

    private val viewModel: MainViewModel by viewModel()

    private lateinit var pagerAdapter:DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onCardListener = this
        initViews()
        setObservers()
    }
    private fun initViews(){
        viewPager.setPageTransformer(false,CustomViewPager())
        viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position > 0 && hintLayout.animation != null){
                    hintLayout.animation.cancel()
                }
            }

            override fun onPageSelected(position: Int) {
                val count = pagerAdapter.count/DataAdapter.MAX_VALUE
                progressIndicatorTv.text = "${(position % pagerAdapter.dataList.data.size)+1}/$count"
            }

        })
    }

    private fun setObservers(){
        viewModel.getCardsList()

        viewModel.isLoading().observe(this, Observer {
            progressBar.isVisible = it
        })
        viewModel.dataList().observe(this, Observer {
            pagerAdapter = DataAdapter(supportFragmentManager,it)
            viewPager.adapter = pagerAdapter
            viewPager.currentItem = 0
            hintLayout.isVisible = true
            hintAnimation()
        })
    }
    private fun hintAnimation(){
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 1000
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        hintLayout.startAnimation(animation)
        animation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                hintLayout.isVisible = false
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })
    }

    companion object{
        lateinit var onCardListener:DataAdapter.OnCardTouchListener
    }

}
