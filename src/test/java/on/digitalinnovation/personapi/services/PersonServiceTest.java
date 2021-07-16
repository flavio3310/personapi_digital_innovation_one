package on.digitalinnovation.personapi.services;


import on.digitalinnovation.personapi.dto.mapper.PersonMapper;
import on.digitalinnovation.personapi.dto.request.PersonDTO;
import on.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import on.digitalinnovation.personapi.entity.Person;
import on.digitalinnovation.personapi.mapper.PersonMapper;
import on.digitalinnovation.personapi.repository.PersonRepository;
import on.digitalinnovation.personapi.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static on.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static on.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSavedMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.ToModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.create(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private PersonDTO createFakeDTO() {
    }

    private MessageResponseDTO createExpectedSuccessMessage(Long savedPersonId) {
        return MessageResponseDTO.builder()
                .massage("Person successfully created with ID " + savedPersonId)
                .build();
    }
}
