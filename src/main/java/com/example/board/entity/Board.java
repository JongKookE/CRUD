package com.example.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Min(2)
    private String title;
    
    @NotNull
    @Min(2)
    private String content;
    
    private String filename;
    private String filepath;

    @Column(name = "view_count", columnDefinition = "int default 0")
    private int viewCount;
}
