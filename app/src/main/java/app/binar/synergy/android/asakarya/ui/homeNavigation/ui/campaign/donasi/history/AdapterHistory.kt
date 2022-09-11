package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.data.api.history.HistoryResponse
import app.binar.synergy.android.asakarya.databinding.AdapterHistoryBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

class AdapterHistory(
    var data: List<HistoryResponse.Content>,
    var listener: EventListener
) : RecyclerView.Adapter<AdapterHistory.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryResponse.Content) {

            val d = history.updatedAt
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(d)
            val formattedDatesString = SimpleDateFormat("dd MMM yyyy", Locale.US).format(date)

            binding.dateNews.text = formattedDatesString

            if (history.imgUrl != null) {
                binding.imageNews.isVisible = true
                Glide.with(binding.root).load(history.imgUrl).into(binding.imageNews)
            } else{
                binding.imageNews.isVisible = false
            }
            binding.newsTitle.text = history.title
            binding.textBerita.text = history.activity
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<HistoryResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterHistoryBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: HistoryResponse.Content)
    }

}