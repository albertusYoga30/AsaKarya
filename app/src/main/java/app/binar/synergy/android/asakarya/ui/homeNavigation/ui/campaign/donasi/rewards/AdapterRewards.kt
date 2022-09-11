package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.rewards

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.rewards.RewardByCampaignResponse
import app.binar.synergy.android.asakarya.databinding.AdapterRewardsBinding

class AdapterRewards(
    var data: List<RewardByCampaignResponse.Content>,
    val listener: EventListener

) : RecyclerView.Adapter<AdapterRewards.ViewHolder>() {
    inner class ViewHolder(val binding: AdapterRewardsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rewards: RewardByCampaignResponse.Content) {
            when (rewards.rewardTypeId) {
                1 -> {
                    binding.imageReward.setImageResource(R.drawable.reward1)
                    binding.currency.text = binding.root.context.getString(R.string.paket_a_value)
                }
                2 -> {
                    binding.imageReward.setImageResource(R.drawable.reward2)
                    binding.currency.text = binding.root.context.getString(R.string.paket_b_value)

                }
                3 -> {
                    binding.imageReward.setImageResource(R.drawable.reward3)
                    binding.currency.text = binding.root.context.getString(R.string.paket_c_value)
                }
            }
            binding.itemReward.text = rewards.item
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<RewardByCampaignResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterRewardsBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: RewardByCampaignResponse.Content)
    }
}