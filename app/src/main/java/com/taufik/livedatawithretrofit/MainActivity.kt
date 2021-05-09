package com.taufik.livedatawithretrofit

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.taufik.livedatawithretrofit.databinding.ActivityMainBinding
import com.taufik.livedatawithretrofit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setViewModel()

        setOnClickButton()
    }

    private fun setViewModel() {
        binding.apply {
            mainViewModel.restaurant.observe(this@MainActivity, {
                tvTitle.text = it.name
                tvDescription.text = it.description

                Glide.with(this@MainActivity)
                    .load("https://restaurant-api.dicoding.dev/images/large/${it.pictureId}")
                    .into(ivPicture)
            })

            mainViewModel.listReview.observe(this@MainActivity, { consumerReviews ->
                val listReview = consumerReviews.map {
                    "${it.review}\n- ${it.name}"
                }

                lvReview.adapter = ArrayAdapter(this@MainActivity, R.layout.item_review, listReview)
                edReview.setText("")
            })

            mainViewModel.isLoading.observe(this@MainActivity, {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            })
        }
    }

    private fun setOnClickButton() {
        binding.apply {
            btnSend.setOnClickListener {
                mainViewModel.postReview(edReview.text.toString())
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }
    }
}