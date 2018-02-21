package com.nerd.app.voisy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nerd.app.voisy.R;
import com.nerd.app.voisy.model.Result;
import com.nerd.app.voisy.model.Sense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 4/21/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    private ArrayList<Sense>  resultList;
    private Context context;

    public ResultAdapter(ArrayList<Sense> resultList, Context context){
        this.resultList = resultList;
        this.context = context;
    }
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ResultAdapter.ViewHolder holder, int position) {
        holder.textView.setText(resultList.get(position).getDefinition()[0]);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    // Add a new item to the RecyclerView on a predefined position
    public void addItems(int position, ArrayList<Sense> data) {
        resultList.add(position, data.get(position));
        notifyItemInserted(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
        }
    }
}
