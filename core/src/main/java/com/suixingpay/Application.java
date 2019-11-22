package com.suixingpay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Configuration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

//    另外一种控制数据大小的方法
//    @Autowired
//    Environment environment;
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        environment.getProperty()
//        //  单个数据大小
//        factory.setMaxFileSize("102400KB"); // KB,MB
//        /// 总上传数据大小
//        factory.setMaxRequestSize("102400KB");
//        return factory.createMultipartConfig();
//    }

}
