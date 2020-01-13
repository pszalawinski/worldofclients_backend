package worldofclients.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import worldofclients.constans.Constans;
import worldofclients.model.Client;
import worldofclients.service.ClientService;

@Controller
public class HomeController {

    public ClientService clientService;

    public HomeController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public String getHomePage(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "index";
    }

    @PostMapping("clients/add")
    public String addClient(@ModelAttribute Client incomeClient) {
        Client client = new Client();
        client.setZipCode(incomeClient.getZipCode());
        client.setStreetNumber(incomeClient.getStreetNumber());
        client.setStreet(incomeClient.getStreet());
        client.setCountry(incomeClient.getCountry());
        client.setCompanyName(incomeClient.getCompanyName());
        client.setCity(incomeClient.getCity());
         String operationResult = clientService.createClient(client).getCompanyName();
        return "redirect:/clients@message:client added " + operationResult;
    }

    @GetMapping("clients/delete")
    public String deleteClient(@RequestParam long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }

    @PostMapping("/clients/update")
    public String updateClient(@ModelAttribute Client incomeClient) {

        Client client = clientService.getClientById(incomeClient.getId());
        if (client == null) {
            return " no object to update! ";
        }
        client.setZipCode(incomeClient.getZipCode());
        client.setStreetNumber(incomeClient.getStreetNumber());
        client.setStreet(incomeClient.getStreet());
        client.setCountry(incomeClient.getCountry());
        client.setCompanyName(incomeClient.getCompanyName());
        client.setCity(incomeClient.getCity());
        clientService.createClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/clients/google")
    public String createApiGoogleLink(@ModelAttribute Client incomeClient)  {
        Client client = clientService.getClientById(incomeClient.getId());
        if (client == null) {
            return " no object to show! ";
        }
        String zipCode = client.setZipCode(incomeClient.getZipCode());
        String streetNumber = client.setStreetNumber(incomeClient.getStreetNumber());
        String streetName = client.setStreet(incomeClient.getStreet());
        String country = client.setCountry(incomeClient.getCountry());
        String companyName = client.setCompanyName(incomeClient.getCompanyName());
        String city = client.setCity(incomeClient.getCity());
        String lat = client.setLat(incomeClient.getLng());
        String lng = client.setLng(incomeClient.getLng());
        String geoLink = Constans.GEO_SERVICE+zipCode+companyName+","+streetName+streetNumber+","+city+","+country+Constans.API_KEY;

        return geoLink;
    }

}
