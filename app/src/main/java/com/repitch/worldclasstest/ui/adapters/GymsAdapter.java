package com.repitch.worldclasstest.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.repitch.worldclasstest.R;
import com.repitch.worldclasstest.network.models.Gym;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by repitch on 23.05.16.
 */
public class GymsAdapter extends RecyclerView.Adapter<GymsAdapter.ViewHolder>  {

    public interface OnItemClickListener {
        void onItemClick(Gym gym);
    }

    private List<Gym> mGyms = new ArrayList<>();
    private OnItemClickListener mItemListener;

    public GymsAdapter(List<Gym> gyms, OnItemClickListener itemListener) {
        mGyms = gyms;
        mItemListener = itemListener;
    }

    public void swap(List gyms) {
        if (mGyms != null) {
            gyms.clear();
            if (gyms != null) {
                mGyms.addAll(gyms);
            }
        } else {
            mGyms = gyms;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gym, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Gym gym = mGyms.get(position);
        holder.txtName.setText(gym.name);
        holder.txtDivision.setText(gym.division);
        holder.frameColor.setBackgroundResource(gym.getColorResId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onItemClick(gym);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGyms == null ? 0 : mGyms.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName, txtDivision;
        public FrameLayout frameColor;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtDivision = (TextView) itemView.findViewById(R.id.txt_division);
            frameColor = (FrameLayout) itemView.findViewById(R.id.frame_color);
        }
    }
}
