package antonio.todo.controller;

import antonio.todo.model.dto.TodoDTO;
import antonio.todo.model.entity.TodoTask;
import antonio.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Đánh dấu lớp này xử lý API
@RequestMapping("/api/tasks")
@Tag(name = "Todo Management", description = "Các API quản lý danh sách công việc")
public class TodoController {
    private final TodoService todoService; // Inject Service thay vì Repository
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @Operation(summary = "Lấy danh sách tất cả các task", description = "Trả về một danh sách các TodoDTO hiện có trong hệ thống")
    @GetMapping
    public List<TodoDTO> getTasks() {
        return todoService.getAllTasks();
    }
    // Để Spring thực hiện kiểm tra,
    // bắt buộc phải thêm Annotation @Valid trước tham số nhận dữ liệu ở Controller
    @Operation(summary = "Tạo task mới", description = "Yêu cầu title tối thiểu 3 ký tự. Trả về task vừa tạo kèm ID")
    @PostMapping
    public TodoDTO add(@Valid @RequestBody TodoDTO dto) { // Nhận DTO để Validation có tác dụng
        return todoService.saveTask(dto);
    }

    @Operation(summary = "Chuyển trạng thái của task")
    @PutMapping("/{id}")
    public TodoDTO toggle(@PathVariable Long id) {
        return todoService.toggleTaskStatus(id);
    }
    @Operation(summary = "Xoá task đã completed")
    @DeleteMapping("/completed")
    public void clear() {
        todoService.clearCompletedTasks();
    }

    @Operation(summary = "Tìm kiếm task theo tiêu đề")
    @GetMapping("/search")
    public List<TodoDTO> search(@RequestParam String keyword) {
        return todoService.searchTasks(keyword);
    }

    @Operation(summary = "Lấy danh sách task có phân trang")
    @GetMapping("/paged")
    public Page<TodoDTO> getTasksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(required = false) Boolean completed) {
        return todoService.getTasksPaged(page, size, sortBy, direction, completed);
    }

    @Operation(summary = "Tìm kiếm nâng cao: Kết hợp nhiều bộ lọc cùng lúc")
    @GetMapping("/search-advanced")
    public Page<TodoDTO> searchAdvanced(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean completed,
            @ParameterObject Pageable pageable) { // @ParameterObject giúp Swagger hiển thị đủ page, size, sort
        return todoService.getAllTasksAdvanced(title, completed, pageable);
    }
}
