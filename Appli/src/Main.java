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
        // Petite protection : si on ne sait pas quoi faire, affiche le message d'help
        if (args.length == 0)
        {
            System.out.println("Erreur, argument absent. Tapez 'help' pour l'aide.");
            
            System.exit(-1); // arrête le programme
        }
        
        // Ces 2 lignes permettent de préparer la BD en "dur"
        PrepareBD serviceBD = new PrepareBD();
        serviceBD.populate();        
        
        Service service = new Service();
        
        // Maintenant on va chercher à comprendre les arguments passés
        // malheureusement en Java, on ne peut pas faire un switch sur un String
        if (args[0].equals("creer-client") == true)
        {
            // On demande les champs "normaux"
            String nom = Saisie.lireChaine("Entrez le nom du client : ");
            String prenom = Saisie.lireChaine("Entrez le prenom du client : ");
            String adresse = Saisie.lireChaine("Entrez l'adresse : ");
            String email = Saisie.lireChaine("Entrez l'adresse email : ");
            String tel = Saisie.lireChaine("Entrez le numéro de téléphone : ");
            
            // La date de naissance demande plusieur étapes
            String dateNaissance = Saisie.lireChaine("Entrez la date de naissance du client (format DD/MM/YYYY) : ");
            String date[] = dateNaissance.split("/");     
            
            if (date.length != 3)
            {
                System.out.println("Erreur, date de naissance invalide");
                return;
            }            
            
            GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            
            // Dernière étape : les médiums
            List<Medium> mediums = service.getAllMediums();
            
            for (int i=0;i<mediums.size();i++)
            {
                System.out.println("Medium n° " + i + " - " + mediums.get(i).getNom());               
            }
            String mediumsStr = Saisie.lireChaine("Entrez les numéros des médiums favoris du client (séparés par des espaces) : ");
            String mediumsTab[] = mediumsStr.split(" ");
            
            List<Medium> mediumsFavoris = new ArrayList<Medium>();
            
            for (int i=0;i<mediumsTab.length;i++)
            {
                mediumsFavoris.add(mediums.get(Integer.parseInt(mediumsTab[i]))); // un peu long, récupère le médium dont on a le numéro
            }
            
            if (service.createClient(nom, prenom, adresse, email, tel, calendar, mediumsFavoris))
            {
                System.out.println("Création du client Ok");
            }
        }
        else if (args[0].equals("modifier-client") == true)
        {
            // On liste les clients
            List<Client> clients = service.getAllClients();
            
            // Les affiche
            for (int i=0;i<clients.size();i++)
            {
                System.out.println("Client n° " + clients.get(i).getNumClient() + " - " + clients.get(i).getPrenom() + " " + clients.get(i).getNom());
            }
            
            // On demande lequel on souhaite modifier
            String numStr = Saisie.lireChaine("Entrez le numéro du client à modifier : ");
            Client client = service.retrieveClient(Integer.parseInt(numStr));
            
            System.out.println("Nous allons vous demander les nouvelles valeurs. Pour ne pas modifier la valeur, faites entrer.");
        
            // On demande les champs "normaux"
            String nom = Saisie.lireChaine("Entrez le nouveau nom du client : ");
            if (nom.equals("") == false)
            {
                client.setNom(nom);
            }
            
            String prenom = Saisie.lireChaine("Entrez le nouveau prenom du client : ");
            if (prenom.equals("") == false)
            {
                client.setPrenom(prenom);
            }
            
            String adresse = Saisie.lireChaine("Entrez la nouvelle adresse : ");
            if (adresse.equals("") == false)
            {
                client.setAdressePostale(adresse);
            }
            
            String email = Saisie.lireChaine("Entrez le nouvel email : ");
            if (email.equals("") == false)
            {
                client.setEmail(email);
            }
            
            String tel = Saisie.lireChaine("Entrez le nouveau numéro de téléphone : ");
            if (tel.equals("") == false)
            {
                client.setTel(tel);
            }
            
            // La date de naissance demande plusieur étapes
            /*String dateNaissance = Saisie.lireChaine("Entrez la nouvelle date de naissance du client (format DD/MM/YYYY) : ");
            String date[] = dateNaissance.split("/");     
            
            if (date.length != 3)
            {
                System.out.println("Erreur, date de naissance invalide");
                return;
            }            
            
            GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            */
            // Dernière étape : les médiums
            /*List<Medium> mediums = service.getAllMediums();
            
            for (int i=0;i<mediums.size();i++)
            {
                System.out.println("Medium n° " + i + " - " + mediums.get(i).getNom());               
            }
            String mediumsStr = Saisie.lireChaine("Entrez les numéros des médiums favoris du client (séparés par des espaces) : ");
            String mediumsTab[] = mediumsStr.split(" ");
            
            List<Medium> mediumsFavoris = new ArrayList<Medium>();
            
            for (int i=0;i<mediumsTab.length;i++)
            {
                mediumsFavoris.add(mediums.get(Integer.parseInt(mediumsTab[i]))); // un peu long, récupère le médium dont on a le numéro
            }*/
        }

        /*List<Medium> listeMediums1 = new ArrayList<Medium>();
        listeMediums1.add(mediums.get(0));
        listeMediums1.add(mediums.get(1));

        List<Medium> listeMediums2 = new ArrayList<Medium>();
        listeMediums1.add(mediums.get(2));
        listeMediums1.add(mediums.get(3));*/

        /*if (service.createClient("Petit", "Benoit", "avenue Gaillard", "jean@titi.com", "9876543210", calendar, listeMediums1))
        {
            System.out.println("Création Ok");
        }
        
        if (service.createClient("Petit2", "Benoit2", "avenue Gaillard2", "jean2@titi.com", "7777543210", calendar, listeMediums2))
        {
            System.out.println("Création Ok");
        }*/
        
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
    
    /*
     * Private (à priori non accessible directement)
     * Pour créer un client en mode interactif
     */
    private  void createClient()
    {

    }

}
