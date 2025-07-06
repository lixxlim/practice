package com.lixlim.practice;

import java.util.*;

public class Hotel {
    private Long id;
    private String name;
    private Integer prefecture;
    private String address;
    private List<Object> conditions;
    private List<Object> rooms;
    private List<Object> plans;

    public Hotel() {}

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public Integer getPrefecture() { return this.prefecture; }
    public void setPrefecture(Integer prefecture) { this.prefecture = prefecture; }

    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }

    public List<Object> getConditions() { return this.conditions; }
    public void setConditions(List<Object> conditions) { this.conditions = conditions; }

    public List<Object> getRooms() { return this.rooms; }
    public void setRooms(List<Object> rooms) { this.rooms = rooms; }

    public List<Object> getPlans() { return this.plans; }
    public void setPlans(List<Object> plans) { this.plans = plans; }

}