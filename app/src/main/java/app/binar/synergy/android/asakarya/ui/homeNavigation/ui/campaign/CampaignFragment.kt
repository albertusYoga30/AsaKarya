package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.campaign.DonasiCepatResponse
import app.binar.synergy.android.asakarya.databinding.FragmentCampaignBinding
import app.binar.synergy.android.asakarya.model.homeModel.BannerModel
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.bookmark.BookmarkActivity
import app.binar.synergy.android.asakarya.ui.donasi.DonasiActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.DetailDonasiActivity
import com.bumptech.glide.Glide

class CampaignFragment : Fragment() {

    private lateinit var viewModel: CampaignViewModel
    private lateinit var binding: FragmentCampaignBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCampaignBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences =
            activity?.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = CampaignViewModel(sharedPreferences)

        binding.textUsername


        binding.searchBar.setOnClickListener {
            startActivity(Intent(this.context, PencarianActivity::class.java))
        }

        binding.icBookmark.setOnClickListener {
            startActivity(Intent(this.context, BookmarkActivity::class.java))
        }

        viewModel.profileDetail.observe(viewLifecycleOwner) {
            binding.username.text = (it.data?.fullName)
        }
        val adapterBanner = AdapterBanner(listOf(),
            object : AdapterBanner.EventListener {
                override fun click(item: BannerModel) {
                }
            })
        binding.recycleviewBanner.adapter = adapterBanner


        val adapterCampaign = AdapterCampaign(listOf(),
            object : AdapterCampaign.EventListener {
                override fun click(item: CampaignResponse.Content) {
                    sharedPreferences?.edit {
                        this.putInt(Const.CAMPAIGN_ID, item.id!!)
                        this.putInt(Const.CATEGORY_ID, item.categoryId!!)
                        apply()
                    }

                    startActivity( Intent(context, DetailDonasiActivity::class.java))

//
//                    val intent: Intent = new Intent(context; DetailDonasiActivity::class.java)
//                    intent.putExtra("campaign_id", item.id)
//                    startActivity(intent)
                }
            })

        binding.recycleviewCampaign.adapter = adapterCampaign

        val adapterDonasiCepat = AdapterDonasiCepat(listOf(),
        object: AdapterDonasiCepat.EventListener{
            override fun click(item: DonasiCepatResponse.Content) {
                sharedPreferences?.edit {
                    this.putInt(Const.CAMPAIGN_ID, item.id!!)
                    this.putInt(Const.CATEGORY_ID, item.categoryId!!)
                    apply()
                }

                startActivity( Intent(context, DetailDonasiActivity::class.java))
            }

        })
        binding.recycleviewCampaign2.adapter = adapterDonasiCepat


        binding.textViewMore.setOnClickListener {
            startActivity(Intent(this.context, DonasiActivity::class.java))
        }
        binding.arrowMore.setOnClickListener {
            startActivity(Intent(this.context, DonasiActivity::class.java))
        }


        viewModel.onViewLoaded()

//        viewModel.getFromAPI()
//
        viewModel.homeModel.observe(viewLifecycleOwner, {
            adapterBanner.update(it.bannerListModel)
//            adapterCampaign.update(it.campaignListModel)
        })

        viewModel.campaign.observe(viewLifecycleOwner, {
            adapterCampaign.update(it.data!!.content)
        })

        viewModel.donasiCepat.observe(viewLifecycleOwner, {
            adapterDonasiCepat.update(it)
        })

        return root
    }
}