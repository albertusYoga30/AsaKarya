package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.ubahProfil

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityUbahProfilBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.AkunFragment
import com.bumptech.glide.Glide
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class UbahProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUbahProfilBinding
    private lateinit var viewModel: UbahProfilViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = UbahProfilViewModel(sharedPreferences)

        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.profileDetail.observe(this, {
            if (it.data?.imgUrl != null) {
                Glide.with(binding.root).load(it.data?.imgUrl).circleCrop().into(binding.imgProfil)
            } else {
                binding.imgProfil.setImageResource(R.drawable.ic_profile)
            }

            binding.etName.setText(it.data?.fullName, TextView.BufferType.EDITABLE)
            binding.etEmail.setText(it.data?.username, TextView.BufferType.EDITABLE)
            binding.etPhone.setText(it.data?.phone, TextView.BufferType.EDITABLE)
        })

        viewModel.onViewLoaded()

        binding.etName.doAfterTextChanged {
            viewModel.onChangeFullname(it.toString())
        }

        binding.etEmail.doAfterTextChanged {
            viewModel.onChangeEmail(it.toString())
        }

        binding.etPhone.doAfterTextChanged {
            viewModel.onChangePhone(it.toString())
        }

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
                    val body =
                        MultipartBody.Part.createFormData("image", tempFile.name, requestBody)

                    viewModel.uploadImage(body)
                }
            }

        binding.clickablePilihGambar.setOnClickListener {
            getContent.launch("image/*")
        }


        binding.btnSimpan.setOnClickListener {
//            viewModel.updateProfile.observe
            viewModel.updateProfile()
        }

        binding.btnBatalSimpan.setOnClickListener {
            onBackPressed()
        }

        viewModel.goToAkunFragment.observe(this, {
            if (it){
//                finish()
                startActivity(Intent(this, AkunFragment::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            }
        })
    }
}