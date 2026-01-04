package antonio.todo.exception;

public record ErrorResponse(int status, String message, long timestamp) {}
