# Stage 1: Build ứng dụng bằng Maven
FROM maven:3.9.6-eclipse-temurin-21-as-builder
WORKDIR /build

# Copy file cấu hình Maven và mã nguồn
COPY pom.xml .
COPY src ./src

# Thực hiện build dự án để tạo file JAR
RUN mvn clean package -DskipTests

# Stage 2: Tạo image chạy ứng dụng (gọn nhẹ)
FROM amazoncorretto:21-al2023-headless
WORKDIR /app

# Copy file JAR từ Stage 1 sang Stage 2
# Chú ý: Tên file JAR phải khớp với tên trong target/
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]