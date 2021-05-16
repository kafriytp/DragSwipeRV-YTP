package com.eunidev.dragswipervytp

import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(var list: ArrayList<DataModel>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {
	
	inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
		val ivColor = itemView.findViewById<ImageView>(R.id.ivColor_CustomLayoutRecyclerview)
		val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle_CustomLayoutRecyclerview)
		val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription_CustomLayoutRecyclerview)
		
		fun bind(model: DataModel) {
			ivColor.setImageDrawable(ColorDrawable(model.color))
			tvTitle.text = model.title
			tvDescription.text = model.description
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_layout_recyclerview, parent, false))
	}
	
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(list[position])
	}
	
	override fun getItemCount(): Int = list.size
	
	fun removeItem(position: Int) {
		list.removeAt(position)
		notifyItemRemoved(position)
	}
	
}