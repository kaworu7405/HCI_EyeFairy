package com.example.eyefairy.CareFunction;

public class ListItem {

    private String surg;
    private String subg;
    private String nam;
    private String cont;

    public ListItem(){};

    public ListItem(String surgery, String subgroup, String name, String content){
        surg = surgery;
        subg = subgroup;
        nam = name;
        cont = content;
    }
    public void setSurgery(String surgery){
        surg = surgery;
    }
    public void setSubgroup(String subgroup){
        subg = subgroup;
    }
    public void setName(String name){
        nam = name;
    }
    public void setContent(String content){
        cont = content;
    }
    public String getSurgery(){
        return surg;
    }
    public String getSubgroup(){
        return subg;
    }
    public String getName(){
        return nam;
    }
    public String getContent(){
        return cont;
    }
}