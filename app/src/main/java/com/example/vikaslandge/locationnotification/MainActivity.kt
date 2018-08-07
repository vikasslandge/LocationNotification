package com.example.vikaslandge.locationnotification

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1.toFloat(),
                object : LocationListener{
                    override fun onLocationChanged(p0: Location?) {
                        textView.text = p0!!.latitude.toString()
                        textView2.text = p0!!.longitude.toString()
                    }

                    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    }

                    override fun onProviderEnabled(p0: String?) {
                    }

                    override fun onProviderDisabled(p0: String?) {
                    }

                }
        )

        button.setOnClickListener(){
        var nManager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var nCompact = NotificationCompat.Builder(this@MainActivity,"Notification")
        nCompact.setTicker("location")
        nCompact.setContentTitle("Moood")
            nCompact.setContentText("This is notification")
        nCompact.setSmallIcon(R.drawable.ic_mood_black_24dp)
            nCompact.setAutoCancel(true)
        nManager.notify(1,nCompact.build())
            var i = Intent(this@MainActivity,
                    MainActivity::class.java)
            var pIntent = PendingIntent.getActivity(
                    this@MainActivity,0,i,0)
            val contentIntent = nCompact.setContentIntent(pIntent)
            nManager.notify(1,nCompact.build())
        }
    }
}
