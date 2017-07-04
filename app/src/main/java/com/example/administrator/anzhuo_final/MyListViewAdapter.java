package com.example.administrator.anzhuo_final;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

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
    public MyListViewAdapter(ArrayList<NewsObject> list,Context context) {
        this.list = list;
        this.context=context;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyListViewHolder holder1=(MyListViewHolder) (holder);
        holder1.position=position;
        NewsObject newsObject=list.get(position);
        holder1.titleView.setText(newsObject.getTitle());
        String url="http://192.168.0.11:8081/AndroidSever/images/";
        url+=newsObject.getUrl();
        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        holder1.imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                holder1.imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        mQueue.add(imageRequest);
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
        public ImageView imageView;

        public MyListViewHolder(View itemView) {
            super(itemView);
            titleView=(TextView)itemView.findViewById(R.id.tv);
            imageView=(ImageView)itemView.findViewById(R.id.iv);
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
