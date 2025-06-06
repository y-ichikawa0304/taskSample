package com.example.taskSample;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomeRestController {
    // タスク1件だけを保持するための入れ物
    record TaskItem(String id, String task, String deadline, boolean done) {
    }

    // すべてのタスクを格納するためのリスト
    private List<TaskItem> taskItems = new ArrayList<>();

    @RequestMapping(value = "/resthello")
    String hello() {
        return " Hello. It works! 現在時刻は " + LocalDateTime.now() + "です。";
    }

    @GetMapping("/restadd")
    String addItem(@RequestParam("task") String task,
            @RequestParam("deadline") String deadline) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        TaskItem item = new TaskItem(id, task, deadline, false);
        taskItems.add(item);

        return "タスクを追加しました";
    }

    @GetMapping("/restlist")
    String listItems() {
        String result = taskItems.stream()
                .map(TaskItem::toString)
                .collect(Collectors.joining(", "));
        return result;
    }

}
