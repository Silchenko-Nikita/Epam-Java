package edu.epam.fop.mocks;

import edu.epam.fop.mocks.client.ClientRepository;
import edu.epam.fop.mocks.client.ClientResponse;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public final class ClientService {

    private final ClientRepository client;

    public ClientService(ClientRepository client) {
        this.client = client;
        long definedId = client.definedId();

        when(client.findById(anyLong())).thenAnswer(invocation -> {
            long id = invocation.getArgument(0);

            if (id == definedId) {
                if (definedId % 2 != 0) {
                    return new ClientResponse(definedId, "Lou", "Tenat");
                } else {
                    return new ClientResponse(definedId, "Louisa", "Rodriguez");
                }
            }
            return null;
        });
    }

    public ClientResponse search(long id) {
        return client.findById(id);
    }
}
