# 使用eclipse-temurin官方Alpine Linux带JRE的轻量级镜像
FROM eclipse-temurin:21.0.3_9-jre-alpine
# 指定维护者信息(可选)
LABEL maintainer="paynezhuang@gmail.com"
# 设置时区
ENV TZ='Asia/Shanghai'
RUN ln -snf /usr/share/zoneinfo/"$TZ" /etc/localtime && echo "$TZ" > /etc/timezone
# 设置工作目录
WORKDIR /app
# 将构建的jar文件复制到镜像中
COPY target/panis-boot-admin-1.0.1-SNAPSHOT.jar app.jar
# 允许外界访问容器的9999端口
EXPOSE 9999
# 配置容器启动后执行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]