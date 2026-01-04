package antonio.todo.service;

import antonio.todo.exception.AuthenticationException;
import antonio.todo.model.dto.LoginRequest;
import antonio.todo.model.entity.User;
import antonio.todo.repository.UserRepository;
import antonio.todo.utils.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public Map<String, String> authenticate(LoginRequest loginRequest) {
        // 1. Tìm user
        // NOTE:
        // Đối với loginRequest.username() và loginRequest.password(), vẫn giữ nguyên dấu ngoặc đơn vì LoginRequest là một Record
        User user = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new AuthenticationException("Tài khoản hoặc mật khẩu không chính xác!"));

        // 2. Kiểm tra mật khẩu
        // NOTE: User là một Class sử dụng Lombok nên phải tuân theo chuẩn get...()
        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new AuthenticationException("Tài khoản hoặc mật khẩu không chính xác!");
        }

        // 3. Tạo Token
        String token = jwtUtils.generateToken(user.getUsername());

        return Map.of("accessToken", token);
    }
}