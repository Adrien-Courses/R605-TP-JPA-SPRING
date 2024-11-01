package fr.adriencaubel.jpa_spring_enterprise_architecture.article;

public class ArticleRequestDTO {
    private String nom;
    private double prix;
    
    public String getNom() {
        return nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}