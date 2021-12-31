package com.miqdad.mbuh

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.miqdad.mbuh.databinding.ActivityMainBinding

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnPemain = arrayOf(
            binding.ivPemain1Batu,
            binding.ivPemain1Kertas,
            binding.ivPemain1Gunting,
        )

        val btnCom = arrayOf(
            binding.ivComBatu,
            binding.ivComKertas,
            binding.ivComGunting,
        )

        btnPemain.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                val hasilCom = btnCom.random()
                Log.d("${btnPemain[index].contentDescription}", "Clicked")
                hasilCom.setBackgroundResource(R.drawable.shape_background)
                oneClick(binding.ivPemain1Batu, binding.ivPemain1Kertas, binding.ivPemain1Gunting)
                cekSuit(btnPemain[index].contentDescription, hasilCom.contentDescription)
                btnPemain.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                btnPemain[index].setBackgroundResource(R.drawable.shape_background)
            }
        }
        binding.ivRefresh.setOnClickListener {
            Toast.makeText(this, "Main Lagi", Toast.LENGTH_SHORT)
                .show()
            Log.d("reset", "Clicked")
            btnPemain.forEach {
                it.setBackgroundResource(android.R.color.transparent)
            }
            btnCom.forEach {
                it.setBackgroundResource(android.R.color.transparent)
            }
            binding.textHasil.setBackgroundResource(android.R.color.transparent)
            binding.textHasil.setTextColor(getColor(R.color.red))
            binding.textHasil.setText(R.string.vs)
            bisaClick(binding.ivPemain1Batu, binding.ivPemain1Kertas, binding.ivPemain1Gunting)
        }

    }

    private fun cekSuit(pemain: CharSequence, com: CharSequence) {
        if (pemain == com) {
            Log.d("Hasil", "Draw")
            binding.textHasil.setBackgroundColor(getColor(R.color.blue_draw))
            binding.textHasil.setTextColor(getColor(R.color.white))
            binding.textHasil.setText(R.string.draw)
        } else if (pemain == binding.ivPemain1Batu.contentDescription && com == binding.ivComGunting.contentDescription || pemain == binding.ivPemain1Gunting.contentDescription && com == binding.ivComKertas.contentDescription || pemain == binding.ivPemain1Kertas.contentDescription && com == binding.ivComBatu.contentDescription) {
            Log.d("Hasil", "pemain 1 win")
            binding.textHasil.setBackgroundColor(getColor(R.color.green_winlose))
            binding.textHasil.setTextColor(getColor(R.color.white))
            binding.textHasil.setText(R.string.pemain_1_win)
        } else {
            Log.d("Hasil", "pemain 2/com win")
            binding.textHasil.setBackgroundColor(getColor(R.color.green_winlose))
            binding.textHasil.setTextColor(getColor(R.color.white))
            binding.textHasil.setText(R.string.pemain_2_win)
        }
        Log.d("Hasil", "$pemain VS $com")
    }

    private fun oneClick(image1: ImageView, image2: ImageView, image3: ImageView) {
        image1.isEnabled = false
        image2.isEnabled = false
        image3.isEnabled = false
    }

    private fun bisaClick(image1: ImageView, image2: ImageView, image3: ImageView) {
        image1.isEnabled = true
        image2.isEnabled = true
        image3.isEnabled = true
    }

}
