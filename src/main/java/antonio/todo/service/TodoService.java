package antonio.todo.service;

import antonio.todo.exception.TaskNotFoundException;
import antonio.todo.model.dto.TodoDTO;
import antonio.todo.repository.TodoRepository;
import antonio.todo.model.entity.TodoTask;
import antonio.todo.repository.specification.TodoSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Slf4j
@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List<TodoDTO> getAllTasks() {
        return repository.findAll().stream()
                .map(TodoDTO::fromEntity) // Method Reference cực gọn
                .toList();
    }
    public List<TodoDTO> searchTasks(String keyword) {
        return repository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(TodoDTO::fromEntity)
                .toList();
    }

    public Page<TodoDTO> getAllTasksAdvanced(String title, Boolean completed, Pageable pageable) {
        // Kết hợp các điều kiện: title AND completed
        Specification<TodoTask> spec = Specification
                .where(TodoSpecification.hasTitle(title))
                .and(TodoSpecification.isCompleted(completed));
//              .and(TodoSpecification.hasDueDate);
        return repository.findAll(spec, pageable).map(TodoDTO::fromEntity);
    }

    public Page<TodoDTO> getTasksPaged(int page, int size, String sortBy, String direction, Boolean completed) {
        // Kiểm tra hướng sắp xếp (ASC hoặc DESC)
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        if (completed != null) {
            return repository.findByCompleted(completed, pageable).map(TodoDTO::fromEntity);
        }
        // repository.findAll(pageable) trả về đối tượng Page chứa dữ liệu và thông tin trang
        return repository.findAll(pageable).map(TodoDTO::fromEntity);
    }

    @Transactional
    public TodoDTO saveTask(TodoDTO dto) {
        // Convert dto to entity
        TodoTask task = dto.toEntity();
        TodoTask savedTask = repository.save(task);
        // return dto to controller
        return TodoDTO.fromEntity(savedTask);
    }


    @Transactional // Đảm bảo tính toàn vẹn dữ liệu
    public TodoDTO toggleTaskStatus(Long id) {
        TodoTask task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Không tìm thấy task với ID: " + id));

        task.setCompleted(!task.isCompleted());
        return TodoDTO.fromEntity(repository.save(task));
    }

    @Transactional
    public void clearCompletedTasks() {
        repository.deleteByCompletedTrue();
    }
}
