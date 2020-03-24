package com.xmldemo.config;

import com.xmldemo.utils.XMLParser;
import com.xmldemo.utils.XMLParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XMLParser xmlParser() {
        return new XMLParserImpl();
    }
}
