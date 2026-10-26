package com.example.sundaywork.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Record {
    private Integer id;
    private String content;
    private LocalDateTime visitTime;
    private LocalDateTime createTime;
}
