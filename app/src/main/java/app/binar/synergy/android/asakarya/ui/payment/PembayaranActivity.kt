package app.binar.synergy.android.asakarya.ui.payment

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityPembayaranBinding
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PembayaranActivity : AppCompatActivity() {
    private lateinit var viewModel: PembayaranViewModel
    private lateinit var binding: ActivityPembayaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(Const.PREF_NAME, MODE_PRIVATE)

        viewModel = PembayaranViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnPembayaran.setOnClickListener {
            startActivity(Intent(this, PermbayaranBerhasilActivity::class.java))
        }

        val  nominal: Int = sharedPreferences.getInt(Const.TOTAL_DONASI, 0)
        Log.d("nominal:: ", nominal.toString())
        binding.nominalMoney.setText("Rp. "+nominal.toString())

        // simpen nama gambar disini
        viewModel.updateDonasi.observe(this, {
            binding.nominalMoney.setText(Const.TOTAL_DONASI)
        })

        val getContent =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    val type = contentResolver.getType(it)
                    val tempFile = File.createTempFile("temp-", null, null)
                    val inputStream = contentResolver.openInputStream(uri)

                    tempFile.outputStream().use {
                        inputStream?.copyTo(it)
                    }

                    val requestBody: RequestBody = tempFile.asRequestBody(type?.toMediaType())
                    val body = MultipartBody.Part.createFormData("image", tempFile.name, requestBody)

                    viewModel.uploadImage(body)
                }
            }

        binding.dragDrop.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.btnPembayaran.setOnClickListener {
            viewModel.updateDonasi
        }

        viewModel.gotoThankyouPage.observe(this, {
            if(it) {

                startActivity(
                    Intent(this, PermbayaranBerhasilActivity::class.java)
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

    }
}