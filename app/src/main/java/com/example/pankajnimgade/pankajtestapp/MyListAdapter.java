package com.example.pankajnimgade.pankajtestapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pankaj.Nimgade on 17-10-2016.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private List<MyItem> myItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyListAdapter(Context context, List<MyItem> myItems) {
        this.context = context;
        this.myItems = myItems;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_item_for_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MyItem myItem = this.myItems.get(position);
        holder.name.setText("" + myItem.getName());
        holder.metaData.setText("" + myItem.getMetadata());
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView metaData;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.Single_item_for_list_name_textView);
            metaData = (TextView) itemView.findViewById(R.id.Single_item_for_list_metadata_textView);
        }
    }
}
