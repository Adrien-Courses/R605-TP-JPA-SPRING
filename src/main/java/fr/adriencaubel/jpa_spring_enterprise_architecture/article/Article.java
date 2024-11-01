package fr.adriencaubel.jpa_spring_enterprise_architecture.article;

import java.util.ArrayList;
import java.util.List;

import fr.adriencaubel.jpa_spring_enterprise_architecture.commande.Commande;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double prix;

    /*
     * - Indique que la relation est gérée côté Commande
     * - L'attribut articles dans Commande définit la relation 
     */
    @ManyToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    private List<Commande> commandes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

    
}