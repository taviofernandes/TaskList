package dev.otavio.tasklist.controller;

import dev.otavio.tasklist.model.SubTask;
import dev.otavio.tasklist.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subtasks")
@RequiredArgsConstructor
public class SubTaskController {

    private final SubTaskService subTaskService;

    //region POST
    @PostMapping("/{taskId}")
    public SubTask postSubTask(@RequestBody SubTask subtask, @PathVariable Long taskId) {
        return subTaskService.createSubTask(subtask, taskId);
    }

    //endregion
    //region GET
    @GetMapping("/{taskId}")
    public List<SubTask> getSubTasks(@PathVariable Long taskId) {
        return subTaskService.getSubTasksById(taskId);
    }

    //endregion
    //region DELETE
    @DeleteMapping("/{taskId}/{subTaskId}")
    public void deleteSubTask(@PathVariable Long taskId, @PathVariable Long subTaskId) {
        subTaskService.removeSubTask(taskId, subTaskId);
    }

    //endregion
    //region PATCH
    @PatchMapping("/{subTaskId}")
    public SubTask patchSubTask(@PathVariable Long subTaskId, @RequestBody SubTask subtask) {
        return subTaskService.editSubTask(subTaskId, subtask);
    }
    //endregion
}
