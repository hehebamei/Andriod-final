package com.example.administrator.anzhuo_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/9.
 */
public class TabFragment4 extends Fragment{
    protected NewsList resp;
    private View view;
    private NewsObject news;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyListViewAdapter myAdapter;
    protected ArrayList<NewsObject> list;
    public TabFragment4(){

    }
    private void showFragment()
    {

    }
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if(null!=view)
        {
            ViewGroup parent=(ViewGroup)view.getParent();
            if(null!=parent)
            {
                parent.removeView(view);
            }
        }
        else {
            view=inflater.inflate(R.layout.fragment_4, container, false);
            initView();
        }
        //removeChildFragment(getParentFragment());

        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        //initView();
    }

    private void initView() {
        //news=new NewsObject(1,"4564","11","www",2);
        //list=new ArrayList<NewsObject>();
        //news=new NewsObject("qqweqe","qeqwe");
        //list.add(news);
        initDate();
        //Log.e("tab", String.valueOf(list));
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyckerView=(RecyclerView)view.findViewById(R.id.f4);
        recyckerView.setLayoutManager(layoutManager);
        recyckerView.setHasFixedSize(true);

        myAdapter=new MyListViewAdapter(list,getContext());
        recyckerView.setAdapter(myAdapter);
        myAdapter.setOnRecyclerViewListener(new MyListViewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getActivity(),NewsContent.class);
                intent.putExtra("Content",list.get(position).getContent());
                intent.putExtra("Title",list.get(position).getTitle());
                intent.putExtra("VideoUrl",list.get(position).getViUrl());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position) {
                return false;
            }
        });

    }
  /*  public void removeChildFragment(Fragment parentFragment) {
        FragmentManager fragmentManager = parentFragment.getChildFragmentManager();
        Fragment child = fragmentManager.findFragmentById(R.id.f1);
        if (child != null) {
            fragmentManager.beginTransaction()
                    .remove(child)
                    .commitAllowingStateLoss();
        }
    }*/
    /* public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz)
     {
         Type type = new TypeToken<ArrayList<JsonObject>>()
         {}.getType();
         ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);

         ArrayList<T> arrayList = new ArrayList<>();
         for (JsonObject jsonObject : jsonObjects)
         {
             arrayList.add(new Gson().fromJson(jsonObject, clazz));
         }
         return arrayList;
     }*/
    private void initDate()
    {
        /*String jsonData = "";
        String url="http://192.168.0.114:8081/AndroidSever/getnews.php";
        Gson gson=new Gson();
        Type type = (Type) new TypeToken<List<NewsObject>>() {}.getType();
        list=gson.fromJson(jsonData,type);*/
        list=new ArrayList<NewsObject>();
        RequestQueue mQueue= Volley.newRequestQueue(getContext());

        // List<NewsObject> ls=new ArrayList<NewsObject>();
        String url="http://192.168.0.11:8081/AndroidSever/getnews3.php";
        StringRequest request = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //Log.e("tab", s);
                // Gson gson=new Gson();
                //list=jsonToArrayList(s, NewsObject.class);

                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("Id");
                        //Log.e("tab", id + "");
                        String title=jsonObject.getString("Title");
                       // Log.e("tab", title);
                        String datatime=jsonObject.getString("DateTime");
                       // Log.e("tab", datatime);
                        String content=jsonObject.getString("Content");
                        int classid=jsonObject.getInt("Classid");
                        String url=jsonObject.getString("ImageUrl");
                        String vurl=jsonObject.getString("VideoUrl");
                        news=new NewsObject(id,title,datatime,content,classid,url,vurl);
                        list.add(news);

                        //Log.e("tab", String.valueOf(list));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // resp=gson.fromJson(s,NewsList.class);
                //list=resp.getNews_list();
                //Type type = (Type) new TypeToken<List<NewsObject>>() {}.getType();
                //list=gson.fromJson(s,type);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Toast.makeText(getActivity(),"失败",Toast.LENGTH_SHORT).show();
                Log.e("TAG", error.getMessage(), error);
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("id", "4");
                return map;
            }
        };
        mQueue.add(request);




        /*JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });*/
    }

}
