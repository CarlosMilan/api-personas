package com.gl.cm.personas.config;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        Provider<LocalDate> localDateProvider = request -> LocalDate.now();

        Converter<String, LocalDate> toLocalDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.parse(source, formatter);
            }
        };

        Converter<String, LocalDateTime> localDateTimeConverter = new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String source) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:sss");
                return LocalDateTime.parse(source, formatter);
            }
        };

        Converter<LocalDate, String> localDateToStringConverter = new AbstractConverter<LocalDate, String>() {
            @Override
            protected String convert(LocalDate source) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return formatter.format(source);
            }
        };

        Converter<LocalDateTime, String> localDateTimeToStringConverter = new AbstractConverter<LocalDateTime, String>() {
            @Override
            protected String convert(LocalDateTime source) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                return formatter.format(source);
            }
        };

        modelMapper.createTypeMap(String.class, LocalDate.class);
        modelMapper.addConverter(toLocalDate);
        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        modelMapper.createTypeMap(String.class, LocalDateTime.class);
        modelMapper.addConverter(localDateTimeConverter);

        modelMapper.createTypeMap(LocalDate.class, String.class);
        modelMapper.addConverter(localDateToStringConverter);

        modelMapper.createTypeMap(LocalDateTime.class, String.class);
        modelMapper.addConverter(localDateTimeToStringConverter);

        modelMapper.createTypeMap(PersonaDTO.class, Persona.class);
        modelMapper.getTypeMap(PersonaDTO.class, Persona.class).addMappings(mapping -> mapping.skip(Persona::setId));

        return modelMapper;
    }

}
