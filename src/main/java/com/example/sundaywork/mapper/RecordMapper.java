package com.example.sundaywork.mapper;

import com.example.sundaywork.entity.Record;
import org.apache.ibatis.annotations.*;
import java.time.LocalDateTime;

@Mapper
public interface RecordMapper {
    @Insert("INSERT INTO record (content, visit_time) VALUES (#{content}, #{visitTime})")
    int insert(Record record);

    @Select("SELECT * FROM record ORDER BY id DESC LIMIT 1")
    Record selectLatest();

    @Update("UPDATE record SET visit_time = #{visitTime} WHERE id = (SELECT id FROM (SELECT MAX(id) AS id FROM record) AS temp)")
    int updateLatestTime(LocalDateTime visitTime);

    @Delete("DELETE FROM record WHERE id = (SELECT id FROM (SELECT MAX(id) AS id FROM record) AS temp)")
    int deleteLatest();
}