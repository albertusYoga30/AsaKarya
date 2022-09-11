package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.data.api.address.AddressResponse
import app.binar.synergy.android.asakarya.databinding.ActivityAlamatBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat.tambahAlamat.TambahAlamatActivity
import app.binar.synergy.android.asakarya.ui.payment.PembayaranDonasiActivity

class AlamatActivity : AppCompatActivity() {
    private lateinit var viewModel: AlamatViewModel
    private lateinit var binding: ActivityAlamatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlamatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = AlamatViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
        val adapterAlamat = AdapterAlamat(listOf(),
            object : AdapterAlamat.EventListener{
                override fun click(item: AddressResponse.Data) {
                    Log.d("address id di almat acitivity:: ", item.id.toString())
                    sharedPreferences.edit {
                        this.putInt(Const.ADDRESS_ID, item.id!!)
                    }
                    viewModel.setMainAddress()
                    viewModel.onViewLoaded()
//                    startActivity(Intent(this@AlamatActivity, PembayaranDonasiActivity::class.java))
                }
            }
        )

        binding.recycleAlamat.adapter = adapterAlamat

        viewModel.onViewLoaded()

        viewModel.address.observe(this, {
            adapterAlamat.update(it.data)
        })

        binding.clickableTambahAlamat.setOnClickListener {
            startActivity(Intent(this@AlamatActivity, TambahAlamatActivity::class.java))
        }
    }
}