package com.example.sundaywork.service;

import com.example.sundaywork.common.Result;

public interface RecordService {
    Result<?> addRecord();
    Result<?> deleteLatestRecord();
    Result<?> updateLatestRecord();
    Result<?> getLatestRecord();
}