/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ptraitement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author maxen
 */
public class Salle {

    private String Nom;
    private List<Client> Liste_des_clients;
    private Admin Administrateur;
    private List<Cours> Liste_des_cours_futurs;
    private List<Cours> Liste_des_cours_passes;
    private String Nom_du_fichier_sauvegarder;

    Scanner scan = new Scanner(System.in);

    public Salle(String Nom, String fichier) {
        this.Nom = Nom;
        this.Nom_du_fichier_sauvegarder = fichier;
        this.Liste_des_clients = new ArrayList<>();
        this.Liste_des_cours_futurs = new ArrayList<>();
        this.Liste_des_cours_passes = new ArrayList<>();
        this.Administrateur = new Admin("Max.admin@admin.fr", "mdpadmin");
    }

    public Object SeConnecter(String email, String mdp) {
        if (email.equals("Max.admin@admin.fr") && mdp.equals("mdpadmin")) {
            System.out.println("Bienvenue Administrateur");
            return this.Administrateur;
        }
        for (Client connection : Liste_des_clients) {
            if (connection.getEmail().equals(email) && connection.getMotDePasse().equals(mdp)) {
                System.out.println("Bienvenue " + connection.getNom());
                return connection;
            }
        }
        System.out.println("Identifiants incorrects.");
        return null;
    }

    // ========================
    // METHODES CLIENT
    // ========================

    public void Creer_Compte() {
        System.out.println("Creation du Compte : ");
        System.out.print("Nom : ");
        String nom = scan.nextLine();
        System.out.print("Prenom : ");
        String prenom = scan.nextLine();
        System.out.print("Email : ");
        String email = scan.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scan.nextLine();
        System.out.print("Numero de telephone : ");
        String tel = scan.nextLine();
        System.out.println("Choix de l abonnement : 1:Trimestriel , 2:Semestriel , 3:Annuel");
        String typeAbo = scan.nextLine();
        String numClient = "CL-" + (Liste_des_clients.size() + 1);
        Client nouveauClient = new Client(numClient, nom, prenom, email, mdp, tel, typeAbo);
        Liste_des_clients.add(nouveauClient);
        System.out.println("Compte cree avec succes !");
    }

    public void Consulter_Compte(Client c) {
        if (c == null) {
            System.out.println("Erreur : client introuvable.");
            return;
        }
        System.out.println(" VOTRE COMPTE : ");
        System.out.println("Nom : " + c.getNom());
        System.out.println("Prenom : " + c.getPrenom());
        System.out.println("Email : " + c.getEmail());
        System.out.println("Mot de passe : " + c.getMotDePasse());
        System.out.println("Numero de telephone : " + c.getNumTel());
        System.out.println("Numero client : " + c.getNumClient());
        System.out.println("Abonnement actuel : " + c.getAbo());
        System.out.println("Statut de l abonnement : " + (c.Abo_est_il_actif() ? "ACTIF" : "INACTIF"));
    }

    public void MJ_du_mdp(Client c) {
        System.out.println("Entrez votre ancien mot de passe : ");
        String ancienMdp = scan.nextLine();
        if (c.getMotDePasse().equals(ancienMdp)) {
            System.out.println("Quel Mot de Passe souhaitez vous enregistrer : ");
            String mdp = scan.nextLine();
            c.setMdp(mdp);
            System.out.println("Nouveau Mot de Passe enregistre.");
        } else {
            System.out.println("Erreur : l ancien mot de passe est incorrect.");
        }
    }

