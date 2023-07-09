package com.ascending.demo.api.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AWSConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${aws.accessKeyId}")
    private String myAWSAccessKeyId;
    @Value("$aws.secreteKey")
    private String myAWSSecretKey;

    @Value("$aws.region")
    private String awsRegion;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE) //每用一次就产生一个新的bean
    public AmazonS3 getAmazonS3() {
        return null;
    }
    private AmazonS3 getS3ClientWithSuppliedCredentials() {
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials(myAWSAccessKeyId, myAWSSecretKey);
//        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .withRegion(awsRegion)
//                .build();
        //Use default credential chain instead of static/basic credential
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(awsRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();


        return s3Client;
    }
    private AmazonS3 getS3ClientUsingDefaultChain() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
        return s3Client;
    }
    private AmazonS3 getS3ClientWithEnvironmentCredentials() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new EnvironmentVariableCredentialsProvider())
                .withRegion(awsRegion)
                .build();
        return s3Client;
    }
}
