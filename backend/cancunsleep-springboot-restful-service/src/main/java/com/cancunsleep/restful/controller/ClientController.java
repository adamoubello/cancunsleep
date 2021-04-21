package com.cancunsleep.restful.controller;

import com.cancunsleep.restful.model.Client;
import com.cancunsleep.restful.service.ClientService;
import com.cancunsleep.restful.service.IClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/client")
public class ClientController {

    final static Logger logger = Logger.getLogger(ClientController.class);

    @Autowired
    IClientService iClientService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        iClientService.save(client);
        logger.debug("Added:: " + client);
        return new ResponseEntity<Client>(client, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateClient(@RequestBody Client client) {
        Client existingClient = iClientService.getById(client.getIdclient());
        if (existingClient == null) {
            logger.debug("Client with idclient " + client.getIdclient() + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iClientService.save(client);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{idclient}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClient(@PathVariable("idclient") Long idclient) {
        Client client = iClientService.getById(idclient);
        if (client == null) {
            logger.debug("Client with idclient " + idclient + " does not exists");
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found Client:: " + client);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = iClientService.getAll();
        if (clients.isEmpty()) {
            logger.debug("Clients does not exists");
            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + clients.size() + " Clients");
        logger.debug(clients);
        logger.debug(Arrays.toString(clients.toArray()));
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @RequestMapping(value = "/{idclient}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClient(@PathVariable("idclient") Long idclient) {
        Client client = iClientService.getById(idclient);
        if (client == null) {
            logger.debug("Client with id " + idclient + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            iClientService.delete(idclient);
            logger.debug("Client with id " + idclient + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }

}
