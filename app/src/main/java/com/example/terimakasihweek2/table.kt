package com.example.terimakasihweek2

import Model.ayam
import Model.GlobalVar
import Model.animal
import Model.sapi
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import android.widget.Toast
import com.example.terimakasihweek2.databinding.ActivityTableBinding
import kotlin.NumberFormatException

class table : AppCompatActivity() {
    private lateinit var viewbind: ActivityTableBinding
    private var position: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewbind = ActivityTableBinding.inflate(layoutInflater)
        setContentView(viewbind.root)
        GetIntent()
        setUpListener()

    }

    private fun GetIntent(){
        if(intent.getIntExtra("status", 0) == GlobalVar.statusadd){
            viewbind.imageView2.visibility = View.INVISIBLE
        }else if(intent.getIntExtra("status", 0) == GlobalVar.statusedit){
            position = intent.getIntExtra("position", -1)
//            viewbind.textView3.visibility = View.INVISIBLE
            viewbind.inputnama.editText?.setText(GlobalVar.ListdataHewan[position].namahewan)
            viewbind.inputusia.editText?.setText(GlobalVar.ListdataHewan[position].usiahewan.toString())
            if(viewbind.radioGroup.checkedRadioButtonId == viewbind.radioButton3.id){
                "Ayam"
            }else if(viewbind.radioGroup.checkedRadioButtonId == viewbind.radioButton2.id){
                "Sapi"
            }else if(viewbind.radioGroup.checkedRadioButtonId == viewbind.radioButton.id){
                "Kambing"
            }
        }
    }

    private fun setUpListener() {
        viewbind.back.setOnClickListener {
            finish()
        }
        viewbind.submit.setOnClickListener {
            try{
                if(viewbind.radioButton3.isChecked) {
                    val hewan = ayam(
                        viewbind.inputnama.editText?.text.toString().trim(),
                        viewbind.inputusia.editText?.text.toString().trim().toInt(),
                        viewbind.radioButton3.text.toString().trim()

                    )
                    if (FormChecker(hewan)) {
                        if (intent.getIntExtra("status", 0) == GlobalVar.statusadd) {
                            GlobalVar.ListdataHewan.add(hewan)
                        } else if (intent.getIntExtra("status", 0) == GlobalVar.statusedit) {
                            GlobalVar.ListdataHewan[position] = hewan
                        }
                        Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                    }
                }else if(viewbind.radioButton2.isChecked) {
                    val hewan = sapi(
                        viewbind.inputnama.editText?.text.toString().trim(),
                        viewbind.inputusia.editText?.text.toString().trim().toInt(),
                        viewbind.radioButton2.text.toString().trim()
                    )
                    if (FormChecker(hewan)) {
                        if (intent.getIntExtra("status", 0) == GlobalVar.statusadd) {
                            GlobalVar.ListdataHewan.add(hewan)
                        } else if (intent.getIntExtra("status", 0) == GlobalVar.statusedit) {
                            GlobalVar.ListdataHewan[position] = hewan
                        }
                        Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                    }
                }
            }catch (e: NumberFormatException) {
                viewbind.inputusia.error = "Usia belom terisi"
            }
        }
    }
    private fun FormChecker(hewan:animal): Boolean {

        var isCompleted = true

        if(hewan.namahewan.isEmpty()){
            viewbind.inputnama.error = "Nama hewan belum terisi"
            isCompleted = false
        }else{
            viewbind.inputnama.error = ""
        }

        if(hewan.usiahewan == 0){
            viewbind.inputusia.error = "Umur hewan belum terisi"
            isCompleted = false
        }else{
            viewbind.inputusia.error = ""
        }

        if(hewan.jenishewan.isEmpty()){
            if (viewbind.radioButton.isChecked == false){
                viewbind.radioButton.error = "Jenis Hewan Belom terisi"
            }else if(viewbind.radioButton2.isChecked == false){
                viewbind.radioButton2.error = "Jenis hewan belom terisi"
            }else{
                viewbind.radioButton3.error = "Jenis hewan belom terisi"
            }
        }

        return isCompleted
    }
}