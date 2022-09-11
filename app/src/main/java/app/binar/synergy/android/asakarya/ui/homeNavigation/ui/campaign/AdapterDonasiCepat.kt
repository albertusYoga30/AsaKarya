package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.campaign.DonasiCepatResponse
import app.binar.synergy.android.asakarya.databinding.AdapterDonasiCepatBinding
import com.bumptech.glide.Glide

class AdapterDonasiCepat(
    var data: List<DonasiCepatResponse.Content>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterDonasiCepat.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterDonasiCepatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(donasiCepat:  DonasiCepatResponse.Content) {
            val img = donasiCepat.imgUrl
            if (img != null){
                Glide.with(binding.root).load(img).into(binding.imageCampaign)
            } else {
                binding.imageCampaign.setImageResource(R.drawable.image_campaign)
            }
//            binding.imageCampaign.setImageResource(campaign.imgUrl)
            binding.titleCampaign.text = donasiCepat.title
            binding.progressValue.text = donasiCepat.sumDonation.toString()
            binding.textDaysleft.text = donasiCepat.daysLeft.toString()

            binding.progressBar.max = donasiCepat.fundAmount!!.toInt()

            ObjectAnimator.ofInt(binding.progressBar, "progress", donasiCepat.sumDonation!!.toInt())
                .start()

            binding.root.setOnClickListener {
                listener.click(donasiCepat)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<DonasiCepatResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterDonasiCepatBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: DonasiCepatResponse.Content)
    }

}