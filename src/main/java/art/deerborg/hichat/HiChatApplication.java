package art.deerborg.hichat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Live Chat API",version = "1.0",description = "LiveChat"))
public class HiChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiChatApplication.class, args);
    }

}
