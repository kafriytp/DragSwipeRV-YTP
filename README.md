Make Recyclerview Dragable and Swipeable

```kotlin
class RVTouchHelper(private val dragable: Boolean, private val listener: RVTouchHelperListener): ItemTouchHelper.Callback() {
	
	interface RVTouchHelperListener {
		fun onMoved(oldPosition: Int, newPosition: Int)
		
		fun onSwipe(viewHolder: RecyclerView.ViewHolder, position: Int)
		
		fun onClear(viewHolder: RecyclerView.ViewHolder)
		
	}
	
	override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
		if (dragable) {
			val flags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
			return makeMovementFlags(flags, 0)
		}
		
		val flags = ItemTouchHelper.START or ItemTouchHelper.END
		return makeMovementFlags(0, flags)
	}
	
	override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
		listener.onMoved(viewHolder.adapterPosition, target.adapterPosition)
		
		return true
	}
	
	override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
		listener.onSwipe(viewHolder, viewHolder.adapterPosition)
	}
	
	override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			getDefaultUIUtil().onSelected(viewHolder?.itemView)
		}
	}
	
	override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
		listener.onClear(viewHolder)
		getDefaultUIUtil().clearView(viewHolder.itemView)
	}
}
```
