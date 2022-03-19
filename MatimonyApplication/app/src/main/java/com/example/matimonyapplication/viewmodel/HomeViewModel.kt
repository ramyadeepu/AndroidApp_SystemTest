package com.example.matimonyapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matimonyapplication.DataBase.DBHelper
import com.example.matimonyapplication.model.HomeModel
import com.example.matimonyapplication.view.HomeActivity

class HomeViewModel:ViewModel() {
   val DB = DBHelper(context = HomeActivity(),null)
   var list: LiveData<ArrayList<HomeModel>> = MutableLiveData()
   fun showData():LiveData<ArrayList<HomeModel>> {
    //  list = DB.info
      return list
   }
}