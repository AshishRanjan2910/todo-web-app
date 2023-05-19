package com.todoproject.springboot.todowebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping(value = "todos", method = RequestMethod.GET)
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("Udemy");
        model.addAttribute("todos", todos);
        return "todos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model) {
        String username = (String)model.get("name");
        Todo newTodo = new Todo(0, username, "",
                LocalDate.now().plusYears(1), false);
        model.addAttribute("newTodo", newTodo);
        return "todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, Todo newTodo) {
        String username = (String)model.get("name");
        todoService.add(username, newTodo.getDescription(),
                LocalDate.now().plusYears(1), false);
        return "redirect:todos";
    }

}