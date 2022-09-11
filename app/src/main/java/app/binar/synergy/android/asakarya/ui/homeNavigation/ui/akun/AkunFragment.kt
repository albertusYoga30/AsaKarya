package app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.edit
import app.binar.synergy.android.asakarya.R
import app.binar.synergy.android.asakarya.constant.Const
import app.binar.synergy.android.asakarya.databinding.FragmentAkunBinding
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.UbahPassword.UbahPasswordActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.ubahProfil.UbahProfilActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.alamat.AlamatActivity
import app.binar.synergy.android.asakarya.ui.homeNavigation.ui.akun.tentangkami.TentangKamiActivity
import app.binar.synergy.android.asakarya.ui.splashscreen.SplashScreenActivity
import com.bumptech.glide.Glide


class AkunFragment : Fragment() {
    private lateinit var binding: FragmentAkunBinding
    private lateinit var viewModel: AkunViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sharedPreferences =
            activity?.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)


        viewModel = AkunViewModel(sharedPreferences)


        // ubah profil
        binding.layoutUbahProfil.setOnClickListener {
            startActivity(Intent(this.context, UbahProfilActivity::class.java))
        }
        // alamat
        binding.layoutAlamat.setOnClickListener {
            startActivity(Intent(this.context, AlamatActivity::class.java))
        }
        // ubah password
        binding.layoutUbahPassword.setOnClickListener {
            startActivity(Intent(this.context, UbahPasswordActivity::class.java))
        }
        // tentang kami
        binding.layoutAboutUs.setOnClickListener {
            startActivity(Intent(this.context, TentangKamiActivity::class.java))
        }
        // website kami
        binding.layoutWebsite.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://asakarya.vercel.app/"))
            startActivity(browserIntent)
        }

        binding.logoutButton.setOnClickListener {
            sharedPreferences?.edit {
                this.putBoolean(Const.IS_LOGIN, false)
            }
            startActivity(Intent(this.context, SplashScreenActivity::class.java))
        }

        viewModel.onViewLoaded()

        viewModel.profileDetail.observe(viewLifecycleOwner, {
            if (it.data?.imgUrl != null) {
                Glide.with(binding.root).load(it.data?.imgUrl).circleCrop()
                    .into(binding.akunLogoUser)
            } else {
                binding.akunLogoUser.setImageResource(R.drawable.ic_profile)
            }

            binding.userNameAkun.text = (it.data?.fullName)
            binding.userTagAkun.text = "Investor"
        })


        return root
    }

}