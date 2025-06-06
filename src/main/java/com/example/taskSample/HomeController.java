package com.example.taskSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    record TaskItem(String id, String task, String deadline, boolean done) {
    }

    // recordで定義した型でリストを作成
    private List<TaskItem> taskItems = new ArrayList<>();
    private final TaskListDao dao;

    @Autowired
    HomeController(TaskListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/hello")
    String hello(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        return "hello";
    }

    @GetMapping("/list")
    String listItems(Model model) {
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("taskList", taskItems);
        return "home";
    }

    @GetMapping("/add")
    String addItem(@RequestParam("task") String task,
            @RequestParam("deadline") String deadline) { // htmlで入力した値をrequestParamで持ってきて、String 〇〇にそれぞれ格納
        String id = UUID.randomUUID().toString().substring(0, 8);
        // TaskItem型のitemインスタンスを作成し引数を入れている（コンストラクタ）
        TaskItem item = new TaskItem(id, task, deadline, false);
        dao.add(item);
        return "redirect:/list";
    }

    @GetMapping("/delete")
    String deleteItem(@RequestParam("id") String id) {
        dao.delete(id);
        return "redirect:/list";
    }

    @GetMapping("/update")
    String updateItem(@RequestParam("id") String id,
            @RequestParam("task") String task,
            @RequestParam("deadline") String deadline,
            @RequestParam("done") boolean done) {
        TaskItem taskItem = new TaskItem(id, task, deadline, done);
        dao.update(taskItem);
        return "redirect:/list";
    }
}
