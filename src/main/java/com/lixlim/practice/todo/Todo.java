package com.lixlim.practice.todo;

import com.lixlim.practice.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Todo extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 255)
    private String text;

}
