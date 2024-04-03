package akatsuki.moodholic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * ChatGPT에서 사용하는 환경 구성
 *
 * @author : lee
 * @fileName : RestTemplate
 * @since : 12/29/23
 */
@Configuration
public class ChatGPTConfig {

    @Value("${openai.api.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiKey);
            return execution.execute(request, body);
        }));
        return restTemplate;
    }

}
