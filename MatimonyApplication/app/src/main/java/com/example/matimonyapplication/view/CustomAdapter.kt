package com.example.matimonyapplication.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.matimonyapplication.R
import com.example.matimonyapplication.model.HomeModel

class CustomAdapter(var context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var list: ArrayList<HomeModel> = ArrayList()

    fun addItem(item: ArrayList<HomeModel>) {
        this.list = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lst = list[position]
        var img = ArrayList<String>()
        img.add(lst.profileImg1)
        img.add(lst.profileImg2)
        img.add(lst.profileImg3)
        holder.name.text = lst.name
        var details: String =
            lst.age + "," + lst.height + "," + lst.language + "," + lst.cast + "," + lst.proffession + "," + lst.address
        holder.details.text = details
        var res: Int = this.context.resources.getIdentifier(lst.profileImg1, null, this.context.packageName)
        val bitmap: Bitmap = BitmapFactory.decodeResource(this.context.resources, res)
        holder.profile.setImageBitmap(bitmap)
        holder.yes.setOnClickListener {
            Toast.makeText(this.context,"Profile is Accepted",Toast.LENGTH_SHORT).show()
            list.remove(list[position])
            notifyDataSetChanged()
        }
        holder.no.setOnClickListener {
            Toast.makeText(this.context,"Profile is Not Accepted",Toast.LENGTH_SHORT).show()
            list.remove(list[position])
            notifyDataSetChanged()

        }
        holder.itemView.setOnClickListener{
            val intent = Intent(this.context, ProfileActivity::class.java)
            intent.putExtra("name", lst.name)
            intent.putExtra("details", details)
            intent.putExtra("imageCollection", img)
            context.startActivity(intent)
        }


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var name: TextView = ItemView.findViewById(R.id.name)
        var details: TextView = ItemView.findViewById(R.id.address)
        var profile: ImageView = ItemView.findViewById(R.id.profile)
        val yes: Button = ItemView.findViewById(R.id.yes)
        val no: Button = ItemView.findViewById(R.id.no)


    }

}
