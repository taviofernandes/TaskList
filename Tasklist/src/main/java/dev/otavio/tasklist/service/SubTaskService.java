package dev.otavio.tasklist.service;

import dev.otavio.tasklist.model.Task;
import dev.otavio.tasklist.model.SubTask;
import dev.otavio.tasklist.repository.SubTaskRepository;
import dev.otavio.tasklist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final TaskRepository taskRepository;


    //region POST

    public SubTask createSubTask(SubTask subtask, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));
        subtask.setTask(task);
        return subTaskRepository.save(subtask);
    }

    //endregion
    //region GET
    public List<SubTask> getSubTasksById(Long taskId) {
        return subTaskRepository.findAllByTask_id(taskId);
    }
    //endregion
    //region DELETE
    public void removeSubTask(Long taskId, Long subTaskId) {
        subTaskRepository.deleteById(subTaskId);
    }
    //endregion
    //region PATCH
    public SubTask editSubTask(Long subTaskId, SubTask subtask) {

        SubTask existingSubTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubTask não encontrada"));

        if (subtask.getTitle() != null) {
            existingSubTask.setTitle(subtask.getTitle());
        }

        if (subtask.getStatus() != null) {
            existingSubTask.setStatus(subtask.getStatus());
        }

        return subTaskRepository.save(existingSubTask);

    }
    //endregion
}
