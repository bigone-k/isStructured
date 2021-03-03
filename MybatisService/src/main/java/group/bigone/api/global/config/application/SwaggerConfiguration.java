package group.bigone.api.global.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder
                .builder()
                .operationsSorter(OperationsSorter.METHOD)
                .build();
    }

    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(swaggerInfo()).select()
                    .apis(RequestHandlerSelectors.basePackage("group.bigone.api.domain"))
                    .paths(PathSelectors.any())
                    .build()
                    .useDefaultResponseMessages(false);

    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("BackendService API Documentation")
                .description("Develop document")
                .version("1.0")
                .build();
    }
}
