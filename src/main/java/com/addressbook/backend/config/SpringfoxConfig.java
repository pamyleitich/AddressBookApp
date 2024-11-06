import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.spi.service.contexts.Orderings;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SpringfoxConfig {
    @Autowired
    private List<WebMvcRequestHandlerProvider> requestHandlerProviders;

    @Bean
    @Primary
    @ConditionalOnProperty(value = "springfox.documentation.enabled", matchIfMissing = true)
    public WebMvcRequestHandlerProvider webMvcRequestHandlerProvider() {
        return new WebMvcRequestHandlerProvider(requestHandlerProviders.stream()
                .filter(provider -> Orderings.byPatternsCondition().compare(provider, provider) == 0)
                .collect(Collectors.toList()));
    }
}
