package dev.otavio.tasklist.controller;

import dev.otavio.tasklist.model.Task;
import dev.otavio.tasklist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasklist")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    //region METODOS TASK

    //region GET
    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    //endregion
    //region ADICIONAR
    @PostMapping
    public Task postTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    //endregion
    //region REMOVER

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    //endregion
    //region EDITAR
    @PatchMapping("/{id}")
    public Task editTask(@RequestBody Task task, @PathVariable Long id) {
        return taskService.patchTask(task, id);
    }
    //endregion

    //endregion


}