    public void MJ_Compte(Client c) {
        System.out.println("Que souhaitez vous modifier : ");
        System.out.println("1 - Nom");
        System.out.println("2 - Prenom");
        System.out.println("3 - Email");
        System.out.println("4 - Numero de telephone");
        System.out.println("5 - Abonnement");
        String mj = scan.nextLine();

        switch (mj) {
            case "1":
                System.out.print("Nouveau Nom : ");
                c.setNom(scan.nextLine());
                System.out.println("Nouveau Nom enregistre.");
                break;
            case "2":
                System.out.print("Nouveau Prenom : ");
                c.setPrenom(scan.nextLine());
                System.out.println("Nouveau Prenom enregistre.");
                break;
            case "3":
                System.out.print("Nouvel Email : ");
                c.setEmail(scan.nextLine());
                System.out.println("Nouvel Email enregistre.");
                break;
            case "4":
                System.out.print("Nouveau Numero de Telephone : ");
                c.setTel(scan.nextLine());
                System.out.println("Nouveau Numero enregistre.");
                break;
            case "5":
                System.out.println("1 - Periodicite  2 - Etat de l abonnement  3 - Annuler");
                String rep = scan.nextLine();
                if (rep.equals("1")) {
                    System.out.println("1 - Trimestriel  2 - Semestriel  3 - Annuel");
                    c.setTypeAbo(scan.nextLine());
                    System.out.println("Periodicite mise a jour.");
                } else if (rep.equals("2")) {
                    c.Abo_devient_actif(!c.Abo_est_il_actif());
                    System.out.println("Abonnement : " + (c.Abo_est_il_actif() ? "ACTIF" : "INACTIF"));
                }
                break;
            default:
                System.out.println("Option invalide.");
        }
    }

    public void Consulter_Cours() {
        System.out.println("--- HISTORIQUE DES COURS PASSES ---");
        if (Liste_des_cours_passes.isEmpty()) {
            System.out.println("Aucun historique disponible.");
        } else {
            for (Cours c : Liste_des_cours_passes) {
                System.out.println(c.toString());
            }
        }
        System.out.println("\n--- PLANNING DES COURS FUTURS ---");
        if (Liste_des_cours_futurs.isEmpty()) {
            System.out.println("Aucun cours n est prevu.");
        } else {
            for (Cours c : Liste_des_cours_futurs) {
                System.out.println(c.toString());
            }
        }
    }

