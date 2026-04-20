package com.qk.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Slf4j
@Component
public class AliyunOSSOperator {
/*自己封装的工具类：调用该类的upload方法 可以把文件上传到阿里云 然后返回url地址*/

    //配置地址方案1：项目打包之后不能更改
/*    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
    private static final String BUCKET_NAME = "ai-web-zilu-zhao";
    private static final String REGION = "cn-beijing";*/

    //如何得到配置文件application.yml内的参数
    //配置阿里云方案2：在application.yml文件内已经配置好了在这里获取，缺点是如果参数过多 代码量多
  /*  @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    @Value("${aliyun.oss.region}")
    private String region;*/

    //配置方案三，在utils包内将参数封装成javabean对象，哪里需要哪里注入
    @Autowired
    private  AliyunOSSProperties aliyunOSSProperties;

    public String upload(byte[] content, String objectName) throws Exception {
        // 创建OSSClient实例
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = null;
        try {
            ossClient = OSSClientBuilder.create()
                    .endpoint(aliyunOSSProperties.getEndpoint())
                    .credentialsProvider(new EnvironmentVariableCredentialsProvider())
                    .clientConfiguration(clientBuilderConfiguration)
                    .region(aliyunOSSProperties.getRegion()  )
                    .build();
            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(aliyunOSSProperties.getBucketName(), objectName, new ByteArrayInputStream(content));
            // 上传文件
            ossClient.putObject(putObjectRequest);
            // 返回文件访问URL
            return "https://" + aliyunOSSProperties.getBucketName() + "." + aliyunOSSProperties.getEndpoint().substring(8) + "/" + objectName;
        } catch (Exception e) {
            log.error("Caught an OSSException: " + e.getMessage());
            throw e;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}