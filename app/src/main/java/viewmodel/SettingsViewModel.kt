package viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hostmapp.data.AppDatabase
import com.example.hostmapp.data.SettingEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking


class SettingsViewModel(context: Context) : ViewModel() {

    private val appDatabase: AppDatabase = AppDatabase.getInstance(context)
    private val _dayNightMode = MutableStateFlow(true)
    val dayNightMode = _dayNightMode.asStateFlow()

    private val _highBeamAssist = MutableStateFlow(false)
    val highBeamAssist = _highBeamAssist.asStateFlow()

    private val _language = MutableStateFlow("English (US)")
    val language = _language.asStateFlow()

    private val _units = MutableStateFlow("km/h")
    val units = _units.asStateFlow()

    private val _autoUpdate = MutableStateFlow(false)
    val autoUpdate = _autoUpdate.asStateFlow()

    fun toggleDayNightMode() {
        _dayNightMode.value = !_dayNightMode.value
        runBlocking {
            appDatabase.settingsDao().insertOrUpdate(SettingEntity("dayNightMode", _dayNightMode.value.toString()))
            printSettings()
        }
    }
    fun toggleHighBeamAssist() {
        _highBeamAssist.value = !_highBeamAssist.value
        runBlocking {
            appDatabase.settingsDao().insertOrUpdate(SettingEntity("highBeamAssist", _highBeamAssist.value.toString()))
            printSettings()
        }
    }
    fun toggleAutoUpdate() {
        _autoUpdate.value = !_autoUpdate.value
        runBlocking {
            appDatabase.settingsDao().insertOrUpdate(SettingEntity("autoUpdate", _autoUpdate.value.toString()))
            printSettings()
        }
    }
    fun updateLanguage(lang: String) {
        _language.value = lang
        runBlocking {
            appDatabase.settingsDao().insertOrUpdate(SettingEntity("language", lang))
            printSettings()
        }

    }
    fun updateUnits(unit: String) {
        _units.value = unit
        runBlocking {
            appDatabase.settingsDao().insertOrUpdate(SettingEntity("units", unit))
            printSettings()
        }
    }
    
    suspend fun printSettings() {
        val dayNightMode = appDatabase.settingsDao().getSetting("dayNightMode")?.value.toString()
        val highBeamAssist = appDatabase.settingsDao().getSetting("highBeamAssist")?.value.toString()
        val autoUpdate = appDatabase.settingsDao().getSetting("autoUpdate")?.value.toString()
        val language = appDatabase.settingsDao().getSetting("language")?.value.toString()
        val units = appDatabase.settingsDao().getSetting("units")?.value.toString()
        Log.i("SettingsViewModel", "Day/Night Mode: $dayNightMode highBeamAssist: $highBeamAssist autoUpdate: $autoUpdate language: $language units: $units")
    }
}