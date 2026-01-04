package antonio.todo.config;

import antonio.todo.model.entity.TodoTask;
import antonio.todo.model.entity.User;
import antonio.todo.repository.TodoRepository;
import antonio.todo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("!test") // Quan trọng: Chỉ chạy khi KHÔNG PHẢI môi trường test
public class DataInitializer {
    @Bean
    public CommandLineRunner initData(TodoRepository repository, UserRepository userRepo, PasswordEncoder encoder) {
        return args -> {
            // Tạo User mẫu: admin / password123
            if (userRepo.findByUsername("admin").isEmpty()) {
                userRepo.save(new User(null, "admin", encoder.encode("password123")));
            }
            repository.save(new TodoTask(null, "Học Spring Boot cơ bản", true));
            repository.save(new TodoTask(null, "Tích hợp Swagger UI", true));
            repository.save(new TodoTask(null, "Viết Unit Test", false));
            repository.save(new TodoTask(null, "task1", false));
            repository.save(new TodoTask(null, "task2", false));
            repository.save(new TodoTask(null, "task3", true));
            repository.save(new TodoTask(null, "task4", false));
            repository.save(new TodoTask(null, "task5", true));
            repository.save(new TodoTask(null, "task6", false));
            repository.save(new TodoTask(null, "task7", true));
        };
    }
}