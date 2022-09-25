package com.example.terimakasihweek2

import Adapter.adapter
import Interface.Listener
import Model.GlobalVar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.terimakasihweek2.databinding.ActivityHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class home : AppCompatActivity(), Listener {
    private lateinit var viewbind: ActivityHomeBinding
    private  val adapterhewan = adapter(GlobalVar.ListdataHewan, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        setupRecycler()
        setUpListener()
    }

    override fun onResume() {
        super.onResume()
        if(GlobalVar.ListdataHewan.size > 0){
            viewbind.Mainrecycler.visibility = View.VISIBLE
            viewbind.textView.visibility = View.GONE
        }else{
            viewbind.Mainrecycler.visibility = View.GONE
            viewbind.textView.visibility = View.VISIBLE
        }
        adapterhewan.notifyDataSetChanged()
    }

    private fun setupRecycler(){
        val layoutManager = LinearLayoutManager(baseContext)
        viewbind.Mainrecycler.layoutManager =layoutManager
        viewbind.Mainrecycler.adapter = adapterhewan
    }

    private fun setUpListener(){
        viewbind.Add.setOnClickListener {
            val add = Intent(baseContext, table::class.java).apply {
                putExtra("status", GlobalVar.statusadd)
            }
            startActivity(add)
        }
    }

    override fun editOnclick(position: Int) {

        val edit = Intent(baseContext, table::class.java).apply {
            putExtra("status", GlobalVar.statusedit)
            putExtra("position", position)
        }
        startActivity(edit)


    }
    override fun onVoiceClicked(posistion: Int){
        if(GlobalVar.ListdataHewan.get(posistion).jenishewan.equals("Ayam")){
            Toast.makeText(baseContext, "Bock Bock Bock", Toast.LENGTH_LONG).show()
        }else if(GlobalVar.ListdataHewan.get(posistion).jenishewan.equals("Kambing")){
            Toast.makeText(baseContext, "Mbekkkkk", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(baseContext, "Moooooooooo", Toast.LENGTH_LONG).show()
        }
    }

    override fun onFeedClicked(posistion: Int) {
        if(GlobalVar.ListdataHewan.get(posistion).jenishewan.equals("Ayam")){
            Toast.makeText(baseContext, "Anda memberi makan biji-bijian", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(baseContext, "Anda memberi makan rumput", Toast.LENGTH_LONG).show()
        }
    }

    override fun deleteOnClick(posistion: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Apakah anda ingin menghapus hewan ini?")
            .setNegativeButton("Batal") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("Setuju") { dialog, which ->
                GlobalVar.ListdataHewan.removeAt(posistion)
                Toast.makeText(baseContext, "Data berhasil di hapus", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }


}