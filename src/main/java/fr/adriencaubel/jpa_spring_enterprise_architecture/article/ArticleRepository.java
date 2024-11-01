package fr.adriencaubel.jpa_spring_enterprise_architecture.article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
