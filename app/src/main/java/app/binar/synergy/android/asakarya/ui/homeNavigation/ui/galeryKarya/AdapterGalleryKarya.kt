package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.galeryKarya//package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.galeryKarya

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.galleryKarya.AllGalleryResponse
import app.binar.synergy.android.asakarya.databinding.AdapterGalleryKaryaBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class AdapterGalleryKarya(
    var data: List<CampaignResponse.Content>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterGalleryKarya.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterGalleryKaryaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(campaign: CampaignResponse.Content) {
            val img = campaign.imgUrl
            if (img != null) {
                Glide.with(binding.root).load(img).into(binding.imageCampaign)
            } else {
                binding.imageCampaign.setImageResource(R.drawable.empty_image)
            }
            binding.titleCampaign.text = campaign.title
            binding.category.text = campaign.categoryName

            val d = campaign.createdAt
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(d)
            val formattedDatesString = SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)
            binding.dateCampaign.text = formattedDatesString
            binding.descCampaign.text = campaign.description

            binding.authorName.text = campaign.profileFullName

            binding.root.setOnClickListener {
                listener.click(campaign)
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
        val binding = AdapterGalleryKaryaBinding.inflate(inflater)
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