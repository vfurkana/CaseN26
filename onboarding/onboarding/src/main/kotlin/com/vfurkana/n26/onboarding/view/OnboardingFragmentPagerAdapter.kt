package com.vfurkana.n26.onboarding.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    var pages: List<OnboardingPage> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = pages.size

    override fun createFragment(position: Int): Fragment {
        return OnboardingPageFragment(pages[position], position == pages.lastIndex)
    }
}