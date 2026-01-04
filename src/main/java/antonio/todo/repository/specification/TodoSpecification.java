package antonio.todo.repository.specification;

import antonio.todo.model.entity.TodoTask;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

    // Miếng Lego 1: Lọc theo tiêu đề (chứa từ khóa)
    public static Specification<TodoTask> hasTitle(String title) {
        return (root, query, cb) -> title == null ? null :
                cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    // Miếng Lego 2: Lọc theo trạng thái
    public static Specification<TodoTask> isCompleted(Boolean completed) {
        return (root, query, cb) -> completed == null ? null :
                cb.equal(root.get("completed"), completed);
    }
    // Scale sau này: dueDate, priority
//    public static Specification<TodoTask> hasDueDate(...) {...};
//    public static Specification<TodoTask> hasPriority(...) {...};

}
