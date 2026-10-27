package com.example.sundaywork.mapper;

import com.example.sundaywork.entity.Record;
import org.apache.ibatis.annotations.*;
import java.time.LocalDateTime;

@Mapper
public interface RecordMapper {

    int insert(Record record);


    Record selectLatest();


    int updateLatestTime(@Param("visitTime") LocalDateTime visitTime);


    int deleteLatest();
}