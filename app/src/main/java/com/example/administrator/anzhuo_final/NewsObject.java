package com.example.administrator.anzhuo_final;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/10.
 */
public class NewsObject implements Serializable {
    private String Title;
    private String DateTime;
    private int Id;
    private String Content;
    private int Classid;
    private String Url;
    private String ViUrl;
    //private List<>
    public NewsObject(int id,String title,String dateTime,String content,int classid,String url,String viUrl)
    {
        this.Id=id;
        this.Title=title;
        this.DateTime=dateTime;
        this.Content=content;
        this.Classid=classid;
        this.Url=url;
        this.ViUrl=viUrl;
    }
    public NewsObject(String title,String dateTime)
    {
        this.Title=title;
        this.DateTime=dateTime;

    }
  public String getTitle()
    {
        return  Title;
    }
    public  String getDateTime()
    {
        return  DateTime;
    }
    public void setTitle(String title)
    {
        this.Title=title;
    }
    public void setDateTime(String dateTime)
    {
        this.DateTime=dateTime;
    }
    public String getUrl(){return Url;}
    public String getViUrl(){return ViUrl;}
    public String getContent() {
        return Content;
    }

    public int getId() {
        return Id;
    }

    public int getClassid() {
        return Classid;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public void setClassid(int classid) {
        this.Classid = classid;
    }
}
