package antonio.todo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // Đánh dấu đây là một bảng trong Database
@Data   // Lombok tự tạo Getter, Setter, toString...
@NoArgsConstructor // Constructor không đối số (JPA cần cái này)
@AllArgsConstructor // Constructor đầy đủ đối số
public class TodoTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id;

    private String title;
    private boolean completed;
}