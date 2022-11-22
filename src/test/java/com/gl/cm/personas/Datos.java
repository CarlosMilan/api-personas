package com.gl.cm.personas;

import com.gl.cm.personas.dto.DireccionDTO;
import com.gl.cm.personas.dto.PersonaDTO;
import com.gl.cm.personas.dto.ProvinciaDTO;
import com.gl.cm.personas.model.Direccion;
import com.gl.cm.personas.model.Persona;
import com.gl.cm.personas.model.Provincia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Datos {

    public static Optional<Persona> createPersona1() {
        Persona persona = new Persona();
        persona.setId(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        persona.setEmail("test@correo.com");
        persona.setNombre("Jhon");
        persona.setApellido("Conor");

        String fecha = "20-03-2003";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        persona.setFechaNacimiento(LocalDate.parse(fecha, formatter));

        persona.setDni("20300400");
        persona.setActivo(true);
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        persona.setCreacion(LocalDateTime.parse("26-09-2022 10:13:56", formatter));

        return Optional.of(persona);
    }

    public static PersonaDTO createPersonaDTO1() {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        personaDTO.setEmail("test@correo.com");
        personaDTO.setNombre("Jhon");
        personaDTO.setApellido("Conor");

        personaDTO.setFechaNacimiento("20-03-2003");

        personaDTO.setDni("20300400");

        return personaDTO;
    }

    public static List<Persona> createPersonasList() {

        DateTimeFormatter localDateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter localDateTimeformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


        Persona persona1 = new Persona();
        persona1.setId(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        persona1.setEmail("test@correo.com");
        persona1.setNombre("Jhon");
        persona1.setApellido("Conor");

        String fecha = "20-03-2003";

        persona1.setFechaNacimiento(LocalDate.parse(fecha, localDateformatter));
        persona1.setDni("20300400");
        persona1.setActivo(true);
        persona1.setCreacion(LocalDateTime.parse("26-09-2022 10:13:56", localDateTimeformatter));

        Persona persona2 = new Persona();
        persona2.setId(UUID.fromString("f86c9ba2-3dad-11ed-b878-0242ac120002"));
        persona2.setEmail("martin@correo.com");
        persona2.setNombre("Martin");
        persona2.setApellido("Milan");

        fecha = "11-07-1992";
        persona2.setFechaNacimiento(LocalDate.parse(fecha, localDateformatter));

        persona2.setDni("32123123");
        persona2.setActivo(true);
        persona2.setCreacion(LocalDateTime.parse("26-09-2022 10:15:13", localDateTimeformatter));

        Persona persona3 = new Persona();
        persona3.setId(UUID.fromString("0bd7b9f6-3dae-11ed-b878-0242ac120002"));
        persona3.setEmail("pedro@correo.com");
        persona3.setNombre("Pedro");
        persona3.setApellido("Gomez");

        fecha = "19-06-2011";
        persona3.setFechaNacimiento(LocalDate.parse(fecha, localDateformatter));

        persona3.setDni("20300400");
        persona3.setActivo(true);
        persona3.setCreacion(LocalDateTime.parse("26-09-2022 12:45:33", localDateTimeformatter));

        return Arrays.asList(persona1, persona2, persona3);
    }

    public static List<PersonaDTO> createPersonasDTOList() {
        PersonaDTO persona1 = new PersonaDTO();
        persona1.setId(UUID.fromString("c2654c34-3dad-11ed-b878-0242ac120002"));
        persona1.setEmail("test@correo.com");
        persona1.setNombre("Jhon");
        persona1.setApellido("Conor");
        persona1.setFechaNacimiento("20-03-2003");
        persona1.setDni("20300400");

        PersonaDTO persona2 = new PersonaDTO();
        persona2.setId(UUID.fromString("f86c9ba2-3dad-11ed-b878-0242ac120002"));
        persona2.setEmail("martin@correo.com");
        persona2.setNombre("Martin");
        persona2.setApellido("Milan");
        persona2.setFechaNacimiento("11-07-1992");
        persona2.setDni("32123123");

        PersonaDTO persona3 = new PersonaDTO();
        persona3.setId(UUID.fromString("0bd7b9f6-3dae-11ed-b878-0242ac120002"));
        persona3.setEmail("pedro@correo.com");
        persona3.setNombre("Pedro");
        persona3.setApellido("Gomez");
        persona3.setFechaNacimiento("19-06-2011");
        persona3.setDni("20300400");

        return Arrays.asList(persona1, persona2, persona3);
    }

    public static Optional<Direccion> createDireccion() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Evergreen East");
        direccion.setNumeracion(449);

        Provincia provincia = new Provincia();
        provincia.setNombre("Buenos Aires");
        provincia.setId(UUID.randomUUID());

        direccion.setProvincia(provincia);
        direccion.setPersona(createPersona1().get());

        return Optional.of(direccion);
    }

    public static DireccionDTO createDireccionDTO() {
        DireccionDTO direccion = new DireccionDTO();
        direccion.setCalle("Evergreen East");
        direccion.setNumeracion(449);

        ProvinciaDTO provincia = new ProvinciaDTO();
        provincia.setNombre("Buenos Aires");
        provincia.setId(UUID.randomUUID());

        direccion.setProvincia(provincia);
        direccion.setPersona(createPersonaDTO1());

        return direccion;
    }

    public static List<Direccion> createDireccionList() {
        Direccion direccion1 = new Direccion();
        direccion1.setCalle("Evergreen East");
        direccion1.setNumeracion(449);

        Provincia provincia = new Provincia();
        provincia.setNombre("Buenos Aires");
        provincia.setId(UUID.randomUUID());

        direccion1.setProvincia(provincia);
        direccion1.setPersona(createPersona1().get());

        Direccion direccion2 = new Direccion();
        direccion2.setCalle("9 de Julio Norte");
        direccion2.setNumeracion(2034);

        Provincia provincia1 = new Provincia();
        provincia1.setNombre("Mendoza");
        provincia1.setId(UUID.randomUUID());

        direccion2.setProvincia(provincia1);
        direccion2.setPersona(createPersona1().get());

        return Arrays.asList(direccion1, direccion2);
    }

    public static List<DireccionDTO> createDireccionDTOList() {
        DireccionDTO direccion1 = new DireccionDTO();
        direccion1.setCalle("Evergreen East");
        direccion1.setNumeracion(449);

        ProvinciaDTO provincia = new ProvinciaDTO();
        provincia.setNombre("Buenos Aires");
        provincia.setId(UUID.randomUUID());

        direccion1.setProvincia(provincia);
        direccion1.setPersona(createPersonaDTO1());

        DireccionDTO direccion2 = new DireccionDTO();
        direccion2.setCalle("9 de Julio Norte");
        direccion2.setNumeracion(2034);

        ProvinciaDTO provincia1 = new ProvinciaDTO();
        provincia1.setNombre("Mendoza");
        provincia1.setId(UUID.randomUUID());

        direccion2.setProvincia(provincia1);
        direccion2.setPersona(createPersonaDTO1());

        return Arrays.asList(direccion1, direccion2);
    }

    public static List<Provincia> createProvinciaList() {
        Provincia provincia1 = new Provincia();
        provincia1.setId(UUID.randomUUID());
        provincia1.setNombre("Buenos Aires");

        Provincia provincia2 = new Provincia();
        provincia2.setId(UUID.randomUUID());
        provincia2.setNombre("Mendoza");

        Provincia provincia3 = new Provincia();
        provincia3.setId(UUID.randomUUID());
        provincia3.setNombre("Jujuy");

        return Arrays.asList(provincia1, provincia2, provincia3);
    }

    public static List<ProvinciaDTO> createProvinciaDTOList() {
        ProvinciaDTO provincia1 = new ProvinciaDTO();
        provincia1.setId(UUID.randomUUID());
        provincia1.setNombre("Buenos Aires");

        ProvinciaDTO provincia2 = new ProvinciaDTO();
        provincia2.setId(UUID.randomUUID());
        provincia2.setNombre("Mendoza");

        ProvinciaDTO provincia3 = new ProvinciaDTO();
        provincia3.setId(UUID.randomUUID());
        provincia3.setNombre("Jujuy");

        return Arrays.asList(provincia1, provincia2, provincia3);
    }
}
