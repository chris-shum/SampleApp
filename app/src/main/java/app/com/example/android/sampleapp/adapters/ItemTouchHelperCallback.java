package app.com.example.android.sampleapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import app.com.example.android.sampleapp.data.Singleton;

/**
 * Created by ShowMe on 7/27/16.
 */
public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapter mAdapter;
    RecyclerViewAdapter recyclerViewAdapter;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter, RecyclerViewAdapter recyclerViewAdapter) {
        this.mAdapter = mAdapter;
        this.recyclerViewAdapter = recyclerViewAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Singleton singleton = Singleton.getInstance();
        if (direction == ItemTouchHelper.START || direction == ItemTouchHelper.END) {
            singleton.getmItemArrayList().remove(viewHolder.getAdapterPosition());
            recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            recyclerViewAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), singleton.getmItemArrayList().size());
        }
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


}
