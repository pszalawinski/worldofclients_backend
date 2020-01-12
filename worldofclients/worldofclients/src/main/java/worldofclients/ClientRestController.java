package worldofclients;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import worldofclients.model.Client;

import java.util.List;

@CrossOrigin
@RestController
public class ClientRestController {

    private ClientService clientService;


    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("api/clients")
    public List<Client> getClients(){return  clientService.getClients();}
    @PostMapping("api/clients")
    public Client createClient(@RequestBody Client client){return clientService.createClient(client);}
    @DeleteMapping("api/clients/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable long id){ return clientService.deleteClient(id);}
    @PutMapping("api/clients/{id}")
    public Client updateClient(@PathVariable long id, @RequestBody Client client){
        return clientService.updateClient(id,client);
    }
    @GetMapping("api/clients/google/{id}")
    public String googleString(@PathVariable long id){
        return clientService.googleString(id);
    }
}
