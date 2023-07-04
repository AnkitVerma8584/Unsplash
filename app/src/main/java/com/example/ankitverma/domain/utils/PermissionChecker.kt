package com.example.ankitverma.domain.utils

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object PermissionChecker {

    fun Fragment.hasStoragePermission(): Boolean =
        ActivityCompat.checkSelfPermission(
            this.requireContext(), getStoragePermission()
        ) == PackageManager.PERMISSION_GRANTED

    fun Fragment.shouldShowRationale(): Boolean =
        ActivityCompat.shouldShowRequestPermissionRationale(
            this.requireActivity(),
            getStoragePermission()
        )

    internal fun getStoragePermission(): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE

    fun Fragment.showImageRational(onOkayClicked: (ok: Unit) -> Unit) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Storage permission required!")
            .setMessage("Your application needs to access your storage for getting images.")
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
            .setPositiveButton(
                "Ok"
            ) { _: DialogInterface?, _: Int -> onOkayClicked.invoke(Unit) }
            .create().show()
    }

    fun Fragment.showSettingsDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Storage permission required!")
            .setMessage("Open the application settings and enable storage permission.")
            .setNegativeButton(
                "Cancel"
            ) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
            .setPositiveButton(
                "Settings"
            ) { _: DialogInterface?, _: Int ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", requireContext().packageName, null)
                intent.data = uri
                startActivity(intent)
            }.create().show()
    }
}