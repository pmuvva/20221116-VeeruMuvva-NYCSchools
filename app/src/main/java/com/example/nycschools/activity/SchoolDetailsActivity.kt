package com.example.nycschools.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.nycschools.R
import com.example.nycschools.databinding.ActivitySchoolDetailsBinding
import com.example.nycschools.remote.SearchViewModelFactory
import com.example.nycschools.util.Constants
import com.example.nycschools.viewmodel.SearchViewModel


class SchoolDetailsActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySchoolDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchoolDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSATResults()
    }

    private fun setSATResults() {
        val schoolDbn = intent.getSerializableExtra(Constants.SCHOOL_DETAILS) as? String

        val searchViewModel = ViewModelProviders.of(this,
            SearchViewModelFactory(this@SchoolDetailsActivity)
        )[SearchViewModel::class.java]

        schoolDbn?.let {
            searchViewModel.getSchoolSATResults(it).observe(this) { response ->
                if(response.isNotEmpty()) {
                    response[0]?.let { schoolSATResults ->
                        binding.tvNoDetails.visibility = View.GONE
                        binding.tvSchoolName.text = schoolSATResults.school_name
                        binding.tvMath.text = String.format(
                            this.resources.getString(R.string.math),
                            schoolSATResults.sat_math_avg_score
                        )
                        binding.tvReading.text = String.format(
                            this.resources.getString(R.string.reading),
                            schoolSATResults.sat_critical_reading_avg_score
                        )
                        binding.tvWriting.text = String.format(
                            this.resources.getString(R.string.writing),
                            schoolSATResults.sat_writing_avg_score
                        )
                    }
                } else {
                    binding.tvNoDetails.visibility = View.VISIBLE
                    binding.tvResults.visibility = View.GONE
                }
            }
        }
    }
}