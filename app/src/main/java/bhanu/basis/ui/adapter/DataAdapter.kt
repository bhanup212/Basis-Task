package bhanu.basis.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import bhanu.basis.model.DataList
import bhanu.basis.ui.CardFragment

/**
 * Created by: Bhanu Prakash
 * Created on:19:52, 12,November,2019
 */
class DataAdapter(fragmentManager: FragmentManager,val dataList: DataList):
    FragmentStatePagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {

    companion object{
        val MAX_VALUE = 200
    }

    override fun getItem(position: Int): Fragment {
        val data = dataList.data[position % dataList.data.size]
        return CardFragment.newInstance(data.text)
    }

    override fun getCount(): Int {
        return dataList.data.size * MAX_VALUE
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    interface OnCardTouchListener{
        fun onActionUp()
    }
}