package com.example.matimonyapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.matimonyapplication.R
import com.example.matimonyapplication.databinding.ActivityProfileBinding
import com.synnapps.carouselview.ImageListener
import java.lang.Character.codePointAt

class ProfileActivity : AppCompatActivity() {
    lateinit var profilebinding: ActivityProfileBinding
    var collect = ArrayList<String>()
    var image = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profilebinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        var name: String? = intent.getStringExtra("name")
        var details: String? = intent.getStringExtra("details")
        collect = intent.getSerializableExtra("imageCollection") as ArrayList<String>

        profilebinding.name.text = name
        profilebinding.address.text = details

        profilebinding.carouselView.pageCount = collect.size
        profilebinding.carouselView.setImageListener(imagelistener)

        for (i in collect)
        {

            var res: Int = resources.getIdentifier(i, null, packageName)
            image.add(res)



        }
        profilebinding.backArrow.setOnClickListener{
            onBackPressed()
        }
    }
// image listener for adapter
    var imagelistener = ImageListener { position, imageView ->
        imageView.setImageResource(image[position])

}

}