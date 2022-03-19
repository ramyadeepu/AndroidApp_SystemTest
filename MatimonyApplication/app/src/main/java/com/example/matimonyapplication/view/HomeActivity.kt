package com.example.matimonyapplication.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matimonyapplication.DataBase.DBHelper
import com.example.matimonyapplication.DataBase.DBHelper.Companion.TABLE_NAME
import com.example.matimonyapplication.R
import com.example.matimonyapplication.databinding.ActivityHomeBinding
import com.example.matimonyapplication.model.HomeModel
import com.example.matimonyapplication.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var homeBinding: ActivityHomeBinding
    lateinit var HomeVModel: HomeViewModel
    private lateinit var DB: DBHelper
   private  var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // using data binding liobrary
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        DB = DBHelper(this, null)
        homeBinding.lifecycleOwner = this
        HomeVModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding.apply {
            this.homeVM = HomeVModel
        }
        // use recycler view
        homeBinding.recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CustomAdapter(this)
        homeBinding.recycler.adapter = adapter
      if (count<1){
          addData()
          count=count+1
      }

        DB.getName()

        //HomeVModel.showData()
        adapter.addItem(DB.info)


    }
    fun addData(){
        val data = HomeModel(
            "Kalyani",
            "27yrs",
            "5 ft",
            "Malayalam",
            "Engineer",
            "Kochi",
            "Nair",
            "@drawable/kalyani1",
            "@drawable/kalyani2",
            "@drawable/kalyani3"
        )

        DB.addName(data)
        val data1 =
            HomeModel(
                "kashmira Iyer",
                "23yrs",
                "5.5 ft",
                "Tamil",
                "Doctor",
                "Tamil Nadu",
                "OC",
                "@drawable/kashmira1",
                "@drawable/kashmira2",
                "@drawable/kashmira3"
            )
        DB.addName(data1)
        val data2= HomeModel(
            "Yami Gautham",
            "23yrs",
            "6 ft",
            "Marathi",
            "IFS",
            "Pune",
            "Mohale",
            "@drawable/yami1",
            "@drawable/yami2",
            "@drawable/yami3"
        )
        DB.addName(data2)

        val data4 = HomeModel(
            "Yami Gautham",
            "23yrs",
            "6 ft",
            "Marathi",
            "IFS",
            "Pune",
            "Mohale",
            "@drawable/yami1",
            "@drawable/yami2",
            "@drawable/yami3"
        )
        DB.addName(data4)
    }


}