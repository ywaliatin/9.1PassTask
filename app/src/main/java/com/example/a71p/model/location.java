package com.example.a71p.model;

public class location {
    private int location_id;
    private String location;
    private String latlong;

    public location(String location, String latlong) {
        this.location = location;
        this.latlong = latlong;
    }



    public int getUser_id(){
        return location_id;
    }
    public String getLocation(){
        return location;
    }
    public String getLatlong(){
        return latlong;
    }


    public void setUser_id(int user_id){this.location_id = user_id;}
    public void setLocation(String location){
        this.location = location;
    }
    public void setLatlong(String latlong){
        this.latlong = latlong;
    }


}
