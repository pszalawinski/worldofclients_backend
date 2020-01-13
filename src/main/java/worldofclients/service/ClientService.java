package worldofclients.service;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import worldofclients.constans.Constans;
import worldofclients.model.Client;
import worldofclients.repository.ClientRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;
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


    public String googleUri(long id) {
        return clientRepository.findById(id).map(c -> {
                    String city = c.getCity().replace(" ", "");
                    String companyName = c.getCompanyName().replace(" ", "");
                    String country = c.getCountry().replace(" ", "");
                    String streetName = c.getStreet().replace(" ", "");
                    String streetNumber = c.getStreetNumber().replace(" ", "");
                    String zipCode = c.getZipCode().replace(" ", "");
                    String linkGoogle = Constans.GEO_SERVICE + zipCode + companyName + "," + streetName + streetNumber + "," + city + "," + country + Constans.API_KEY;

                    try {
                        return parse(linkGoogle);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        ).orElseThrow(() -> new RuntimeException(errorMessage));
    }


    public String parse(String linkGeoGoogle) throws IOException {

        String url = linkGeoGoogle;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject obj_JSONObject = new JSONObject(response.toString());
        JSONArray results_array = obj_JSONObject.getJSONArray("results");
        JSONObject results = results_array.getJSONObject(0);
        JSONObject geometry_array = results.getJSONObject("geometry");
        JSONObject geometry = geometry_array.getJSONObject("location");
        BigDecimal lng = geometry.getBigDecimal("lng");
        BigDecimal lat = geometry.getBigDecimal("lat");
        String location = lng.toString() + "  " + lat.toString();
        System.out.println(location);
        System.out.println(lng);
        System.out.println(lat);

        JSONObject jsonObject = new JSONObject(geometry);

        return geometry.toString();
    }
}
