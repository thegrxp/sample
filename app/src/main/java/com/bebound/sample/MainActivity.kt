package com.bebound.sample

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bebound.sample.generated.BoolRequest
import com.bebound.sample.generated.BoolResponse
import com.bebound.sdk.BeBound
import com.bebound.sdk.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BeBound.SubscriptionListener, BeBound.Listener {

    private val requestCodePermission = 6432

    private val permissions = arrayOf(
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.READ_SMS,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.SEND_SMS,
        Manifest.permission.INTERNET
    )

    @TargetApi(23)
    private fun ensurePermissions(vararg permissions: String) {
        var request = false
        for (permission in permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                request = true
                break
            }
        }
        if (request) {
            requestPermissions(permissions, requestCodePermission)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 23) {
            ensurePermissions(*permissions)
        }

        // Subscription to Be-Bound service is mandatory before sending a normal message
        // but has to be done only once at first launch of the app.
        // You can handle it in a splash screen for instance, so that you just have to check if you're
        // subscribed once.
        button_subscription.setOnClickListener {
            BeBound.Subscription.getInstance().listener(this@MainActivity).subscribe()
        }

        // This is how to send a message and receive a response
        button_request.setOnClickListener {
            // To not have to check this when you send a message, a check in a splash activity is helpful
            if (BeBound.isSubscribed()) {
                val content = BoolRequest().withBoolTest(true)

                BeBound
                    .message(content)
                    .response(BoolResponse::class.java)
                    .listener(this)
                    .send()
            }
        }
    }

    /**
     * Subscription listeners
     */
    override fun onSubscriptionError(p0: Int, p1: String?) {
        // Subscription Error
    }

    override fun onSubscriptionSent() {
        // Subscription Sent
    }

    override fun onSubscriptionTimeout() {
        // Timeout
    }

    override fun onSubscriptionSuccess(p0: Short) {
        // Subscription Success, if you're in a splash activity you can go to the next activity
    }

    /**
     * Messages Listeners
     */
    override fun onSuccess(p0: Message<*>?) {
        // Message has been sent
    }

    override fun onResponse(p0: Message<*>?) {
        // You handle your response here. Please note that we process everything in a background thread,
        // you need to use a UI thread to make UI changes.
    }

    override fun onError(p0: Int, p1: String?) {
        // Error
    }
}
