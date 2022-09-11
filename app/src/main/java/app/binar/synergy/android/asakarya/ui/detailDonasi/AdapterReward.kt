package app.binar.synergy.android.asakarya.ui.detailDonasi

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.reward.RewardResponse
import app.binar.synergy.android.asakarya.databinding.AdapterDonasiBinding
import app.binar.synergy.android.asakarya.databinding.AdapterRewardBinding
import app.binar.synergy.android.asakarya.ui.donasi.AdapterDonasi
import com.bumptech.glide.Glide

class AdapterReward(
    var data: List<RewardResponse.Data>,
    val listener: AdapterReward.EventListener
): RecyclerView.Adapter<AdapterReward.ViewHolder>() {
    inner class ViewHolder(val binding: AdapterRewardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rewaard: RewardResponse.Data) {
           binding.titleItemPaket.setText(rewaard.item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<RewardResponse.Data>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterReward.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterRewardBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterReward.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: RewardResponse.Data)
    }

}