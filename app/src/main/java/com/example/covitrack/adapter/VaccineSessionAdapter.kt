package com.example.covitrack.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covitrack.R
import com.example.covitrack.model.SessionsItem

class VaccineSessionAdapter(val context:Context): RecyclerView.Adapter<VaccineSessionAdapter.SessionViewholder>() {

    private var sessions = ArrayList<SessionsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewholder {
        return SessionViewholder(LayoutInflater.from(context).inflate(R.layout.vaccine_session_card,parent,false))
    }

    override fun onBindViewHolder(holder: SessionViewholder, position: Int) {
        val session = sessions[position]
        holder.name.text = session.name
        holder.state_name.text = session.stateName
        if(session.minAgeLimit == 18) holder.age.text = "${session.minAgeLimit}+"
        else holder.age.text = session.minAgeLimit.toString()
        holder.fee_type.text = session.feeType
        holder.vaccine.text = session.vaccine

        if(session.availableCapacity!! < 20){
            holder.doses.backgroundTintList = context.getColorStateList(R.color.danger)
        }
        else if(session.availableCapacity in 21..69){
            holder.doses.backgroundTintList = context.getColorStateList(R.color.yellow)
        }
        else{
            holder.doses.backgroundTintList = context.getColorStateList(R.color.success)
        }
        holder.doses.text = session.availableCapacity.toString()
    }

    override fun getItemCount(): Int {
        return sessions.size
    }

    fun updateList(list:List<SessionsItem>){
        sessions.clear()
        sessions.addAll(list)
        notifyDataSetChanged()
    }

    inner class SessionViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val state_name = itemView.findViewById<TextView>(R.id.state_name)
        val vaccine = itemView.findViewById<TextView>(R.id.vaccine)
        val fee_type = itemView.findViewById<TextView>(R.id.fee_type)
        val age = itemView.findViewById<TextView>(R.id.age)
        val doses = itemView.findViewById<TextView>(R.id.doses)
    }
}