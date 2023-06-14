package com.example.advweek4.view

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.advweek4.R
import com.example.advweek4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }
    companion object{
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int) {
            val CHANNEL_ID = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            var builder = NotificationCompat.Builder(instance!!.applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(NotificationCompat.BigTextStyle())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext!!)
            if (ActivityCompat.checkSelfPermission(
                    instance!!.applicationContext,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notificationManager.notify(1001, builder.build())
        }


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable = Observable.just("a stream of data","hellow","world")
            .subscribe(
                { Log.d("Messages", it) },
                { Log.e("Messages", it.message.toString()) },
                { Log.d("Messages", "Completed") }
            )

//        val observer = object : Observer<String> {
//            //event pertama kali dijalankan (hanya dijalankan satu kali)
//            override fun onSubscribe(d: Disposable) {
//                Log.d("Messages", "begin subscribe")
//            }
//            //event setelah subscribe (membaca data sejumlah data yang dimiliki observable)
//            override fun onNext(t: String) {
//                Log.d("Messages", "data: $t")
//            }
//            override fun onError(e: Throwable) {
//                Log.e("Messages", "error: ${e.message.toString()}")
//            }
//            //ketika data yang dibaca sudah habis
//            override fun onComplete() {
//                Log.d("Messages", "complete")
//            }
//        }
//        observable
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(observer)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
            }
    }
}