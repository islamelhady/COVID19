package com.elhady.covid19.ui.settings

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.elhady.covid19.R
import com.elhady.covid19.ui.binding.addOnPropertyChanged
import com.elhady.covid19.utils.SettingsEvent

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var viewModel: SettingsViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar!!.setTitle(R.string.title_settings)
        }

        viewModel.mode.addOnPropertyChanged {
            AppCompatDelegate.setDefaultNightMode(it.get())
        }

        val intervals = findPreference<ListPreference>(getString(R.string.pref_key_intervals))!!
        intervals.setOnPreferenceChangeListener { _, newValue ->
            viewModel.changeNotificationInterval(newValue as String)
            true
        }

        val theme = findPreference<ListPreference>(getString(R.string.pref_key_theme_picker))!!
        theme.setOnPreferenceChangeListener { _, newValue ->
            viewModel.changeTheme(newValue as String)
            true
        }

        findPreference<SwitchPreference>(getString(R.string.pref_key_notifications))!!.apply {
            onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                viewModel.enableNotifications(newValue as Boolean)
                true
            }
        }

        findPreference<Preference>(getString(R.string.pref_key_version))!!.apply {
            summary = context.packageManager.getPackageInfo(context.packageName, 0).versionName
            SettingsEvent("Version", null)
        }

        findPreference<Preference>(getString(R.string.pref_key_send_feedback))!!.apply {
            Preference.OnPreferenceClickListener {
                sendFeedback(requireActivity())
                SettingsEvent("Send feedback", null)
                true
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this)
                .navigateUp()
        }
        return true
    }

    /**
     * Email client intent to send support mail
     * Appends the necessary device information to email body
     * useful when providing support
     */
    private fun sendFeedback(context: Context) {
        var body: String? = null
        try {
            body = context.packageManager.getPackageInfo(context.packageName, 0).versionName
            body =
                "\n\n-----------------------------\nPlease don't remove this information\n Device OS: Android \n Device OS version: " +
                        Build.VERSION.RELEASE + "\n App Version: " + body + "\n Device Brand: " + Build.BRAND +
                        "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER
        } catch (e: PackageManager.NameNotFoundException) {
        }

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("islam.elhadyy@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Query from android app")
            putExtra(Intent.EXTRA_TEXT, body)
        }

        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        }
    }
}