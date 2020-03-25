package com.xmlexercise.config;

import com.xmlexercise.utils.ValidationUtil;
import com.xmlexercise.utils.ValidationUtilImpl;
import com.xmlexercise.utils.XMLParser;
import com.xmlexercise.utils.XMLParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public XMLParser xmlParser() {
        return new XMLParserImpl();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
