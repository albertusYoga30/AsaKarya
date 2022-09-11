package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.campaign.donasi.messages

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.res.stringResource
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.data.api.donation.DonationsResponse
import app.binar.synergy.android.asakarya.databinding.AdapterMessagesBinding

class AdapterMessages(
    var data: List<DonationsResponse.Content>,
    var listener: EventListener
) : RecyclerView.Adapter<AdapterMessages.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterMessagesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(messages: DonationsResponse.Content) {
            if (messages.profileFullName == null) {
                binding.donatorName.text = binding.root.context.getString(R.string.author_name)

            } else {
                binding.donatorName.text = messages.profileFullName
            }
            binding.detailMessages.text = messages.notes
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<DonationsResponse.Content>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMessagesBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: DonationsResponse.Content)
    }

}