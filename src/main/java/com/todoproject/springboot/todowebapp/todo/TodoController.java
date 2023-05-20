package com.todoproject.springboot.todowebapp.todo;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("todos")
    public String listAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("Udemy");
        model.addAttribute("todos", todos);
        return "todos";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model) {
        String username = (String)model.get("name");
        Todo todo = new Todo(0, username, "",
                LocalDate.now().plusYears(1), false);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value="add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String)model.get("name");
        todoService.add(username, todo.getDescription(),
                LocalDate.now().plusYears(1), false);
        return "redirect:todos";
    }

}