package com.example.sslcommerzedtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amount = 100
        val sslCommerzInitialization = SSLCommerzInitialization(
            "googl611cf29fa3b46",
            "googl611cf29fa3b46@ssl",
            amount.toDouble(),
            SSLCCurrencyType.BDT,
            "123456789098765",
            "yourProductType",
            SSLCSdkType.TESTBOX
        )


        var sslCCustomerInfoInitializer = SSLCCustomerInfoInitializer(
            "CustomerName",
            "customerEmail",
            "customerAddress",
            "",
            "1341",
            "Bangladesh",
            "1234567890",
        )

        var sslCProductInitializer = SSLCProductInitializer(
            "productName",
            "productCategory",
            SSLCProductInitializer.ProductProfile.TravelVertical(
                "productProfile",
                "hoursTillDeparture",
                "flightType",
                "pnr",
                "journeyFromTo"
            )
        )

        var sslcTransactionResponseListener = object : SSLCTransactionResponseListener {
            override fun transactionSuccess(p0: SSLCTransactionInfoModel?) {
                tv.text = p0.toString()
            }

            override fun transactionFail(p0: String?) {
               tv.text = p0
            }

            override fun merchantValidationError(p0: String?) {
                tv.text = p0
            }

        }

        IntegrateSSLCommerz
            .getInstance(this)
            .addSSLCommerzInitialization(sslCommerzInitialization)
            .addCustomerInfoInitializer(sslCCustomerInfoInitializer)
            .addProductInitializer(sslCProductInitializer)
            .buildApiCall(sslcTransactionResponseListener)

    }
}