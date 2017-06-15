package com.example.administrator.anzhuo_final;

import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public class NewsList {
    private List<NewsObject> news_list;
    public NewsList(){}
    public NewsList(List<NewsObject> news_list)
    {
        this.news_list=news_list;
    }

    public List<NewsObject> getNews_list() {
        return news_list;
    }
    public void setNews_list(List<NewsObject> news_list)
    {
        this.news_list=news_list;
    }
}