    public void Inscrire_Cours(Client c) {
        if (!c.Abo_est_il_actif()) {
            System.out.println("Erreur : Votre abonnement est INACTIF.");
            return;
        }
        System.out.println("Entrez le nom du cours auquel vous inscrire : ");
        String nomCours = scan.nextLine();
        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getType_de_cours().equalsIgnoreCase(nomCours)) {
                cours.ajouterParticipant(c);
                c.getListe_des_cours_futurs_clients().add(cours);
                System.out.println("Inscription reussie a : " + nomCours);
                this.Sauvegarder();
                return;
            }
        }
        System.out.println("Aucun cours trouve sous le nom : " + nomCours);
    }

    public void Consulter_SES_Cours_futur(Client c) {
        System.out.println(" PLANNING DES PROCHAINS COURS DONT VOUS ETES INSCRIT : ");
        List<Cours> mesCours = c.getListe_des_cours_futurs_clients();
        if (mesCours == null || mesCours.isEmpty()) {
            System.out.println("Vous n etes inscrit a aucun cours futur.");
        } else {
            for (Cours cours : mesCours) {
                System.out.println(cours.toString());
            }
        }
    }

    public void Consulter_SES_Cours_passes(Client c) {
        System.out.println(" COURS AUXQUELS VOUS AVEZ PARTICIPE : ");
        List<Cours> mesCours = c.getListe_des_cours_passes_clients();
        if (mesCours == null || mesCours.isEmpty()) {
            System.out.println("Vous n avez participe a aucun cours.");
        } else {
            for (Cours cours : mesCours) {
                System.out.println(cours.toString());
            }
        }
    }

    public void Déinscrire(Client c) {
        System.out.println("Entrez le nom du cours dont vous voulez vous deinscrire : ");
        String nomCours = scan.nextLine();
        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getType_de_cours().equalsIgnoreCase(nomCours)) {
                cours.retirerParticipant(c);
                c.getListe_des_cours_futurs_clients().remove(cours);
                System.out.println("Deinscription reussie de " + nomCours);
                this.Sauvegarder();
                return;
            }
        }
        System.out.println("Erreur : vous n etes pas inscrit au cours : " + nomCours);
    }

    public void Consulter_liste_activité() {
        System.out.println(" LES ACTIVITES : ");
        for (Cours cours : Liste_des_cours_futurs) {
            System.out.println(" - " + cours.getNomActivite());
        }
        for (Cours cours : Liste_des_cours_passes) {
            System.out.println(" - " + cours.getNomActivite());
        }
    }

    // ========================
    // METHODES ADMIN
    // ========================

    public void Consulter_Compte_client() {
        System.out.println("Liste des Clients : ");
        if (Liste_des_clients.isEmpty()) {
            System.out.println("Aucun client enregistre.");
        } else {
            for (Client c : Liste_des_clients) {
                System.out.println("Nom : " + c.getNom() + "  Prenom : " + c.getPrenom()
                        + "  Email : " + c.getEmail()
                        + "  Statut : " + (c.Abo_est_il_actif() ? "Actif" : "Suspendu"));
            }
        }
    }

    public void Recherche_Client() {
        System.out.println("Recherche de Client :");
        System.out.println("1 - Nom , 2 - Email , 3 - Telephone");
        String recherche = scan.nextLine();
        System.out.println("Entrez votre recherche : ");
        String saisi = scan.nextLine();
        boolean trouve = false;

        for (Client c : Liste_des_clients) {
            String valeur = "";
            switch (recherche) {
                case "1": valeur = c.getNom(); break;
                case "2": valeur = c.getEmail(); break;
                case "3": valeur = c.getNumTel(); break;
                default:
                    System.out.println("Critere invalide.");
                    return;
            }
            if (valeur != null && valeur.equalsIgnoreCase(saisi)) {
                System.out.println("CLIENT TROUVE : " + c.getPrenom() + " " + c.getNom()
                        + "  Email : " + c.getEmail()
                        + "  Statut : " + (c.Abo_est_il_actif() ? "Actif" : "Inactif"));
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun resultat.");
        }
    }

    public void Reactiver_abo() {
        System.out.println("REACTIVATION DE L ABONNEMENT : ");
        System.out.print("Entrez l email du client : ");
        String emailRecherche = scan.nextLine();
        boolean trouve = false;

        for (Client c : Liste_des_clients) {
            if (c.getEmail().equalsIgnoreCase(emailRecherche)) {
                trouve = true;
                if (c.Abo_est_il_actif()) {
                    System.out.println("L abonnement de " + c.getPrenom() + " est deja ACTIF.");
                } else {
                    c.Abo_devient_actif(true);
                    System.out.println("L abonnement de " + c.getPrenom() + " a ete REACTIVE.");
                    this.Sauvegarder();
                }
                break;
            }
        }
        if (!trouve) {
            System.out.println("Erreur : aucun client trouve avec l email " + emailRecherche);
        }
    }

    public void deactiver_abo() {
        System.out.println("DESACTIVATION DE L ABONNEMENT : ");
        System.out.print("Entrez l email du client : ");
        String emailRecherche = scan.nextLine();
        boolean trouve = false;

        for (Client c : Liste_des_clients) {
            if (c.getEmail().equalsIgnoreCase(emailRecherche)) {
                trouve = true;
                if (c.Abo_est_il_actif()) {
                    c.Abo_devient_actif(false);
                    System.out.println("L abonnement de " + c.getPrenom() + " est maintenant INACTIF.");
                    this.Sauvegarder();
                } else {
                    System.out.println("L abonnement de " + c.getPrenom() + " est deja INACTIF.");
                }
                break;
            }
        }
        if (!trouve) {
            System.out.println("Erreur : aucun client trouve avec l email " + emailRecherche);
        }
    }

    public void Consulter_liste_cours_passes() {
        System.out.println("HISTORIQUE DES COURS : ");
        if (Liste_des_cours_passes.isEmpty()) {
            System.out.println("Aucun cours trouve.");
        } else {
            for (Cours cours : Liste_des_cours_passes) {
                System.out.println(cours.toString());
            }
        }
    }

    public void Consulter_liste_cours_futur() {
        System.out.println("PLANNING DES COURS : ");
        if (Liste_des_cours_futurs.isEmpty()) {
            System.out.println("Aucun cours trouve.");
        } else {
            for (Cours cours : Liste_des_cours_futurs) {
                System.out.println(cours.toString());
            }
        }
    }

    public void Supprimer_cours() {
        System.out.println("Entrez le nom du cours a supprimer : ");
        String nom = scan.nextLine();
        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getType_de_cours().equalsIgnoreCase(nom)) {
                Liste_des_cours_futurs.remove(cours);
                System.out.println("Le cours " + nom + " a ete supprime.");
                this.Sauvegarder();
                return;
            }
        }
        System.out.println("Erreur : cours introuvable.");
    }

    public void Creer_cours() {
        System.out.println("CREATION DE COURS : ");
        System.out.print("Nom du cours : ");
        String typeDeCours = scan.nextLine();
        System.out.print("Activite : ");
        String activite = scan.nextLine();
        System.out.print("Nombre de places maximal : ");
        int nbrDePlace = Integer.parseInt(scan.nextLine());
        System.out.print("Nom du coach : ");
        String nomCoach = scan.nextLine();
        System.out.print("Date (Annee-Mois-Jour, ex: 2025-12-25) : ");
        LocalDate dateCours = LocalDate.parse(scan.nextLine());
        System.out.print("Heure (ex: 18:30) : ");
        String heure = scan.nextLine();
        Cours nouveauCours = new Cours(typeDeCours, activite, nbrDePlace, nomCoach, dateCours, heure);
        Liste_des_cours_futurs.add(nouveauCours);
        System.out.println("Cours cree avec succes !");
        this.Sauvegarder();
    }

    public void Modifier_Cours() {
        System.out.println("Entrez le nom du cours a modifier : ");
        String nom = scan.nextLine();
        Cours coursTrouve = null;

        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getType_de_cours().equalsIgnoreCase(nom)) {
                coursTrouve = cours;
                break;
            }
        }
        if (coursTrouve == null) {
            System.out.println("Erreur : aucun cours trouve avec ce nom.");
            return;
        }

        System.out.println("MODIFICATION DE " + coursTrouve.getType_de_cours());
        System.out.println("1 - Nom du cours");
        System.out.println("2 - Nom de l activite");
        System.out.println("3 - Nombre de places");
        System.out.print("Votre choix : ");
        String choix = scan.nextLine();

        switch (choix) {
            case "1":
                System.out.print("Nouveau nom : ");
                coursTrouve.setNomCours(scan.nextLine());
                break;
            case "2":
                System.out.print("Nouvelle activite : ");
                coursTrouve.setNomActivite(scan.nextLine());
                break;
            case "3":
                System.out.print("Nouveau nombre de places : ");
                coursTrouve.setNbrPlace(Integer.parseInt(scan.nextLine()));
                break;
            default:
                System.out.println("Option invalide.");
                return;
        }
        this.Sauvegarder();
        System.out.println("Modification reussie.");
    }

    public void Consulter_liste_cours_par_activite() {
        System.out.print("Entrez le nom de l activite recherchee : ");
        String activite = scan.nextLine();
        boolean trouve = false;
        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getNomActivite().equalsIgnoreCase(activite)) {
                System.out.println(cours.toString());
                trouve = true;
            }
        }
        for (Cours cours : Liste_des_cours_passes) {
            if (cours.getNomActivite().equalsIgnoreCase(activite)) {
                System.out.println(cours.toString());
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun cours pour cette activite.");
        }
    }

    public void Verifier_cours_populaire() {
        System.out.println("COURS LES PLUS POPULAIRES ( > 10 participants ) ");
        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getListe_Client_Inscrit().size() > 10) {
                System.out.println(cours.getType_de_cours() + " : " + cours.getListe_Client_Inscrit().size() + " participants");
            }
        }
        for (Cours cours : Liste_des_cours_passes) {
            if (cours.getListe_Client_Inscrit().size() > 10) {
                System.out.println(cours.getType_de_cours() + " : " + cours.getListe_Client_Inscrit().size() + " participants");
            }
        }
    }

    public void Verifier_cours_pas_populaire() {
        System.out.println("COURS PEU FREQUENTES ( < 2 participants ) ");
        for (Cours cours : Liste_des_cours_futurs) {
            if (cours.getListe_Client_Inscrit().size() < 2) {
                System.out.println(cours.getType_de_cours() + " : " + cours.getListe_Client_Inscrit().size() + " participants");
            }
        }
        for (Cours cours : Liste_des_cours_passes) {
            if (cours.getListe_Client_Inscrit().size() < 2) {
                System.out.println(cours.getType_de_cours() + " : " + cours.getListe_Client_Inscrit().size() + " participants");
            }
        }
    }
    
    public void trierCours(){
        LocalDate Aujourd_Hui = LocalDate.now();
        List<Cours> ADeplacer = new ArrayList<>();
        
        for (Cours c : Liste_des_cours_futurs){
            if (c.getDate().isBefore(Aujourd_Hui)){
                ADeplacer.add(c);
            }
        }
        
        for (Cours c : ADeplacer){
            Liste_des_cours_futurs.remove(c);
            Liste_des_cours_passes.add(c);
        }
        System.out.println("Tri effectué : " + ADeplacer.size() + " cours déplacés vers le passé.");
    }
    

    // ========================
    // SAUVEGARDE / CHARGEMENT
    // ========================

    public void Sauvegarder() {
        try {
            FileWriter fw = new FileWriter(this.Nom_du_fichier_sauvegarder, false);
            PrintWriter pw = new PrintWriter(fw);
            for (Client client : Liste_des_clients) {
                pw.println(client.getNumClient() + " ; " + client.getNom() + " ; " + client.getPrenom()
                        + " ; " + client.getEmail() + " ; " + client.getMotDePasse()
                        + " ; " + client.getNumTel() + " ; " + client.getAbo()
                        + " ; " + client.Abo_est_il_actif());
            }
            pw.close();
            System.out.println("Sauvegarde reussie.");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }

    public void Charger() {
        try {
            FileReader fr = new FileReader(this.Nom_du_fichier_sauvegarder);
            BufferedReader br = new BufferedReader(fr);
            String ligne;
            this.Liste_des_clients.clear();
            while ((ligne = br.readLine()) != null) {
                String[] data = ligne.split(" ; ");
                if (data.length == 8) {
                    String numClient = data[0];
                    String nom = data[1];
                    String prenom = data[2];
                    String email = data[3];
                    String mdp = data[4];
                    String tel = data[5];
                    String typeAbo = data[6];
                    boolean estActif = Boolean.parseBoolean(data[7]);
                    Client c = new Client(numClient, nom, prenom, email, mdp, tel, typeAbo);
                    c.Abo_devient_actif(estActif);
                    this.Liste_des_clients.add(c);
                }
            }
            br.close();
            System.out.println("Chargement termine : " + Liste_des_clients.size() + " clients recuperes.");
        } catch (IOException e) {
            System.out.println("Aucun fichier de sauvegarde trouve ou erreur de lecture.");
        }
    }

    // ========================
    // GETTERS
    // ========================

    public String getNom() {
        return Nom;
    }

    public List<Cours> getListeDesCoursFuturs() {
        return Liste_des_cours_futurs;
    }

    public List<Cours> getListeDesCoursPasses() {
        return Liste_des_cours_passes;
    }

    public List<Client> getListeDesClients() {
        return Liste_des_clients;
    }
}
