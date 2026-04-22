/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ptraitement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxen
 */
public class Cours {

    private String Type_de_cours;
    private String Activites;
    private int Nbr_de_place;
    private int Nbr_Inscrit;
    private String nomCoach;
    private LocalDate Date;
    private String Heure;
    private List<Client> Liste_Client_Inscrit;

    public Cours(String Type_de_cours, String Activites, int Nbr_de_place, String nomCoach, LocalDate Date, String Heure) {
        this.Type_de_cours = Type_de_cours;
        this.Activites = Activites;
        this.Nbr_de_place = Nbr_de_place;
        this.nomCoach = nomCoach;
        this.Date = Date;
        this.Heure = Heure;
        this.Liste_Client_Inscrit = new ArrayList<>();
    }

    public void ajouterParticipant(Client c) {
        if (Liste_Client_Inscrit.size() < Nbr_de_place) {
            if (!Liste_Client_Inscrit.contains(c)) {
                Liste_Client_Inscrit.add(c);
            } else {
                System.out.println("Vous etes deja inscrit a ce cours.");
            }
        } else {
            System.out.println("Le cours est complet.");
        }
    }

    public void retirerParticipant(Client c) {
        Liste_Client_Inscrit.remove(c);
    }

    @Override
    public String toString() {
        return "[" + Type_de_cours + "] " + Activites + " avec " + nomCoach
                + " le " + Date + " a " + Heure
                + " (Places : " + Liste_Client_Inscrit.size() + "/" + Nbr_de_place + ")";
    }

    public String getType_de_cours() {
        return Type_de_cours;
    }
    
    public int getNbr_Inscrit(){
        Nbr_Inscrit = Liste_Client_Inscrit.size();
        return Nbr_Inscrit;
    }

    public String getNomActivite() {
        return Activites;
    }

    public LocalDate getDate() {
        return Date;
    }

    public List<Client> getListe_Client_Inscrit() {
        return Liste_Client_Inscrit;
    }
    

    public void setNomCours(String nouveauNom) {
        if (nouveauNom != null && !nouveauNom.isEmpty()) {
            this.Type_de_cours = nouveauNom;
        } else {
            System.out.println("Erreur : le nom ne peut pas etre vide.");
        }
    }

    public void setNomActivite(String nouveauNomActivite) {
        if (nouveauNomActivite != null && !nouveauNomActivite.isEmpty()) {
            this.Activites = nouveauNomActivite;
        } else {
            System.out.println("Erreur : le nom ne peut pas etre vide.");
        }
    }

    public void setNbrPlace(int nouveauNbrPlace) {
        if (nouveauNbrPlace > 0) {
            this.Nbr_de_place = nouveauNbrPlace;
        } else {
            System.out.println("Erreur : le nombre doit etre superieur a 0.");
        }
    }
    

    public int getNb_Place_Max() {
        return Nbr_de_place;
    }

    public String getCoach() {
        return nomCoach;
    }

    public String getHeure() {
        return Heure;
    }

    public void setType_de_cours(String type_de_cours) {
        this.Type_de_cours = type_de_cours;
    }

    public void setNb_Place_Max(int Places) {
        this.Nbr_de_place = Places;
    }

    public void setCoach(String coach) {
        this.nomCoach = coach;
    }

    public void setDate(java.time.LocalDate date) {
        this.Date = date;
    }

    public void setHeure(String heure) {
        this.Heure = heure;
    }
    
    
    
}
