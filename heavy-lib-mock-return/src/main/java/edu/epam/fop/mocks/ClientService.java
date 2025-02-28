package edu.epam.fop.mocks;

import edu.epam.fop.mocks.client.ClientRepository;
import edu.epam.fop.mocks.client.ClientResponse;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public final class ClientService {

  private final ClientRepository client;

  public ClientService(ClientRepository client) {
    this.client = client;
    long definedId = client.definedId();

    when(client.findById(definedId)).thenReturn(new ClientResponse(definedId, "Lou", "Tenat"));
    when(client.findById(not(eq(definedId)))).thenReturn(null);
  }
  
  public ClientResponse search(long id) {
    return client.findById(id);
  }
}
