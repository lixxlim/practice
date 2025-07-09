package com.lixlim.practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Plan {
    private Long id;
    private String name;
    private Integer room_id;
    private Long price;
    private List<String> conditions;
}
