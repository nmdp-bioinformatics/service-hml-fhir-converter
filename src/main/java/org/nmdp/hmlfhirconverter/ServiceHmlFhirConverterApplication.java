package org.nmdp.hmlfhirconverter;

/**
 * Created by abrown3 on 12/21/16.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ServiceHmlFhirConverterApplication /*extends SpringBootServletInitializer*/ {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHmlFhirConverterApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application ) {
//        return application.sources(ServiceHmlFhirConverterApplication.class);
//    }
}
