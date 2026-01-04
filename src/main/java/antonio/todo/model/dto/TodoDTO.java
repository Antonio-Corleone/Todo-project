package antonio.todo.model.dto;

import antonio.todo.model.entity.TodoTask;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Sử dụng Record để code cực kỳ ngắn gọn
public record TodoDTO(
//        @Schema(description = "ID của task", example = "1")
        Long id,
        // @NotBlank: Đảm bảo chuỗi không null và không chỉ chứa khoảng trắng.
        @NotBlank(message = "Tiêu đề không được để trống")
        // @Size: Giới hạn độ dài để tránh làm tràn bộ nhớ hoặc lỗi database.
        @Size(min = 3, max = 100, message = "Tiêu đề phải từ 3 đến 100 ký tự")
//        @Schema(description = "Tiêu đề công việc", example = "Học Java")
        String title,
//        @Schema(description = "Trạng thái hoàn thành")
        boolean isCompleted) {
    // Chúng ta có thể thêm một static method để chuyển đổi từ Entity sang DTO
    public static TodoDTO fromEntity(TodoTask task) {
        return new TodoDTO(task.getId(), task.getTitle(), task.isCompleted());
    }
    // Thêm hàm này để chuyển ngược lại Entity
    public TodoTask toEntity() {
        TodoTask task = new TodoTask();
        task.setTitle(this.title);
        task.setCompleted(this.isCompleted);
        return task;
    }
}
