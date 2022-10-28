package com.example.mygithubuser.adapter
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mygithubuser.follow.FollowerFragment
import com.example.mygithubuser.follow.FollowingFragment
import com.example.mygithubuser.R

class SectionPager(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    companion object {
        @StringRes
        val LayoutTittle = intArrayOf(R.string.layout_followers, R.string.layout_following)
    }

    override fun getItemCount(): Int {
        return LayoutTittle.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

}