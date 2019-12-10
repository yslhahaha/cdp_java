package tiens.cdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("tiens.cdp.dao")
public class CdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdpApplication.class, args);
    }

}
