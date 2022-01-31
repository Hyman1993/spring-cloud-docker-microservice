# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 复制文件到容器
ADD /target/microservice-consumer-movie-0.0.1-SNAPSHOT.jar movie.jar
RUN bash -c 'touch /movie.jar'

# 声明需要暴露的端口
EXPOSE 8010

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.end=file:/dev./urandom","-jar","/movie.jar"]