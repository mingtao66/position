package net.hzbox.vj.journal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("net.hzbox.vj.journal.dao")
public class JournalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalServiceApplication.class, args);
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @LoadBalanced
    @Bean
    public RestTemplate loadBalanced(ProtobufHttpMessageConverter hmc) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(hmc);
        return restTemplate;
    }

    @Primary
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
