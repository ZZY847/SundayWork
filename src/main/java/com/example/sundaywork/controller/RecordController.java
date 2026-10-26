package com.example.sundaywork.controller;

import com.example.sundaywork.common.Result;
import com.example.sundaywork.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/add")
    public Result<?> add() {
        return recordService.addRecord();
    }

    @GetMapping("/reduce")
    public Result<?> reduce() {
        return recordService.deleteLatestRecord();
    }

    @GetMapping("/update")
    public Result<?> update() {
        return recordService.updateLatestRecord();
    }

    @GetMapping("/select")
    public Result<?> select() {
        return recordService.getLatestRecord();
    }
}