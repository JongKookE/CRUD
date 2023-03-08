package com.example.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, message = "두 글자 이상 입력해주세요")
    private String title;
    
    @NotNull
    @Size(min = 2, message = "두 글자 이상 입력해주세요")
    private String content;
    
    private String filename;
    private String filepath;

    @Column(name = "view_count", columnDefinition = "int default 0")
    private int viewCount;
}
