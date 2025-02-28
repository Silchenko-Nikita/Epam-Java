package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Client;
import com.epam.rd.autocode.assessment.appliances.repository.ClientRepository;
import com.epam.rd.autocode.assessment.appliances.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllClients() {
        Client client1 = new Client(1L, "John Doe", "john@example.com", "123456", "1234-5678");
        Client client2 = new Client(2L, "Jane Smith", "jane@example.com", "password", "4321-8765");
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));

        List<Client> result = clientService.getAllClients();

        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(client1, client2);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetClientById() {
        Long clientId = 1L;
        Client client = new Client(clientId, "John Doe", "john@example.com", "123456", "1234-5678");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Client result = clientService.getClientById(clientId);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("John Doe");
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    public void testSaveClient() {
        Client client = new Client(null, "John Doe", "john@example.com", "123456", "1234-5678");
        Client savedClient = new Client(1L, "John Doe", "john@example.com", "123456", "1234-5678");
        when(clientRepository.save(client)).thenReturn(savedClient);

        Client result = clientService.saveClient(client);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    public void testDeleteClientById() {
        Long clientId = 1L;
        doNothing().when(clientRepository).deleteById(clientId);

        clientService.deleteClientById(clientId);


        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
