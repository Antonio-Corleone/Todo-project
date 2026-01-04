package antonio.todo;

import antonio.todo.controller.TodoController;
import antonio.todo.model.dto.TodoDTO;
import antonio.todo.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest(TodoController.class) // Chỉ load tầng Web
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Thay thế cho @MockBean trong Spring Boot 3.4+
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void add_InvalidTitle_ShouldReturn400() throws Exception {
        // Tạo một DTO sai quy tắc (title chỉ có 1 ký tự)
        TodoDTO invalidDto = new TodoDTO(null, "H", false);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest()) // Kiểm tra có trả về 400 không
                .andExpect(jsonPath("$.message").exists());
    }
}