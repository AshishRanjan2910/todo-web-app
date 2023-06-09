package com.todoproject.springboot.todowebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

//import org.springframework.stereotype.Service;

//Commenting "@Service" for getting no attention from spring boot
// shifting the logic to data-jpa inbuilt logic (db used: mysql)
//@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;

    static {
        todos.add(new Todo(++todosCount, "Coursera","Learn React",
                LocalDate.now().plusYears(1), false ));
        todos.add(new Todo(++todosCount, "Coursera","Learn Spring",
                LocalDate.now().plusYears(2), false ));
        todos.add(new Todo(++todosCount, "Udemy","Learn Full Stack Development",
                LocalDate.now().plusYears(3), false ));
    }

    public void add(String username, String desc, LocalDate targetDate, boolean isDone){
        todos.add(new Todo(++todosCount, username, desc, targetDate, isDone));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
}
