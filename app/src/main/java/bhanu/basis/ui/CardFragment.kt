package bhanu.basis.ui


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import bhanu.basis.R
import kotlinx.android.synthetic.main.viewpager_row_layout.*



/**
 * A simple [Fragment] subclass.
 */
class CardFragment : Fragment() {

    companion object{
        fun newInstance(text:String):Fragment{
            return CardFragment().apply {
                arguments = Bundle(1).apply {
                    putString("text",text)
                }
            }
        }
    }

    private var dX: Float = 0f
    private var dY: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text.text = arguments?.getString("text")

        card_view.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_UP -> {
                    MainActivity.onCardListener.onActionUp()
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                }
                MotionEvent.ACTION_POINTER_UP -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    view.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                }
            }
            card_view.invalidate()
            true
        }
    }


}
