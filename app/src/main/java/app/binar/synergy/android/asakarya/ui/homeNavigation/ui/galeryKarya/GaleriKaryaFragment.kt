package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.galeryKarya

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.donasiSaya.getDonationsByUserIdResponse
import app.binar.synergy.android.asakarya.data.api.galleryKarya.AllGalleryResponse
import app.binar.synergy.android.asakarya.databinding.FragmentGaleriKaryaBinding
import app.binar.synergy.android.asakarya.ui.galeriKarya.GaleriKaryaActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.DetailDonasiActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.donasiSaya.AdapterDonasiSaya
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.donasiSaya.DonasiSayaViewModel

//import app.binar.synergy.android.asakarya.ui.homeNavigation.databinding.FragmentNotificationsBinding

class GaleriKaryaFragment : Fragment() {

    private lateinit var viewModel: GaleriKaryaViewModel
    private lateinit var binding: FragmentGaleriKaryaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleriKaryaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences =
            activity?.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = GaleriKaryaViewModel(sharedPreferences)

        val adapterGalleryKarya = AdapterGalleryKarya(listOf(),
            object : AdapterGalleryKarya.EventListener {
                override fun click(item: CampaignResponse.Content) {
                    sharedPreferences?.edit {
                        this.putInt(Const.CAMPAIGN_ID, item.id!!)
                        this.putInt(Const.CATEGORY_ID, item.categoryId!!)
                        apply()
                    }
                    startActivity( Intent(context, GaleriKaryaActivity::class.java))

                }
            })

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        binding.recycleviewGaleriKarya.layoutManager = staggeredGridLayoutManager
        binding.recycleviewGaleriKarya.adapter = adapterGalleryKarya
        viewModel.onViewLoaded()

        viewModel.galleryKarya.observe(viewLifecycleOwner,{
            adapterGalleryKarya.update(it.data!!.content)
        })
        return root
    }


}