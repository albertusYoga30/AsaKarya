package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat.tambahAlamat

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityTambahAlamatBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat.AlamatActivity
import app.binar.synergy.android.asakarya.ui.loading.LoadingDialog

class TambahAlamatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahAlamatBinding
    private lateinit var viewModel: TambahAlamatViewModel
    private val loading by lazy{ LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahAlamatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = TambahAlamatViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        binding.etNameAlamat.doAfterTextChanged {
            viewModel.onChangeReceipent(it.toString())
        }

        binding.etNoTelpAlamat.doAfterTextChanged {
            viewModel.onChangePhone(it.toString())
        }

        binding.etAlamatLengkapAlamat.doAfterTextChanged {
            viewModel.onChangeAddress(it.toString())
        }

        binding.etKodePosAlamat.doAfterTextChanged {
            viewModel.onChangePostalCode(Integer.parseInt(it.toString()))
        }


        binding.btnSimpanAlamat.setOnClickListener {
            viewModel.onViewLoaded()
        }

        binding.btnBatalSimpan.setOnClickListener {
            onBackPressed()
        }

        viewModel.gotoAlamatPage.observe(this, {
            if (it) {
                startActivity(
                    Intent(this, AlamatActivity::class.java)
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


        viewModel.showLoading.observe(this,  {
            loading.showLoading(it)
        })
    }
}