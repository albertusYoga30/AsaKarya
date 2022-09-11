package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.donasiSaya

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.donasiSaya.getDonationsByUserIdResponse
import app.binar.synergy.android.asakarya.databinding.ActivityDonasiBinding
import app.binar.synergy.android.asakarya.databinding.FragmentCampaignBinding
import app.binar.synergy.android.asakarya.databinding.FragmentDonasiSayaBinding
import app.binar.synergy.android.asakarya.model.homeModel.BannerModel
import app.binar.synergy.android.asakarya.model.homeModel.CampaignModel
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.AdapterBanner
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.CampaignViewModel
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.PencarianActivity

//import app.binar.synergy.android.asakarya.ui.homeNavigation.databinding.FragmentHomeBinding

class DonasiSayaFragment : Fragment() {

    private lateinit var viewModel: DonasiSayaViewModel
    private lateinit var binding: FragmentDonasiSayaBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDonasiSayaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences =
            activity?.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = DonasiSayaViewModel(sharedPreferences)

//        viewModel.isEmpty.observe(this, {
//            if (!it) {
//                binding.layoutEmpty.visibility = View.VISIBLE
//            } else {
//                binding.layoutEmpty.visibility = View.GONE
//            }
//        })

        val adapterDonasiSaya = AdapterDonasiSaya(listOf(),
        object : AdapterDonasiSaya.EventListener {
            override fun click(item: getDonationsByUserIdResponse.Content) {

            }
        })

        binding.recycleDonasiSaya.adapter = adapterDonasiSaya

        viewModel.onViewLoaded()
        viewModel.donasiSaya.observe(this, {
            if(it.data == null){
                binding.layoutEmpty.visibility = View.VISIBLE
            } else {
                binding.layoutEmpty.visibility = View.GONE
                adapterDonasiSaya.update(it.data!!.content)
            }

        })
        return root
    }
}