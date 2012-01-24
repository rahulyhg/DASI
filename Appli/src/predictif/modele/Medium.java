/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.modele;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


/**
 *
 * @author Administrateur
 */
@Entity
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMedium;

    private String nom;

    @ManyToMany(mappedBy = "mediumsFavoris")
    private List<Client> clients;

    public int getIdMedium() {
        return idMedium;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Medium()
    {
        clients = new ArrayList<Client>();
    }

    public Medium(String nom)
    {
        this.nom = nom;
        clients = new ArrayList<Client>();
    }
}
