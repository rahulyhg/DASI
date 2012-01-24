/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.Util;


import java.util.GregorianCalendar;
import java.util.List;
import predictif.modele.Client;
import predictif.modele.Horoscope;
import predictif.modele.Predictions.AmourPrediction;
import predictif.modele.Predictions.Prediction;
import predictif.modele.Predictions.SantePrediction;
import predictif.modele.Predictions.TravailPrediction;
import predictif.service.PreparationBD;
import predictif.service.Service;

/**
 *
 * @author Administrateur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        PreparationBD serviceBD = new PreparationBD();
        Service service = new Service();
        serviceBD.preparerBD();
  
        GregorianCalendar calendar = new GregorianCalendar(1991, 10, 21);
        Client unClient = new Client("fouchet", "pierre", "rue titi", "jean@email", "0665642480", calendar, service.calculerSigneAstro(calendar), null);
        service.createClient(unClient);
        List<Prediction> amours = service.getPrediction(Service.TypePrediction.AMOUR);
        List<Prediction> travaux = service.getPrediction(Service.TypePrediction.TRAVAIL);
        List<Prediction> santes = service.getPrediction(Service.TypePrediction.SANTE);
        Horoscope unHoro = new Horoscope (null, (AmourPrediction)amours.get(0),(TravailPrediction) travaux.get(0), (SantePrediction)santes.get(0),unClient);
        unClient.addHoroscope(unHoro);
        service.updateClient(unClient);     
       
     //   service.verifierEmploye(99, "titi");
     //   service.verifierEmploye(2, "hashedpasswd");
        
        service.supprimerClient(unClient);
    }

}
