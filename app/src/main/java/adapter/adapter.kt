package Adapter

import Interface.Listener
import Model.GlobalVar
import Model.animal
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.terimakasihweek2.R
import com.example.terimakasihweek2.databinding.CardBinding

class adapter (val ListdataHewan: ArrayList<animal>, val listener: Listener):
    RecyclerView.Adapter<adapter.viewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card, parent, false)
        return viewHolder(view, listener)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(ListdataHewan[position], position)
    }

    override fun getItemCount(): Int {
        return ListdataHewan.size
    }

    class viewHolder(val itemView: View, val listener1: Listener): RecyclerView.ViewHolder(itemView) {

        val viewBind = CardBinding.bind(itemView)

        fun setData(hewan: animal, position: Int) {
            viewBind.namahewan.text = hewan.namahewan
            viewBind.jenishewan.text = hewan.jenishewan
            viewBind.usiahewan.text = hewan.usiahewan.toString()+" tahun"
            viewBind.edit.setOnClickListener {
                listener1.editOnclick(position)
            }
            viewBind.delete.setOnClickListener{
                listener1.deleteOnClick(position)
            }
            viewBind.suarahewan.setOnClickListener {
                listener1.onVoiceClicked(position)
            }
            viewBind.makan.setOnClickListener {
                listener1.onFeedClicked(position)
            }


        }
    }
}



