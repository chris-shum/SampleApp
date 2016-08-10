package app.com.example.android.sampleapp.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import app.com.example.android.sampleapp.DetailsActivity;
import app.com.example.android.sampleapp.R;
import app.com.example.android.sampleapp.data.Singleton;
import app.com.example.android.sampleapp.models.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ShowMe on 8/8/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    ArrayList<Item> mItemArrayList;
    Activity mActivity;

    public RecyclerViewAdapter(ArrayList<Item> mItemArrayList, Activity activity) {
        this.mItemArrayList = mItemArrayList;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_card, parent, false);
        ViewHolder rcv = new ViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Singleton singleton = Singleton.getInstance();
        Picasso.with(holder.productImage.getContext()).load(singleton.getmItemArrayList().get(position).getLargeImage()).placeholder(R.drawable.noimage).into(holder.productImage);
        holder.productName.setText(singleton.getmItemArrayList().get(position).getName());
        holder.productPrice.setText("$"+singleton.getmItemArrayList().get(position).getMsrp());
        holder.productPrice.setPaintFlags(holder.productPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.productSalePrice.setText("Sale Price: $"+singleton.getmItemArrayList().get(position).getSalePrice());
    }

    @Override
    public int getItemCount() {
        if (mItemArrayList.size() != 0) {
            return mItemArrayList.size();
        } else {
            return 0;
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mItemArrayList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mItemArrayList, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mItemArrayList.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.productImage)
        ImageView productImage;
        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.productPrice)
        TextView productPrice;
        @BindView(R.id.productSalePrice)
        TextView productSalePrice;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(itemView.getContext(), DetailsActivity.class);
            intent.putExtra("Position", getAdapterPosition());

            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(mActivity, Pair.create(view, "Settings")).toBundle();

            itemView.getContext().startActivity(intent, bundle);
        }
    }




}
