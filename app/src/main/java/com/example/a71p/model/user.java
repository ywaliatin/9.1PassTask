package com.example.a71p.model;

public class user {
    private int user_id;
    private String post_type;
    private String name;
    private String phonenumber;
    private String item_title;
    private String description;
    private String date;
    private String location;

    public user(String post_type, String name, String phonenumber, String item_title, String description, String date, String location) {
        this.post_type = post_type;
        this.name =name;
        this.phonenumber = phonenumber;
        this.item_title = item_title;
        this.description = description;
        this.date = date;
        this.location = location;
    }



    public int getUser_id(){
        return user_id;
    }
    public String getPost_type(){
        return post_type;
    }
    public String getName(){
        return name;
    }
    public String getPhonenumber(){
        return phonenumber;
    }
    public String getItem_title(){
        return item_title;
    }
    public String getDescription(){
        return description;
    }
    public String getDate(){
        return date;
    }
    public String getLocation(){
        return location;
    }


    public void setUser_id(int user_id){this.user_id = user_id;}
    public void setPost_type(String post_type){
        this.post_type = post_type;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }
    public void setItem_title(String item_title){
        this.item_title = item_title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setLocation(String location){
        this.location = location;
    }

}
