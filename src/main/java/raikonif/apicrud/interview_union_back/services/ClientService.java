package raikonif.apicrud.interview_union_back.services;

import raikonif.apicrud.interview_union_back.entity.Client;

import java.util.List;

public interface ClientService {
    public List<Client> findAll();
    public Client save(Client client);
    public Client findById(Long id);
    public void delete(Client client);
}
