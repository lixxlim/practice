package com.lixlim.practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Hotel {
    private Long id;
    private String name;
    private Long prefecture;
    private String address;
    private List<String> conditions;
    private List<Room> rooms;
    private List<List<Plan>> plans;
}
