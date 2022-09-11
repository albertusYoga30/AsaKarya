package app.binar.synergy.android.asakarya.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import app.binar.synergy.android.asakarya.R

private const val ARG_PARAM1 = "param1"

class OnBoardingFragment : Fragment() {
    private var param1: String? = null
    private lateinit var listener: (CharSequence) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_on_boarding_adapter, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textFragment by lazy {
            mutableListOf<TextView>(
                view.findViewById(R.id.text_title_onBoarding),
                view.findViewById(R.id.text_desc_onboarding)
            )
        }

        val imageFragment = view.findViewById<ImageView>(R.id.image_onBoarding)
        when (param1) {
            "0" -> {
                textFragment[0].setText(R.string.text_title_onBoarding1)
                textFragment[1].setText(R.string.text_desc_onBoarding1)
                imageFragment.setImageResource(R.drawable.image_onboarding1)
            }
            "1" -> {
                textFragment[0].text = resources.getString(R.string.text_title_onBoarding2)
                textFragment[1].text = resources.getString(R.string.text_desc_onBoarding2)
                imageFragment.setImageResource(R.drawable.image_onboarding2)
            }
            "2" -> {
                textFragment[0].text = resources.getString(R.string.text_title_onBoarding3)
                textFragment[1].text = resources.getString(R.string.text_desc_onBoarding3)
                imageFragment.setImageResource(R.drawable.image_onboarding3)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, listener: (CharSequence) -> Unit) =
            OnBoardingFragment().apply {
                this.listener = listener
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}