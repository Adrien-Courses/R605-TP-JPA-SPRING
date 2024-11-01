package fr.adriencaubel.jpa_spring_enterprise_architecture.client;

public class ClientRequestModel {
    private String nom;
    private String email;

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
