package fr.adriencaubel.jpa_spring_enterprise_architecture.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(ClientRequestModel clientRequestModel) {
    	Client client = toEntity(clientRequestModel);
    	
        // Vérification email unique
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà existant");
        }
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client non trouvé"));
        
        // Vérification : pas de commandes en cours
        if (!client.getCommandes().isEmpty()) {
            throw new RuntimeException("Impossible de supprimer un client avec des commandes");
        }
        
        clientRepository.delete(client);
    }
    
    private Client toEntity(ClientRequestModel clientRequestModel) {
    	Client client = new Client();
        client.setNom(clientRequestModel.getNom());
        client.setEmail(clientRequestModel.getEmail());
        return client;
    }
}