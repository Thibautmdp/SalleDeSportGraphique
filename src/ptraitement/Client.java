/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ptraitement;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxen
 */
public class Client extends Utilisateur {

    private String NumClient;
    private String Nom;
    private String Prenom;
    private String Tel;
    private String TypeAbo;
    private boolean AboActif;
    private List<Cours> Liste_des_cours_futurs_Clients;
    private List<Cours> Liste_des_cours_passes_Clients;

    public Client(String NumClient, String Nom, String Prenom, String Email, String Mdp, String Tel, String TypeAbo) {
        super(Email, Mdp);
        this.NumClient = NumClient;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.TypeAbo = TypeAbo;
        this.AboActif = true;
        this.Liste_des_cours_futurs_Clients = new ArrayList<>();
        this.Liste_des_cours_passes_Clients = new ArrayList<>();
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getMotDePasse() {
        return getMdp();
    }

    public String getNumTel() {
        return Tel;
    }

    public String getAbo() {
        return TypeAbo;
    }

    public String getNumClient() {
        return NumClient;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }
   
    public void setTypeAbo(String Periodicite) {
        switch (Periodicite) {
            case "1":
                Periodicite = "Trimestriel";
                break;
            case "2":
                Periodicite = "Semestriel";
                break;
            case "3":
                Periodicite = "Annuel";
                break;
        }
        this.TypeAbo = Periodicite;
    }

    public boolean Abo_est_il_actif() {
        return this.AboActif;
    }

    public void Abo_devient_actif(boolean NouvelEtat) {
        this.AboActif = NouvelEtat;
    }

    public List<Cours> getListe_des_cours_futurs_clients() {
        return this.Liste_des_cours_futurs_Clients;
    }

    public List<Cours> getListe_des_cours_passes_clients() {
        return this.Liste_des_cours_passes_Clients;
    }
}
