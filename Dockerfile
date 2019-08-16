FROM registry-vpc.cn-beijing.aliyuncs.com/qianjia2018/qianjia_public:oraclejdk

VOLUME /tmp

ADD target/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]