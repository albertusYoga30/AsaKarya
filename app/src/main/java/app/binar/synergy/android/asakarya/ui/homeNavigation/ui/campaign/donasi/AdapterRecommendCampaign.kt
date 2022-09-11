package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignByCategoryResponse
import app.binar.synergy.android.asakarya.databinding.AdapterCampaignBinding
import com.bumptech.glide.Glide

class AdapterRecommendCampaign(
    var data: List<CampaignByCategoryResponse.Content>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterRecommendCampaign.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterCampaignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(campaign:  CampaignByCategoryResponse.Content) {
            val img = campaign.imgUrl
            if (img != null){
                Glide.with(binding.root).load(img).into(binding.imageCampaign)
            } else {
                binding.imageCampaign.setImageResource(R.drawable.image_campaign)
            }
//            binding.imageCampaign.setImageResource(campaign.imgUrl)
            binding.titleCampaign.text = campaign.title
            binding.category.text = campaign.categoryName
            binding.progressValue.text = campaign.sumDonation.toString()
            binding.textDaysleft.text = campaign.daysLeft.toString()
            binding.progressBar.max = campaign.fundAmount!!.toInt()

            ObjectAnimator.ofInt(binding.progressBar, "progress", campaign.sumDonation!!.toInt())
                .start()
//            binding.progressBar.progress = campaign.
            binding.root.setOnClickListener {
                listener.click(campaign)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<CampaignByCategoryResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCampaignBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: CampaignByCategoryResponse.Content)
    }

}