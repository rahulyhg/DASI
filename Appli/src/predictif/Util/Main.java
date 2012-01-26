/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.Util;

import java.util.GregorianCalendar;
import java.util.List;
import predictif.modele.Client;
import predictif.modele.Medium;
import predictif.modele.Predictions.AmourPrediction;
import predictif.modele.Predictions.Prediction;
import predictif.modele.Predictions.SantePrediction;
import predictif.modele.Predictions.TravailPrediction;
import predictif.service.PrepareBD;
import predictif.service.Service;

/**
 *
 * @author Administrateur
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        PrepareBD serviceBD = new PrepareBD();
        serviceBD.populate();
        Service service = new Service();

        List<Client> clients = service.getAllClients();
        Client client = clients.get(0);
        client.setNom("J'ai été modifié");
        if (service.updateClient(client))
        {
            System.out.println(client.getNom() + " mis à jour");
        }
        else
        {
            System.out.println("Mise à jour de " + client.getNom());
        }

        service.deleteClient(service.retrieveClient(228));
        service.deleteClient(service.retrieveClient(229));
        service.deleteClient(service.retrieveClient(230));
        GregorianCalendar calendar = new GregorianCalendar(1991, 10, 21);
        List<Medium> mediums = service.getAllMediums();
        service.createClient("Durant", "Alphonse", "rue Jean Baffier", "Alph.Durant@hotmail.com", "0123456789", calendar, mediums);
        service.retrieveClient(31);

        service.createClient("Petit", "Benoit", "avenue Gaillard", "jean@titi.com", "9876543210", calendar, mediums);
        service.createClient("Petit2", "Benoit", "avenue Gaillard", "jean@titi.com", "9876543210", calendar, mediums);

        List<Prediction> amours = service.getPrediction(Service.PredictionType.AMOUR);
        List<Prediction> travaux = service.getPrediction(Service.PredictionType.TRAVAIL);
        List<Prediction> santes = service.getPrediction(Service.PredictionType.SANTE);
        clients = service.getAllClients();
        client = clients.get(0);

        service.createHoroscope((AmourPrediction) amours.get(0), (TravailPrediction) travaux.get(0), (SantePrediction) santes.get(0), client.getMediumsFavoris().get(0), client);
        service.connectEmploye(99, "titi");
        boolean verifierEmploye = service.connectEmploye(2, "hashedpasswd");
        if (verifierEmploye)
        {
            System.out.println("Connexion ok");
        }
        else
        {
            System.out.println("Connexion ko");
        }
        service.deleteClient(client);
    }

}
