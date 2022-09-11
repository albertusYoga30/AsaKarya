package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.faq

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.data.api.campaign.CampaignResponse
import app.binar.synergy.android.asakarya.data.api.faq.FaqResponse
import app.binar.synergy.android.asakarya.databinding.AdapterFaqBinding

class AdapterFaq(
    var data: List<FaqResponse.Content>,
    val listener: EventListener

) : RecyclerView.Adapter<AdapterFaq.ViewHolder>() {
    inner class ViewHolder(val binding: AdapterFaqBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faq: FaqResponse.Content) {
            binding.question.text = faq.question
            binding.answer.text = faq.answer
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<FaqResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterFaqBinding.inflate(inflater)
        binding.layoutFaq.setOnClickListener {
            if (binding.answer.isVisible){
                binding.answer.isVisible = false
                binding.icDropdown.rotation = 0f

            }else{
                binding.answer.isVisible = true
                binding.icDropdown.rotation = -90f
            }
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: FaqResponse.Content)
    }
}