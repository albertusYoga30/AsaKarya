package app.binar.synergy.android.asakarya.ui.payment

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityPembayaranDonasiBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.HomeNavigationActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat.AlamatActivity
import com.bumptech.glide.Glide

class PembayaranDonasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPembayaranDonasiBinding
    private lateinit var viewModel: PembayaranDonasiViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranDonasiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)


        viewModel = PembayaranDonasiViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
        binding.inputCurrency.setOnClickListener {
            binding.layoutTotalDonasi.isVisible = true

        }

        binding.buttonPembayaran.setOnClickListener {
            startActivity(Intent(this, PembayaranActivity::class.java))

        }

        binding.inputCurrency.doAfterTextChanged {
            viewModel.onChangeAmount(Integer.parseInt(it.toString()))
            binding.totalDonasi.setText("Rp. "+it)
        }

        binding.inputDukungan.doAfterTextChanged {
            viewModel.onChangeNotes(it.toString())
        }

        viewModel.detailDonasi.observe(this, {
            Glide.with(this).load(it.data?.imgUrl).into(binding.itemPembayaranImage)
            binding.titlePembayaranDonasi.setText(it.data?.title)
            binding.authorNamePembayaranDonasi.setText(it.data?.profileFullName)

        })

        binding.buttonPembayaran.setOnClickListener {
            viewModel.doAddDonasi()
        }


        viewModel.gotoPembayaran.observe(this, {
            if (it) {
                startActivity(
                    Intent(this, PembayaranActivity::class.java)
                        .apply {
                            //apply this.addFlags untuk membuat halaman tidak kembali kesign in ketika sudah login
                            this.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                        Intent.FLAG_ACTIVITY_NEW_TASK
                            )
                        }
                )
            }
        })

        binding.layoutAddress.setOnClickListener {
            startActivity(Intent(this, AlamatActivity::class.java))
        }

        viewModel.detailAlamat.observe(this, {
            binding.textName.setText(it.data?.recipient)
            binding.textNo.setText(it.data?.phone)
            binding.textAddress.setText(it.data?.address)

            if (it.data?.main == true) {
                binding.textLabelAddress.visibility = View.VISIBLE
            }
        })


        viewModel.onViewLoaded()

    }

}