package br.com.boomerang.packbackapp

import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import br.com.boomerang.packbackapp.ui.activity.ListaProdutoActivity
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Region
import org.altbeacon.beacon.powersave.BackgroundPowerSaver
import org.altbeacon.beacon.startup.BootstrapNotifier
import org.altbeacon.beacon.startup.RegionBootstrap

class PackbackApplication: Application(), BootstrapNotifier {

    private var regionBootstrap: RegionBootstrap? = null
    private var backgroundPowerSaver: BackgroundPowerSaver? = null
    private var haveDetectedBeaconsSinceBoot = false
    private var mainActivity: ListaProdutoActivity? = null

    override fun onCreate() {
        super.onCreate()
        val beaconManager = BeaconManager.getInstanceForApplication(this)

        beaconManager.beaconParsers.clear()

        beaconManager.beaconParsers.add(BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT))


        Log.d(TAG, "setting up background monitoring for beacons and power saving")

        val region = Region("backgroundRegion", null, null, null)
        regionBootstrap = RegionBootstrap(this, region)

        backgroundPowerSaver = BackgroundPowerSaver(this)
    }

    override fun didEnterRegion(region: Region) {
        Log.d(TAG, "did enter region.")
        if (!haveDetectedBeaconsSinceBoot) {
            Log.d(TAG, "auto launching MainActivity")

            val intent = Intent(this, ListaProdutoActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            this.startActivity(intent)

            haveDetectedBeaconsSinceBoot = true
        } else {
            if (mainActivity == null) {
                sendNotification()
            }
        }


    }

    override fun didExitRegion(region: Region) {
        if (mainActivity != null) {
        }
    }

    override fun didDetermineStateForRegion(state: Int, region: Region) {
        if (mainActivity != null) {
        }
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(this)
                .setContentTitle("Produtos recicláveis")
                .setContentText("Clique para ver os produtos disponíveis neste setor")

        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(Intent(this, ListaProdutoActivity::class.java))
        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(resultPendingIntent)
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }

    fun setMonitoringActivity(activity: ListaProdutoActivity) {
        this.mainActivity = activity
    }

    companion object {
        private const val TAG = "BeaconsService"
    }

}