# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 复制文件到容器
ADD /target/microservice-discovery-eureka-ha-0.0.1-SNAPSHOT.jar eureka2.jar
RUN bash -c 'touch /eureka2.jar'

# 声明需要暴露的端口
EXPOSE 8762

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.end=file:/dev./urandom","-jar","/eureka2.jar"]