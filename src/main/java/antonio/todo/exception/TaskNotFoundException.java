package antonio.todo.exception;

// Chuyển sang RuntimeException để không cần khai báo 'throws' khắp nơi
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
