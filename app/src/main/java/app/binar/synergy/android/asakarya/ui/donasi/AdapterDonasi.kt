package app.binar.synergy.android.asakarya.ui.donasi

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.databinding.AdapterDonasiBinding
import com.bumptech.glide.Glide

class AdapterDonasi(
    var data: List<CampaignResponse.Content>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterDonasi.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterDonasiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(donasi: CampaignResponse.Content) {
            val img = donasi.imgUrl
            if (img != null){
                Glide.with(binding.root).load(img).into(binding.imageCampaign)
            }
            else {
                binding.imageCampaign.setImageResource(R.drawable.image_campaign)
            }
            binding.titleCampaign.text = donasi.title
            binding.authorName.text = donasi.profileFullName
            binding.category.text = donasi.categoryName

            binding.progressBar.max = donasi.fundAmount!!
            ObjectAnimator.ofInt(binding.progressBar, "progress", donasi.sumDonation!!.toInt())
                .start()

            binding.estimateValue.text = donasi.sumDonation.toString()
            binding.estimateDay.text = donasi.daysLeft.toString()
            binding.root.setOnClickListener {
                listener.click(donasi)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<CampaignResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterDonasiBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: CampaignResponse.Content)
    }

}