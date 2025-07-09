package com.lixlim.practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Room {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer count;
    private List<String> conditions;
}
