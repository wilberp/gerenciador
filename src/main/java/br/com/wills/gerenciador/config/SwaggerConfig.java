package br.com.wills.gerenciador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swagger(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApinfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.wills.gerenciador"))
                .paths(PathSelectors.any())
                .build();

    }

    public ApiInfo getApinfo(){
       return new ApiInfo("Gerenciador Financeiro", "Gerenciador Financeiro Pessoal desenvolvido para controle de receita e despesas.", "1.0" ,null,null, null,null, Collections.emptyList());    }

}
