package com.lixlim.practice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Hotel {
    private Long id;
    private String name;
    private Long prefecture;
    private String address;
    private List<Object> conditions;
    private List<Object> rooms;
    private List<Object> plans;
}
