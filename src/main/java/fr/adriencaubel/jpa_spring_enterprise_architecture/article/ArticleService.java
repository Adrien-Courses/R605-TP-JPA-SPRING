package fr.adriencaubel.jpa_spring_enterprise_architecture.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(ArticleRequestDTO articleRequestDTO) {
    	Article article = toEntity(articleRequestDTO);
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Article non trouvé"));
        
        // On ne supprime pas mais le rendons inactifs
        article.setActif(false);
        articleRepository.save(article);
    }
    
    private Article toEntity(ArticleRequestDTO articleRequestDTO) {
    	Article article = new Article();
    	article.setNom(articleRequestDTO.getNom());
    	article.setPrix(articleRequestDTO.getPrix());
    	return article;
    }

    public List<Article> getArticles(Boolean actif) {
        if (actif == null) {
            return articleRepository.findAll();
        }
        return articleRepository.findByActif(actif);
    }
}