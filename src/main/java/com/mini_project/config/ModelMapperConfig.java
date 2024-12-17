package com.mini_project.config;

import com.mini_project.model.ProductCategory;
import com.mini_project.model.dto.ProductCategoryDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<ProductCategoryDTO, ProductCategory>() {
            @Override
            protected void configure() {
                skip(destination.getId(),destination.getId());
            }
        });
//
//        modelMapper.addMappings(new PropertyMap<WorkLogCreateDTO, WorkLog>() {
//            @Override
//            protected void configure() {
//                skip(destination.getId());
//            }
//        });
//
//        modelMapper.addMappings(new PropertyMap<LoanCreateDTO, Loan>() {
//            @Override
//            protected void configure() {
//                skip(destination.getId());
//            }
//        });

        return modelMapper;
    }
}
