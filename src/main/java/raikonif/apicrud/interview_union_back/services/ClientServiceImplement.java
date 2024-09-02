package raikonif.apicrud.interview_union_back.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raikonif.apicrud.interview_union_back.dao.ClientDao;
import raikonif.apicrud.interview_union_back.entity.Client;

import java.util.List;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional
    public List<Client> findAll() {
        return (List<Client>) clientDao.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Override
    @Transactional
    public void delete(Client client) {
    clientDao.delete(client);
    }

}
