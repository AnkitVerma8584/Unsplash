package com.example.ankitverma.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import coil.load
import com.example.ankitverma.databinding.FragmentImageBinding
import com.example.ankitverma.domain.utils.PermissionChecker
import com.example.ankitverma.domain.utils.PermissionChecker.hasStoragePermission
import com.example.ankitverma.domain.utils.PermissionChecker.shouldShowRationale
import com.example.ankitverma.domain.utils.PermissionChecker.showImageRational
import com.example.ankitverma.domain.utils.PermissionChecker.showSettingsDialog

class ImageFragment : Fragment() {
    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result: Boolean ->
            if (result) imagePickerLauncher.launch("image/*")
            else {
                requestStoragePermission()
            }
        }

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            if (it != null) {
                binding.selectImageImageView.load(it)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            buttonSelectImage.setOnClickListener {
                if (hasStoragePermission()) {
                    imagePickerLauncher.launch("image/*")
                } else {
                    permissionLauncher.launch(PermissionChecker.getStoragePermission())
                }
            }

            removeImage.setOnClickListener {
                selectImageImageView.load(null)
            }
        }
    }

    private fun requestStoragePermission() {
        if (shouldShowRationale()) {
            showImageRational { permissionLauncher.launch(PermissionChecker.getStoragePermission()) }
        } else {
            showSettingsDialog()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}