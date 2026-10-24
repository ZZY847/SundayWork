package com.example.sundaywork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/add")
    public String add() {
        String sql = "INSERT INTO record (content, visit_time) VALUES (?, ?)";
        jdbcTemplate.update(sql, "新记录", LocalDateTime.now());
        return "add 成功，已插入一条记录";
    }

    @GetMapping("/reduce")
    public String reduce() {
        String sql = "DELETE FROM record WHERE id = (SELECT id FROM (SELECT MAX(id) AS id FROM record) AS temp)";
        int rows = jdbcTemplate.update(sql);
        return rows > 0 ? "reduce 成功，已删除最新记录" : "reduce 失败：没有记录";
    }

    @GetMapping("/update")
    public String update() {
        String sql = "UPDATE record SET visit_time = ? WHERE id = (SELECT id FROM (SELECT MAX(id) AS id FROM record) AS temp)";
        int rows = jdbcTemplate.update(sql, LocalDateTime.now());
        return rows > 0 ? "update 成功，已更新时间" : "update 失败：没有记录";
    }

    @GetMapping("/select")
    public String select() {
        String sql = "SELECT * FROM record ORDER BY id DESC LIMIT 1";
        List<Record> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Record r = new Record();
            r.setId(rs.getInt("id"));
            r.setContent(rs.getString("content"));
            r.setVisitTime(rs.getTimestamp("visit_time").toLocalDateTime());
            r.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            return r;
        });

        if (list.isEmpty()) return "暂无数据";
        Record latest = list.get(0);
        return "最新数据 → id: " + latest.getId() + ", 内容: " + latest.getContent() + ", 访问时间: " + latest.getVisitTime();
    }
}