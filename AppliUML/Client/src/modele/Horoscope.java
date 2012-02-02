/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.modele;

import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import predictif.modele.Predictions.AmourPrediction;
import predictif.modele.Predictions.SantePrediction;
import predictif.modele.Predictions.TravailPrediction;

/**
 *
 * @author Administrateur
 */
@Entity
public class Horoscope
{

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
    
    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateInsertion;

    public Horoscope()
    {
    }

    public Horoscope(Medium medium, AmourPrediction amour, TravailPrediction travail, SantePrediction sante, Client leClient)
    {
        this.medium = medium;
        this.amour = amour;
        this.travail = travail;
        this.sante = sante;
        this.leClient = leClient;
        dateInsertion = new GregorianCalendar();
    }

    public AmourPrediction getAmour()
    {
        return amour;
    }

    public Client getLeClient()
    {
        return leClient;
    }

    public Medium getMedium()
    {
        return medium;
    }

    public int getNumHoroscope()
    {
        return numHoroscope;
    }

    public SantePrediction getSante()
    {
        return sante;
    }

    public TravailPrediction getTravail()
    {
        return travail;
    }
    
    @Override
    public String toString()
    {
        StringBuilder contenu = new StringBuilder();
        contenu.append(leClient.toString());
        contenu.append("\nCher ");
        contenu.append(leClient.getNom());
        contenu.append(" , votre voyance vous est offerte par : ");
        contenu.append(medium.getNom());
        contenu.append("\nTravail : ");
        contenu.append(travail.toString());
        contenu.append("\nSanté : ");
        contenu.append(sante.toString());
        contenu.append("\nAmour : ");
        contenu.append(amour.toString());
        
        return contenu.toString();
    }
}
