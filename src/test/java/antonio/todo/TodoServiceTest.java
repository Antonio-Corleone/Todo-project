package antonio.todo;

import antonio.todo.model.dto.TodoDTO;
import antonio.todo.model.entity.TodoTask;
import antonio.todo.repository.TodoRepository;
import antonio.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository repository; // Giả lập Repository

    @InjectMocks
    private TodoService todoService; // Tiêm Mock vào Service

    private TodoTask sampleTask;

    @BeforeEach
    void setUp() {
        sampleTask = new TodoTask(1L, "Test Task", false);
    }

    @Test
    void testSaveTask_Success() {
        // Given (Giả lập hành vi của Repo)
        TodoDTO dto = new TodoDTO(null, "Test Task", false);
        when(repository.save(any(TodoTask.class))).thenReturn(sampleTask);

        // When (Thực thi hàm cần test)
        TodoDTO result = todoService.saveTask(dto);

        // Then (Kiểm tra kết quả)
        assertNotNull(result);
        assertEquals("Test Task", result.title());
        verify(repository, times(1)).save(any(TodoTask.class));
    }
}