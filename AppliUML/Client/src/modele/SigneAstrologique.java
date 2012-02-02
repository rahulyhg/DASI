/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.modele;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Administrateur
 */
@Entity
public class SigneAstrologique {

    @Id
    private String nom;

    private int jourDebut;
    private int jourFin;
    private int moisDebut;
    private int moisFin;

    public SigneAstrologique() {
    }

    public SigneAstrologique(String nom, int jourDebut, int jourFin, int moisDebut, int moisFin) {
        this.nom = nom;
        this.jourDebut = jourDebut;
        this.jourFin = jourFin;
        this.moisDebut = moisDebut;
        this.moisFin = moisFin;
    }
    
    public String getNom()
    {
        return nom;
    }
}
