package fr.adriencaubel.jpa_spring_enterprise_architecture.commande;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.adriencaubel.jpa_spring_enterprise_architecture.article.Article;
import fr.adriencaubel.jpa_spring_enterprise_architecture.article.ArticleRepository;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.Client;
import fr.adriencaubel.jpa_spring_enterprise_architecture.client.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository, 
                           ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeRepository = commandeRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    public Commande createCommande(CommandeRequestModel commandeRequestModel) {
    	// Récupérer le client
        Client client = clientRepository.findById(commandeRequestModel.getClientId())
            .orElseThrow(() -> new EntityNotFoundException("Client non trouvé"));
        
        // Récupérer les articles
        List<Article> articles = articleRepository.findAllById(commandeRequestModel.getArticleIds());
        
        // Vérifier si tous les articles sont actifs
        List<Article> articlesInactifs = articles.stream()
                .filter(article -> !article.isActif())
                .collect(Collectors.toList());

        if (!articlesInactifs.isEmpty()) {
            throw new IllegalArgumentException("Impossible de créer la commande. Les articles suivants sont inactifs: " 
                + articlesInactifs.stream()
                    .map(Article::getNom)
                    .collect(Collectors.joining(", ")));
        }
        
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setArticles(articles);
        commande.setCreatedOn(LocalDateTime.now());
        
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Long id) {
    	Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commande non trouvée"));
    	
        commandeRepository.delete(commande);
    }
}
