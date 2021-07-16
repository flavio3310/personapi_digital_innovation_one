package on.digitalinnovation.personapi.mapper;

import on.digitalinnovation.personapi.dto.request.PersonDTO;
import on.digitalinnovation.personapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "string")
public interface PersonMapper {


    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person ToModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
