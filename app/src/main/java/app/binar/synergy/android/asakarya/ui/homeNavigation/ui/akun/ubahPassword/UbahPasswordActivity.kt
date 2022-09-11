package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.UbahPassword

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.ActivityUbahPasswordBinding
import app.binar.synergy.android.asakarya.databinding.ActivityUbahProfilBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.AkunFragment
import app.binar.synergy.android.asakarya.ui.loading.LoadingDialog
import android.R
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import app.binar.synergy.android.asakarya.databinding.FragmentAkunBinding
import app.binar.synergy.android.asakarya.ui.splashscreen.SplashScreenActivity


class UbahPasswordActivity : AppCompatActivity() {
    private lateinit var viewModel: UbahPasswordViewModel
    private lateinit var binding: ActivityUbahPasswordBinding
    private val loading by lazy{LoadingDialog(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbahPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)

        viewModel = UbahPasswordViewModel(sharedPreferences)


        viewModel.onViewLoaded()

        binding.etOldPass.doAfterTextChanged {
            viewModel.onChangeOldPass(it.toString())
        }

        binding.etNewPass.doAfterTextChanged {
            viewModel.onChangeNewPass(it.toString())
        }

        binding.etRenewPass.doAfterTextChanged {
            viewModel.onChangerenewPass(it.toString())
        }
        binding.iconBack.setOnClickListener {
            onBackPressed()
        }

        var show1 = true
        binding.imagePassVisibility.setOnClickListener {
            if (show1) {
                binding.etOldPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imagePassVisibility.setImageResource(app.binar.synergy.android.asakarya.R.drawable.ic_eye_visible)
                show1 = false
            } else {
                binding.etOldPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imagePassVisibility.setImageResource(app.binar.synergy.android.asakarya.R.drawable.ic_eye)
                show1 = true
            }
        }

        var show2 = true
        binding.imagePassVisibility2.setOnClickListener {
            if (show2) {
                binding.etNewPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imagePassVisibility2.setImageResource(app.binar.synergy.android.asakarya.R.drawable.ic_eye_visible)
                show2 = false
            } else {
                binding.etNewPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imagePassVisibility2.setImageResource(app.binar.synergy.android.asakarya.R.drawable.ic_eye)
                show2 = true
            }
        }

        var show3 = true
        binding.imagePassVisibility3.setOnClickListener {
            if (show3) {
                binding.etRenewPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imagePassVisibility3.setImageResource(app.binar.synergy.android.asakarya.R.drawable.ic_eye_visible)
                show3 = false
            } else {
                binding.etRenewPass.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imagePassVisibility3.setImageResource(app.binar.synergy.android.asakarya.R.drawable.ic_eye)
                show3 = true
            }
        }


        binding.btnSimpan.setOnClickListener {
            viewModel.updatePassword()
        }
        binding.btnBatalSimpan.setOnClickListener {
            onBackPressed()
        }

        viewModel.showMessage.observe(this,  {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.showLoading.observe(this, Observer {
            loading.showLoading(it)
        })

        viewModel.gotoSplashScreen.observe(this, {
            if (it){
                startActivity(Intent(this, AkunFragment::class.java))
            }
        })
    }
}