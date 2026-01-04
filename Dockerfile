# Sử dụng Amazon Corretto (JDK 21) rất ổn định và nhẹ
FROM amazoncorretto:21-al2023-headless

# Bước 2: Tạo thư mục làm việc bên trong container
WORKDIR /app

# Bước 3: Copy file JAR đã build từ máy vào container
# Lưu ý: Bạn cần chạy lệnh 'mvn clean package' trước để tạo file JAR này
COPY target/*.jar app.jar

# Bước 4: Mở cổng 8080 (cổng mặc định của Spring Boot)
EXPOSE 8080

# Bước 5: Lệnh để khởi chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]