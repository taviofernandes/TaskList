package dev.otavio.tasklist.service;

import dev.otavio.tasklist.model.Task;
import dev.otavio.tasklist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;


    //region METODOS

    //region GET
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    //endregion
    //region POST
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    //endregion
    //region DELETE
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    //endregion
    //region PATCH

    public Task patchTask(Task task, Long id) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada"));

        if (task.getTitle() != null) {
            existingTask.setTitle(task.getTitle());
        }
        if (task.getConclusion() != null) {
            existingTask.setConclusion(task.getConclusion());
        }
        if (task.getStatus() != null) {
            existingTask.setStatus(task.getStatus());
        }

        return taskRepository.save(existingTask);
    }

    //endregion

    //endregion
}
