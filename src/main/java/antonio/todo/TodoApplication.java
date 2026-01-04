package antonio.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}
//Note
/*
* @RequestBody, @PathVariable, @Service, @ExceptionHandler(TaskNotFoundException.class),
* @NoArgsConstructor, @AllArgsConstructor
 * */
