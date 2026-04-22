/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ptraitement;


import java.util.Scanner;

/**
 *
 * @author maxen
 */
public class Salledesport_projet2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Salle salle = new Salle("LE SPORT AU MAX", "sauvegarde.txt");

        salle.Charger();

        System.out.println("======================================");
        System.out.println("  BIENVENUE A " + salle.getNom());
        System.out.println("======================================");

        if (salle.getListeDesCoursFuturs().isEmpty()) {
            ajouterCoursDemonstration(salle);
        }

        boolean continuer = true;

        while (continuer) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Se connecter");
            System.out.println("2. Creer un compte client");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    menuConnexion(scanner, salle);
                    break;
                case "2":
                    salle.Creer_Compte();
                    salle.Sauvegarder();
                    break;
                case "3":
                    System.out.println("Au revoir !");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        }

        scanner.close();
    }

    public static void menuConnexion(Scanner scanner, Salle salle) {
        System.out.println("\n--- CONNEXION ---");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        Object utilisateur = salle.SeConnecter(email, mdp);

        if (utilisateur == null) {
            System.out.println("Echec de la connexion. Veuillez reessayer.");
            return;
        }

        if (utilisateur instanceof Admin) {
            menuAdmin(scanner, salle);
        } else if (utilisateur instanceof Client) {
            menuClient(scanner, salle, (Client) utilisateur);
        }
    }

    public static void menuClient(Scanner scanner, Salle salle, Client client) {
        boolean deconnexion = false;

        while (!deconnexion) {
            System.out.println("\n--- MENU CLIENT ---");
            System.out.println("Bienvenue " + client.getPrenom() + " " + client.getNom());
            System.out.println("1.  Consulter mon compte");
            System.out.println("2.  Modifier mon mot de passe");
            System.out.println("3.  Modifier mes informations personnelles");
            System.out.println("4.  Consulter tous les cours");
            System.out.println("5.  M inscrire a un cours");
            System.out.println("6.  Voir mes cours futurs");
            System.out.println("7.  Voir mes cours passes");
            System.out.println("8.  Me desinscrire d un cours");
            System.out.println("9.  Consulter la liste des activites");
            System.out.println("10. Se deconnecter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":  salle.Consulter_Compte(client); break;
                case "2":  salle.MJ_du_mdp(client); salle.Sauvegarder(); break;
                case "3":  salle.MJ_Compte(client); salle.Sauvegarder(); break;
                case "4":  salle.Consulter_Cours(); break;
                case "5":  salle.Inscrire_Cours(client); break;
                case "6":  salle.Consulter_SES_Cours_futur(client); break;
                case "7":  salle.Consulter_SES_Cours_passes(client); break;
                case "8":  salle.Déinscrire(client); break;
                case "9":  salle.Consulter_liste_activité(); break;
                case "10": deconnexion = true; System.out.println("Deconnexion reussie."); break;
                default:   System.out.println("Choix invalide.");
            }
        }
    }

    public static void menuAdmin(Scanner scanner, Salle salle) {
        boolean deconnexion = false;

        while (!deconnexion) {
            System.out.println("\n--- MENU ADMINISTRATEUR ---");
            System.out.println("1.  Consulter tous les clients");
            System.out.println("2.  Rechercher un client");
            System.out.println("3.  Reactiver un abonnement");
            System.out.println("4.  Desactiver un abonnement");
            System.out.println("5.  Consulter les cours passes");
            System.out.println("6.  Consulter les cours futurs");
            System.out.println("7.  Creer un cours");
            System.out.println("8.  Modifier un cours");
            System.out.println("9.  Supprimer un cours");
            System.out.println("10. Consulter les cours par activite");
            System.out.println("11. Voir les cours populaires (>10 inscrits)");
            System.out.println("12. Voir les cours peu frequentes (<2 inscrits)");
            System.out.println("13. Se deconnecter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":  salle.Consulter_Compte_client(); break;
                case "2":  salle.Recherche_Client(); break;
                case "3":  salle.Reactiver_abo(); break;
                case "4":  salle.deactiver_abo(); break;
                case "5":  salle.Consulter_liste_cours_passes(); break;
                case "6":  salle.Consulter_liste_cours_futur(); break;
                case "7":  salle.Creer_cours(); break;
                case "8":  salle.Modifier_Cours(); break;
                case "9":  salle.Supprimer_cours(); break;
                case "10": salle.Consulter_liste_cours_par_activite(); break;
                case "11": salle.Verifier_cours_populaire(); break;
                case "12": salle.Verifier_cours_pas_populaire(); break;
                case "13": deconnexion = true; System.out.println("Deconnexion reussie."); break;
                default:   System.out.println("Choix invalide.");
            }
        }
    }

    public static void ajouterCoursDemonstration(Salle salle) {
        System.out.println("Ajout de cours de demonstration...");
        salle.getListeDesCoursFuturs().add(new Cours("Yoga",        "Yoga",        15, "Sophie", java.time.LocalDate.now().plusDays(2), "18:30"));
        salle.getListeDesCoursFuturs().add(new Cours("Musculation", "Musculation", 10, "Thomas", java.time.LocalDate.now().plusDays(3), "19:00"));
        salle.getListeDesCoursFuturs().add(new Cours("Crossfit",    "Crossfit",    20, "Julien", java.time.LocalDate.now().plusDays(5), "17:30"));
        salle.getListeDesCoursFuturs().add(new Cours("Pilates",     "Pilates",     12, "Marie",  java.time.LocalDate.now().plusDays(7), "10:00"));
        System.out.println("4 cours de demonstration ajoutes !");
    }
}
