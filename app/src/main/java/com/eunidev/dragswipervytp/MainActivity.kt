package com.eunidev.dragswipervytp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eunidev.dragswipervytp.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	
	private lateinit var rvAdapter: RVAdapter
	
	private val list = populateData()
	private var itemTouchHelper: ItemTouchHelper? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		val rvTouchHelperDragCallback = RVTouchHelper(true, object : RVTouchHelper.RVTouchHelperListener {
			override fun onMoved(oldPosition: Int, newPosition: Int) {
				Collections.swap(list, oldPosition, newPosition)
				rvAdapter.notifyItemMoved(oldPosition, newPosition)
			}
			
			override fun onSwipe(viewHolder: RecyclerView.ViewHolder, position: Int) {}
			
			override fun onClear(viewHolder: RecyclerView.ViewHolder) {}
		})
		
		itemTouchHelper = ItemTouchHelper(rvTouchHelperDragCallback)
		
		with(binding.rvColorMainActivity) {
			rvAdapter = RVAdapter(list)
			layoutManager = LinearLayoutManager(this@MainActivity)
			adapter = rvAdapter
			itemTouchHelper?.attachToRecyclerView(this)
		}
		
	}
	
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu_mode, menu)
		
		return true
	}
	
	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.drag_MenuMode -> {
				val rvTouchHelperDragCallback = RVTouchHelper(true, object : RVTouchHelper.RVTouchHelperListener {
					override fun onMoved(oldPosition: Int, newPosition: Int) {
						Collections.swap(list, oldPosition, newPosition)
						rvAdapter.notifyItemMoved(oldPosition, newPosition)
					}
					
					override fun onSwipe(viewHolder: RecyclerView.ViewHolder, position: Int) {}
					
					override fun onClear(viewHolder: RecyclerView.ViewHolder) {}
				})
				
				itemTouchHelper = null
				itemTouchHelper = ItemTouchHelper(rvTouchHelperDragCallback)
				itemTouchHelper?.attachToRecyclerView(binding.rvColorMainActivity)
			}
			
			R.id.swipe_MenuMode -> {
				val rvTouchHelperSwipeCallback = RVTouchHelper(false, object : RVTouchHelper.RVTouchHelperListener {
					override fun onMoved(oldPosition: Int, newPosition: Int) {}
					
					override fun onSwipe(viewHolder: RecyclerView.ViewHolder, position: Int) {
						rvAdapter.removeItem(viewHolder.adapterPosition)
					}
					
					override fun onClear(viewHolder: RecyclerView.ViewHolder) {}
				})
				
				itemTouchHelper = null
				itemTouchHelper = ItemTouchHelper(rvTouchHelperSwipeCallback)
				itemTouchHelper?.attachToRecyclerView(binding.rvColorMainActivity)
			}
		}
		
		return true
	}
	
	private fun populateData(): ArrayList<DataModel> {
		val list = ArrayList<DataModel>()
		list.add(DataModel("Blue 50", "#E1F5FE", Color.parseColor("#E1F5FE")))
		list.add(DataModel("Blue 100", "#B3E5FC", Color.parseColor("#B3E5FC")))
		list.add(DataModel("Blue 200", "#81D4FA", Color.parseColor("#81D4FA")))
		list.add(DataModel("Blue 300", "#4FC3F7", Color.parseColor("#4FC3F7")))
		list.add(DataModel("Blue 400", "#29B6F6", Color.parseColor("#29B6F6")))
		list.add(DataModel("Blue 500", "#03A9F4", Color.parseColor("#03A9F4")))
		list.add(DataModel("Blue 600", "#039BE5", Color.parseColor("#039BE5")))
		list.add(DataModel("Blue 700", "#0288D1", Color.parseColor("#0288D1")))
		list.add(DataModel("Blue 800", "#0277BD", Color.parseColor("#0277BD")))
		list.add(DataModel("Blue 900", "#01579B", Color.parseColor("#01579B")))
		
		return list
	}
}