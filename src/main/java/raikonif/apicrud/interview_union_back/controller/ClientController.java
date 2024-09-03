package raikonif.apicrud.interview_union_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raikonif.apicrud.interview_union_back.entity.Client;
import raikonif.apicrud.interview_union_back.services.ClientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ClientController {


    @GetMapping("/clients")
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Client> list = clientService.findAll();
            return new ResponseEntity<Object>(list, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            Client client = clientService.findById(id);
            return new ResponseEntity<Object>(client, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<Object> save(@RequestBody Client client){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Client newClient = clientService.save(client);
            return new ResponseEntity<Object>(newClient, HttpStatus.CREATED);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Client client){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Client clientUpdate = clientService.findById(id);
            if (clientUpdate != null) {
                clientUpdate.setName(client.getName());
                clientUpdate.setFirstLastName(client.getFirstLastName());
                clientUpdate.setSecondLastName(client.getSecondLastName());
                clientUpdate.setDocType(client.getDocType());
                clientUpdate.setDocNumber(client.getDocNumber());
                clientUpdate.setBirthDate(client.getBirthDate());
                clientUpdate.setGender(client.getGender());
                clientService.save(clientUpdate);
                return new ResponseEntity<Object>(clientUpdate, HttpStatus.OK);
            } else {
                map.put("message", "Client not found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Client client = clientService.findById(id);
            if (client != null) {
                clientService.delete(client);
                map.put("message", "Client deleted successfully");
                return new ResponseEntity<Object>(client, HttpStatus.OK);
            } else {
                map.put("message", "Client not found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
