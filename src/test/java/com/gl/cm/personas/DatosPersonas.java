package com.gl.cm.personas;

import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.model.Persona;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DatosPersonas {

    public static Optional<Persona> createPersona1() {
        Persona persona = new Persona();
        persona.setId(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        persona.setEmail("test@correo.com");
        persona.setNombre("Jhon");
        persona.setApellido("Conor");

        String fecha = "20/03/2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona.setDni("20300400");
        persona.setActivo(true);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        persona.setCreacion(LocalDateTime.parse("26/09/2022-10:13:56", formatter));

        return Optional.of(persona);
    }

    public static PersonaDTO createPersonaDTO1() {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setEmail("test@correo.com");
        personaDTO.setNombre("Jhon");
        personaDTO.setApellido("Conor");

        String fecha = "20/03/2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        personaDTO.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        personaDTO.setDni("20300400");

        return personaDTO;
    }

    public static List<Persona> createPersonasList() {
        Persona persona1 = new Persona();
        persona1.setId(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        persona1.setEmail("test@correo.com");
        persona1.setNombre("Jhon");
        persona1.setApellido("Conor");

        String fecha = "20/03/2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona1.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona1.setDni("20300400");
        persona1.setActivo(true);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        persona1.setCreacion(LocalDateTime.parse("26/09/2022-10:13:56", formatter));

        Persona persona2 = new Persona();
        persona2.setId(UUID.fromString("f86c9ba2-3dad-11ed-b878-0242ac120002"));
        persona2.setEmail("martin@correo.com");
        persona2.setNombre("Martin");
        persona2.setApellido("Milan");

        fecha = "11/07/1992";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona2.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona2.setDni("32123123");
        persona2.setActivo(true);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        persona2.setCreacion(LocalDateTime.parse("26/09/2022-10:15:13", formatter));

        Persona persona3 = new Persona();
        persona3.setId(UUID.fromString("0bd7b9f6-3dae-11ed-b878-0242ac120002"));
        persona3.setEmail("pedro@correo.com");
        persona3.setNombre("Pedro");
        persona3.setApellido("Gomez");

        fecha = "19/06/2011";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        persona3.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona3.setDni("20300400");
        persona3.setActivo(true);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm:ss");
        persona3.setCreacion(LocalDateTime.parse("26/09/2022-12:45:33", formatter));

        return Arrays.asList(persona1, persona2, persona3);
    }
}
