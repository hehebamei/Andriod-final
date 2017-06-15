package com.example.administrator.anzhuo_final;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2017/6/10.
 */
public class MyListViewAdapter extends RecyclerView.Adapter {
    private Context context;

    private ArrayList<NewsObject> list;//;=new ArrayList<NewsObject>();
    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
        boolean onItemLongClick(int position);
    }
    private OnRecyclerViewListener onRecyclerViewListener;
    public  void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    private static final String TAG = MyListViewAdapter.class.getSimpleName();
    public MyListViewAdapter(ArrayList<NewsObject> list) {
        this.list = list;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyListViewHolder holder1=(MyListViewHolder) (holder);
        holder1.position=position;
        NewsObject newsObject=list.get(position);
        holder1.titleView.setText(newsObject.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
       // return 2;
    }
    class MyListViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        public View rView;
        public TextView titleView;
        public int position;

        public MyListViewHolder(View itemView) {
            super(itemView);
            titleView=(TextView)itemView.findViewById(R.id.tv);
            rView=itemView.findViewById(R.id.recycler_iteam);
            rView.setOnClickListener(this);
            rView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(null!=onRecyclerViewListener)
            onRecyclerViewListener.onItemClick(position);
        }

        @Override
        public boolean onLongClick(View v) {
            if(null!=onRecyclerViewListener)
                onRecyclerViewListener.onItemLongClick(position);
            return false;
        }
    }
}
