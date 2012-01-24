/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import predictif.modele.Predictions.AmourPrediction;
import predictif.modele.Predictions.SantePrediction;
import predictif.modele.Predictions.TravailPrediction;

/**
 *
 * @author Administrateur
 */
@Entity
public class Horoscope {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numHoroscope;

    @ManyToOne
    private Medium medium;

    @ManyToOne
    private AmourPrediction amour;

    @ManyToOne
    private TravailPrediction travail;

    @ManyToOne
    private SantePrediction sante;

    @ManyToOne
    private Client leClient;

    public Horoscope() {
    }

    public Horoscope(Medium medium, AmourPrediction amour, TravailPrediction travail, SantePrediction sante, Client leClient) {
        this.medium = medium;
        this.amour = amour;
        this.travail = travail;
        this.sante = sante;
        this.leClient = leClient;
    }

    

}
