package com.example.MC1;

import com.example.MC1.services.dto.MessageDTOToModelConvertor;
import com.example.MC1.services.dto.MessageModelToDTOConvertor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@SpringBootApplication
public class Mc1Application {

	public static void main(String[] args) {
		SpringApplication.run(Mc1Application.class, args);
	}

	@Bean
	@Qualifier( "myConverter" )
	public ConversionService conversionService()
	{
		var converterService = new DefaultConversionService();
		converterService.addConverter( new MessageDTOToModelConvertor() );
		converterService.addConverter( new MessageModelToDTOConvertor() );
		return converterService;
	}
}
