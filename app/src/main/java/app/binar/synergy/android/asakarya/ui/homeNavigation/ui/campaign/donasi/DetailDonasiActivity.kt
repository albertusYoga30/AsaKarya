package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.isVisible
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignByCategoryResponse
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import app.binar.synergy.android.asakarya.data.api.faq.FaqResponse
import app.binar.synergy.android.asakarya.data.api.history.HistoryResponse
import app.binar.synergy.android.asakarya.data.api.rewards.RewardByCampaignResponse
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.reward.RewardResponse
import app.binar.synergy.android.asakarya.databinding.ActivityDetailDonasiBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.faq.AdapterFaq
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.history.AdapterHistory
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.history.HistoryActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages.AdapterMessages
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages.MessagesActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.rewards.AdapterRewards
import at.blogc.android.views.ExpandableTextView
import app.binar.synergy.android.asakarya.ui.donasi.AdapterDonasi
import app.binar.synergy.android.asakarya.ui.payment.PembayaranDonasiActivity
import com.bumptech.glide.Glide

class DetailDonasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDonasiBinding
    private lateinit var viewModel: DetailDonasiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDonasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = DetailDonasiViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnDonasi.setOnClickListener {
            startActivity(Intent(this, PembayaranDonasiActivity::class.java))
        }

        binding.bookmark.setOnClickListener {
            Toast.makeText(this, "Cuma Text Doang", Toast.LENGTH_SHORT).show()

            //            favorite = if (favorite){
//                binding.bookmark.setImageResource(R.drawable.ic_bookmark)
//                binding.bookmark.setColorFilter(R.color.black)
//                Toast.makeText(this, "Cuma Text Doang", Toast.LENGTH_SHORT).show()
//                false
//
//            }else{
//                binding.bookmark.setImageResource(R.drawable.ic_bookmark_gray)
//                binding.bookmark.setColorFilter(R.color.black)
//                true
//
//            }

        }

        viewModel.campaignDetail.observe(this, {
            if (it.data?.imgUrl == null) {
                binding.imageDonasiSaya.setImageResource(R.drawable.empty_image)
            } else {
                Glide.with(this).load(it.data?.imgUrl).into(binding.imageDonasiSaya)
            }
            binding.titleCampaign.text = it.data?.title
            binding.category.text = it.data?.categoryName
            binding.collectedAmount.text = it.data?.sumDonation.toString()
            binding.collectedLimit.text = it.data?.fundAmount.toString()
            binding.estimateDay.text = it.data?.daysLeft.toString()

            binding.collectedBar.max = it.data?.fundAmount!!.toInt()
            ObjectAnimator.ofInt(binding.collectedBar, "progress", it.data?.sumDonation!!.toInt())
                .start()

            binding.donaturNum.text = it.data?.countDonation.toString()
//            binding.donaturNum.setText(it.data?.countDonation!!)
//            binding.estimateDay.setText(it.data?.daysLeft!!)

            if (it.data?.profileImage == null) {
                binding.accountLogo.setImageResource(R.drawable.ic_account)

            } else {
                Glide.with(this).load(it.data?.profileImage).circleCrop().into(binding.accountLogo)
            }

            binding.authorName.text = it.data?.profileFullName
            binding.roleDef.text = it.data?.categoryName
            binding.detailInfo.text = it.data?.description

        })

        //expandable detail info
        binding.detailInfo.expandInterpolator
        binding.detailInfo.collapseInterpolator
        binding.expandDetailInfo.setOnClickListener {
            if (binding.detailInfo.isExpanded) {
                binding.detailInfo.collapse()
                binding.textExpand.setText(R.string.text_view_more)
                binding.icExpand.scaleY = 1F
            } else {
                binding.detailInfo.toggle()
                binding.textExpand.setText(R.string.text_view_less)
                binding.icExpand.scaleY = -1F
            }
        }

        //recyclerview History
        val adapterHistory = AdapterHistory(listOf(),
            object : AdapterHistory.EventListener {
                override fun click(item: HistoryResponse.Content) {
                }
            })

        //recyclerview messages
        val adapterMessages = AdapterMessages(listOf(),
            object : AdapterMessages.EventListener {
                override fun click(item: DonationsResponse.Content) {
                }
            })

        //recyclerview faq
        val adapterFaq = AdapterFaq(listOf(),
            object : AdapterFaq.EventListener {
                override fun click(item: FaqResponse.Content) {
                }
            })
        //recyclerview reward
        val adapterReward = AdapterRewards(listOf(),
            object : AdapterRewards.EventListener {
                override fun click(item: RewardByCampaignResponse.Content) {
                }
            })

        //recyclerview recommend
        val adapterRecommendCampaign = AdapterRecommendCampaign(listOf(),
            object : AdapterRecommendCampaign.EventListener {
                override fun click(item: CampaignByCategoryResponse.Content) {
                    if (sharedPreferences != null) {
                        sharedPreferences.edit {
                            this.putInt(Const.CAMPAIGN_ID, item.id!!)
                            this.putInt(Const.CATEGORY_ID, item.categoryId!!)
                            apply()
                        }
                    }

                    startActivity(
                        Intent(
                            this@DetailDonasiActivity,
                            DetailDonasiActivity::class.java
                        )
                    )

                }
            })

        viewModel.onViewLoaded()

        binding.recyclerviewNews.adapter = adapterHistory

        binding.recycleviewMessages.adapter = adapterMessages

        binding.recyclerviewFaq.adapter = adapterFaq

        binding.recyclerviewReward.adapter = adapterReward

        binding.recyclerviewRecommend.adapter = adapterRecommendCampaign

        viewModel.history.observe(this, {
            adapterHistory.update(it.data!!.content)
            if (adapterHistory.itemCount > 0) {
                binding.extensorNews.isVisible = true
                binding.emptyHistory.isVisible = false

            } else {
                binding.emptyHistory.isVisible = true
                binding.extensorNews.isVisible = false
            }
        })

        viewModel.messages.observe(this, {
            adapterMessages.update(it.data!!.content)
            if (adapterMessages.itemCount > 0) {
                binding.extensorMessages.isVisible = true
                binding.emptyMessages.isVisible = false

            } else {
                binding.emptyMessages.isVisible = true
                binding.extensorMessages.isVisible = false
            }
        })

        viewModel.faq.observe(this, {
            adapterFaq.update(it.data)
        })

        viewModel.rewards.observe(this, {
            adapterReward.update(it.data)
        })
        viewModel.recommendCampaign.observe(this, {
            adapterRecommendCampaign.update(it.data!!.content)
        })


        binding.extensorNews.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.extensorMessages.setOnClickListener {
            startActivity(Intent(this, MessagesActivity::class.java))
        }


    }
}