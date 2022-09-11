package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.donasiSaya

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.donasiSaya.getDonationsByUserIdResponse
import app.binar.synergy.android.asakarya.databinding.AdapterDonasiBinding
import app.binar.synergy.android.asakarya.databinding.AdapterDonasiSayaBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class AdapterDonasiSaya(
    var data: List<getDonationsByUserIdResponse.Content>,
    val listener: EventListener
) :
    RecyclerView.Adapter<AdapterDonasiSaya.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterDonasiSayaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
        fun bind(donasi: getDonationsByUserIdResponse.Content) {
            val img = donasi.campaignImage
            if (img != null) {
                Glide.with(binding.root).load(img).into(binding.imageDonasi)
            } else {
                binding.imageDonasi.setImageResource(R.drawable.image_campaign)
            }
            binding.textTitle.text = donasi.campaignTitle
            binding.textDonasiSum.text = (donasi.amount.toString())

            if (donasi.status == 1) {
                binding.textDonasiStatus.text = binding.root.context.getString(R.string.text_berhasil)
            } else {
                binding.textDonasiStatus.text = binding.root.context.getString(R.string.text_menunggu)
                binding.textDonasiStatus.background = binding.root.context.getDrawable(R.drawable.shape_status_menunggu)
                binding.textDonasiStatus.setTextColor(R.color.color_orange)
            }

            val d = donasi.updatedAt
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(d)
            val formattedDatesString = SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)
            binding.textDate.text = formattedDatesString

            binding.root.setOnClickListener {
                listener.click(donasi)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<getDonationsByUserIdResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterDonasiSayaBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: getDonationsByUserIdResponse.Content)
    }

}