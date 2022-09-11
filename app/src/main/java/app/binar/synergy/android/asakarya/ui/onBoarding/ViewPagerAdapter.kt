package app.binar.synergy.android.asakarya.ui.onBoarding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (Fragment:FragmentActivity, listener:(CharSequence)->Unit):FragmentStateAdapter(Fragment){
    private val dataFragment= mutableListOf(
        OnBoardingFragment.newInstance("0",listener),
        OnBoardingFragment.newInstance("1",listener),
        OnBoardingFragment.newInstance("2",listener),
    )

    override fun getItemCount(): Int =3

    override fun createFragment(position: Int): Fragment = dataFragment[position]

}