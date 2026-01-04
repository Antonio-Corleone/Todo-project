package antonio.todo.repository;

import antonio.todo.model.entity.TodoTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JpaRepository<TodoTask, Long> báo cho Spring biết:
 * "Tôi muốn làm việc với thực thể TodoTask và kiểu dữ liệu của ID là Long"
 * */
@Repository
public interface TodoRepository extends JpaRepository<TodoTask, Long>, JpaSpecificationExecutor<TodoTask> {
    // extends JpaSpecificationExecutor Bây giờ repository đã có thêm hàm: findAll(Specification<T> spec, Pageable pageable)
    // Tìm kiếm các task mà tiêu đề chứa từ khóa (không phân biệt hoa thường)
    List<TodoTask> findByTitleContainingIgnoreCase(String keyword);

    @Modifying
    @Transactional
    void deleteByCompletedTrue(); // Spring Data JPA tự hiểu logic này và tạo câu lệnh DELETE SQL

    // Tìm kiếm có phân trang và lọc theo trạng thái completed
    // Nếu tham số completed là null, Spring JPA sẽ không biết xử lý nên ta dùng query đơn giản này
    Page<TodoTask> findByCompleted(Boolean completed, Pageable pageable);
}
