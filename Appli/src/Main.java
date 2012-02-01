/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
import predictif.Util.Saisie;

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
        // Ces 2 lignes permettent de préparer la BD en "dur"
        PrepareBD serviceBD = new PrepareBD();
        serviceBD.populate();        
        
        Service service = new Service();

        // Test création de clients avec référent minimum
        // Préparation de la date
        GregorianCalendar calendar = new GregorianCalendar(1991, 10, 21);
        
        // Préparation des listes de médiums
        List<Medium> mediums = service.getAllMediums();
        
        List<Medium> listeMediums1 = new ArrayList<Medium>();
        listeMediums1.add(mediums.get(0));
        listeMediums1.add(mediums.get(1));
      
        List<Medium> listeMediums2 = new ArrayList<Medium>();
        listeMediums1.add(mediums.get(2));
        listeMediums1.add(mediums.get(3));

        // Création des clients
        String nom = Saisie.lireChaine("Entrez le nom du client : "); 
        String prenom = Saisie.lireChaine("Entrez le prenom du client : "); 
        String adresse = Saisie.lireChaine("Entrez l'adresse : "); 
        String email = Saisie.lireChaine("Entrez l'adresse email : "); 
        String tel = Saisie.lireChaine("Entrez le numéro de téléphone : ");
        
        if (service.createClient(nom, prenom, adresse, email, tel, calendar, mediums))
        {
            System.out.println("Création Ok");
        }

        if (service.createClient("Petit", "Benoit", "avenue Gaillard", "jean@titi.com", "9876543210", calendar, listeMediums1))
        {
            System.out.println("Création Ok");
        }
        
        if (service.createClient("Petit2", "Benoit2", "avenue Gaillard2", "jean2@titi.com", "7777543210", calendar, listeMediums2))
        {
            System.out.println("Création Ok");
        }
        
        
        // Test de la fonctionnalité modification de clients
        Client client = service.retrieveClient(28);
        client.setNom("J'ai été modifié");
        
        if (service.updateClient(client))
        {
            System.out.println(client.getNom() + " mis à jour");
        }
        else
        {
            System.out.println("Mise à jour de " + client.getNom());
        }
        
        
        // Test de la connexion des employés
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
        
        
        // Test des horoscopes
        // Récupération de tous les horoscopes
        List<Prediction> amours = service.getPrediction(Service.PredictionType.AMOUR);
        List<Prediction> travaux = service.getPrediction(Service.PredictionType.TRAVAIL);
        List<Prediction> santes = service.getPrediction(Service.PredictionType.SANTE);
        
        List<Client> clients = service.getAllClients();

        clients = service.getAllClients();
        client = clients.get(0);
        
        System.out.println("Création d'un horoscope pour :"+client.getNom()+" "+client.getPrenom());

        service.createHoroscope((AmourPrediction) amours.get(0), (TravailPrediction) travaux.get(0), (SantePrediction) santes.get(0), client.getMediumsFavoris().get(0), client);
        
        System.out.println(client.getHoroscope().get(0).toString());
        
        
        // Test suppression client
        service.deleteClient(service.retrieveClient(29));
    }

}
