package com.example.sundaywork.service.impl;

import com.example.sundaywork.common.Result;
import com.example.sundaywork.entity.Record;
import com.example.sundaywork.mapper.RecordMapper;
import com.example.sundaywork.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Result<?> addRecord() {
        Record record = new Record();
        record.setContent("这是一条企业级记录");
        record.setVisitTime(LocalDateTime.now());
        recordMapper.insert(record);
        return Result.success("add 成功", null);
    }

    @Override
    public Result<?> deleteLatestRecord() {
        int rows = recordMapper.deleteLatest();
        if (rows > 0) return Result.success("reduce 成功", null);
        return Result.error("没有记录可删");
    }

    @Override
    public Result<?> updateLatestRecord() {
        int rows = recordMapper.updateLatestTime(LocalDateTime.now());
        if (rows > 0) return Result.success("update 成功", null);
        return Result.error("没有记录可更新");
    }

    @Override
    public Result<?> getLatestRecord() {
        Record record = recordMapper.selectLatest();
        if (record == null) return Result.error("暂无数据");
        return Result.success("查询成功", record);
    }
}