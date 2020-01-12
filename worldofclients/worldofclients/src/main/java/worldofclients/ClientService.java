package worldofclients;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import worldofclients.constans.Constans;
import worldofclients.model.Client;

import java.util.List;

@Service
public class ClientService {
    String errorMessage = "not found";
    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(long id) {
        return clientRepository.findById(id).orElseThrow(Error::new);
    }

    public Client updateClient(long id, Client client) {
        return clientRepository.findById(id).map(c -> {
            c.setCity(client.getCity());
            c.setCompanyName(client.getCompanyName());
            c.setCountry(client.getCountry());
            c.setStreet(client.getStreet());
            c.setStreetNumber(client.getStreetNumber());
            c.setZipCode(client.getZipCode());
            return clientRepository.save(c);

        }).orElseThrow(() -> new RuntimeException(errorMessage));
    }

    public ResponseEntity<?> deleteClient(long id) {
        return clientRepository.findById(id).map(c -> {
            clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException(errorMessage));
    }


    public String googleString(long id) {
        return clientRepository.findById(id).map(c -> {
            String city = c.getCity();
            String companyName = c.getCompanyName().replace(" ","");
            String country = c.getCountry();
            String streetName = c.getStreet().replace(" ","");
            String streetNumber = c.getStreetNumber();
            String zipCode = c.getZipCode().replace(" ","");
            return Constans.GEO_SERVICE + zipCode + companyName + "," + streetName + streetNumber + "," + city + "," + country + Constans.API_KEY;

        }).orElseThrow(() -> new RuntimeException(errorMessage));
    }
}
