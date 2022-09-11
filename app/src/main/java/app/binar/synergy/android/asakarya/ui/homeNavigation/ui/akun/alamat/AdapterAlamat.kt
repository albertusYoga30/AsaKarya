package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.binar.synergy.android.asakarya.data.api.address.AddressResponse
import app.binar.synergy.android.asakarya.data.api.profile.UserDetailResponse
import app.binar.synergy.android.asakarya.databinding.AdapterAlamatBinding

class AdapterAlamat(
    var data: List<AddressResponse.Data>,
    val listener: EventListener

) :
    RecyclerView.Adapter<AdapterAlamat.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterAlamatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(address: AddressResponse.Data) {
            binding.textName.text = address.recipient
            binding.textNo.text = address.phone
//            var alamat = ""
//            alamat = alamat + address.main + " " + address.postalCode
            binding.textAddress.text = address.address + " " + address.postalCode

            if (address.main == true) {
                binding.textLabelAddress.visibility = View.VISIBLE
            }

            binding.root.setOnClickListener {
                listener.click(address)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<AddressResponse.Data>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterAlamatBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface EventListener {
        fun click(item: AddressResponse.Data)
    }
}

