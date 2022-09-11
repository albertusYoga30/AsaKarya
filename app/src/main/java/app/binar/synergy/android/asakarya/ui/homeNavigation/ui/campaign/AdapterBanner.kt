package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.databinding.AdapterCampaignBannerBinding
import app.binar.synergy.android.asakarya.model.homeModel.BannerModel

class AdapterBanner(
    var data: List<BannerModel>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterBanner.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterCampaignBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(banner:BannerModel){
            binding.campaignBanner.setImageResource(banner.image)
            binding.root.setOnClickListener {
                listener.click(banner)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<BannerModel>){
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCampaignBannerBinding.inflate(inflater)
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener{
        fun click(item:BannerModel)
    }
}