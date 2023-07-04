package com.example.ankitverma.ui

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.example.ankitverma.R
import com.example.ankitverma.data.local.DataStore
import com.example.ankitverma.databinding.ActivityOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    @Inject
    lateinit var dataStore: DataStore
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun skipOnboarding() {
            runBlocking {
                dataStore.onBoardingDone()
                startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
                finish()
            }
        }

        runBlocking {
            if (dataStore.getOnBoardingStatus()) {
                startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
                finish()
            }
        }

        binding.apply {
            skipIntroButton.setOnClickListener {
                skipOnboarding()
            }

            nextButtonCard.setOnClickListener {
                if (onboardingImageViewFlipper.currentView.id == R.id.slide3) {
                    skipOnboarding()
                    return@setOnClickListener
                }
                if (onboardingImageViewFlipper.currentView.id == R.id.slide2) {
                    nextButton.setImageResource(R.drawable.ic_ready)
                    val dimensionInDp = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        65F,
                        resources.displayMetrics
                    ).toInt()
                    nextButtonCard.layoutParams.height = dimensionInDp
                    nextButtonCard.layoutParams.width = dimensionInDp
                    nextButtonCard.requestLayout()
                }
                progress.setProgress(progress.progress + 25, true)
                onboardingImageViewFlipper.showNext()
                onboardingTextViewFlipper.showNext()

            }
        }
    }


}