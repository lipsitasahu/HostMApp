package com.example.hostmapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.hostmapp.IDashboardInterface
import com.example.hostmapp.ParcelableDashboardData
import com.example.hostmapp.data.AppDatabase
import com.example.hostmapp.data.SettingsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random


class DashboardDataService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private lateinit var db: AppDatabase

    @Volatile private var currentData = ParcelableDashboardData(
        speed = 0f,
        rpm = 0f,
        gear = "N",
        fuelLevel = 100f,
        batteryLevel = 100f
    )

    private val binder = object : IDashboardInterface.Stub() {
        override fun getDashboardData(): ParcelableDashboardData = currentData

        override fun updateSetting(key: String, value: String) {
            serviceScope.launch {
                db.settingsDao().insertOrUpdate(SettingsEntity(key, value))
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getInstance(this)
        startSimulation()
    }

    override fun onBind(intent: Intent?): IBinder = binder

    private fun startSimulation() {
        serviceScope.launch {
            while (isActive) {
                delay(3000)
                val newData = currentData.copy(
                    speed = Random.nextInt(0, 180).toFloat(),
                    rpm = Random.nextInt(500, 7000).toFloat(),
                    fuelLevel = (currentData.fuelLevel - 0.1f).coerceAtLeast(0f),
                    batteryLevel = (currentData.batteryLevel - 0.05f).coerceAtLeast(0f),
                    gear = listOf("N", "1", "2", "3", "4", "5", "6").random()
                )
                currentData = newData
            }
        }
    }

    override fun onDestroy() {
        serviceScope.cancel()
        super.onDestroy()
    }
}
