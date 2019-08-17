package com.lambda.shoppinglist

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GroceryRepository.createGroceryList()

        puppy_list_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GroceryAdapter(GroceryRepository.groceryList)
            
        }

        share_button.setOnClickListener {

            val addedGroceries = getAdded()
            createNotification(addedGroceries)

            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, addedGroceries)
            startActivity(shareIntent)
        }


    }

    //use to send

    fun getAdded(): String{
        var addedString = ""
        for (grocery in GroceryRepository.groceryList){

            if (grocery.isAdded) addedString += "${grocery.groceryNames}"


        }

        return addedString
    }

    fun  createNotification(addedGrocery: String){

        val channelId = "${this.packageName}.simplechannel"
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "List Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Channel to send list notification"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("Share List Notification")
            .setContentText(addedGrocery)
            .setAutoCancel(true)
        notificationManager.notify(1, notificationBuilder.build())

    }


}
