/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.Util;


import java.util.GregorianCalendar;
import java.util.List;
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
        serviceBD.preparerBD();
        Service service = new Service();
  
        GregorianCalendar calendar = new GregorianCalendar(1991, 10, 21);
        service.createClient("fouchet", "pierre", "rue titi", "jean@email", "0665642480", calendar,service.getAllMediums());
        service.createClient("fouchet2", "pierre", "rue titi", "jean@email", "0665642480", calendar,service.getAllMediums());
        service.createClient("fouchet3", "pierre", "rue titi", "jean@email", "0665642480", calendar,service.getAllMediums());
        service.createClient("fouchet4", "pierre", "rue titi", "jean@email", "0665642480", calendar,service.getAllMediums());
        service.createClient("fouchet5", "pierre", "rue titi", "jean@email", "0665642480", calendar,service.getAllMediums());
        service.createClient("fouchet6", "pierre", "rue titi", "jean@email", "0665642480", calendar,service.getAllMediums());
       
        
        
        
//        List<Prediction> amours = service.getPrediction(Service.TypePrediction.AMOUR);
//        List<Prediction> travaux = service.getPrediction(Service.TypePrediction.TRAVAIL);
//        List<Prediction> santes = service.getPrediction(Service.TypePrediction.SANTE);
                
//        service.createHoroscope((AmourPrediction)amours.get(0),(TravailPrediction) travaux.get(0), (SantePrediction)santes.get(0),unClient.getMediumsFavoris().get(0),unClient);
       
     //   service.verifierEmploye(99, "titi");
     //   service.verifierEmploye(2, "hashedpasswd");
        
//        service.supprimerClient(unClient);
    }

}
