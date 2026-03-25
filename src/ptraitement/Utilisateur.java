/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ptraitement;


/**
 *
 * @author maxen
 */
public class Utilisateur {

    private String Email;
    private String Mdp;

    public Utilisateur(String email, String mdp) {
        this.Email = email;
        this.Mdp = mdp;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getMdp() {
        return this.Mdp;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setMdp(String mdp) {
        this.Mdp = mdp;
    }
}
