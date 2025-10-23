package viewmodel

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hostmapp.IDashboardInterface
import com.example.hostmapp.ParcelableDashboardData
import com.example.hostmapp.service.DashboardDataService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class DashboardViewModel : ViewModel() {
    private val _dashboardData = MutableStateFlow(
        ParcelableDashboardData(
            0f, 0f, 100f, 100f, "N"
        )
    )
    val dashboardData: StateFlow<ParcelableDashboardData> = _dashboardData

    private var dashboardService: IDashboardInterface? = null
    private var bound = false


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            dashboardService = IDashboardInterface.Stub.asInterface(service)
            bound = true
            startDataUpdates()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
            dashboardService = null
        }
    }

    fun bindService(context: Context) {
        val intent = Intent("com.example.hostmapp.DASHBOARD_SERVICE")
        intent.setPackage("com.example.hostmapp")
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun unbindService(context: Context) {
        if (bound) {
            context.unbindService(serviceConnection)
            bound = false
        }
    }

    private fun startDataUpdates() {
        viewModelScope.launch {
            while (bound) {
                try {
                    val data = dashboardService?.getDashboardData()
                    if (data != null) {
                        _dashboardData.value = data
                        val currentDateTime: LocalDateTime = LocalDateTime.now()
                        dashboardService?.updateSetting(currentDateTime.toString(),data.toString())
                    }
                } catch (_: Exception) { }
                delay(1000)
            }
        }
    }
}
