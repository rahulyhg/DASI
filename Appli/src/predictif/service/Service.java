/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.service;

import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityTransaction;
import predictif.dao.ClientDao;
import predictif.dao.EmployeDao;
import predictif.dao.HoroscopeDao;
import predictif.dao.MediumDao;
import predictif.dao.PredictionDao;
import predictif.dao.SigneAstrologiqueDao;
import predictif.modele.Client;
import predictif.modele.Horoscope;
import predictif.modele.Predictions.Prediction;
import predictif.modele.SigneAstrologique;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class Service
{
    public enum TypePrediction {TRAVAIL, SANTE, AMOUR, TOUS};
    
    protected ClientDao clientDao;
    protected HoroscopeDao horoscopeDao;
    protected SigneAstrologiqueDao signeDao;
    protected EmployeDao employeDao;
    protected MediumDao mediumDao;
    protected PredictionDao predictionDao;
    
    public Service()
    {
        clientDao = ClientDao.getInstance();
        horoscopeDao = HoroscopeDao.getInstance();
        signeDao = SigneAstrologiqueDao.getInstance();
        employeDao = EmployeDao.getInstance();
        mediumDao = MediumDao.getInstance();
        predictionDao = PredictionDao.getInstance();
    }

    /**
     * Méthode permettant de mettre à jour le signe astrologique d'un client
     * @param client 
     */
    public void calculerSigneAstro(Client client)
    {
        client.setSigneAstrologique(calculerSigneAstro(client.getDateNaissance()));
    }

    /**
     * Méthode permettant de récuperer le signe astrologique correspondant à une
     * date de naissance.
     * Utile pour maintenir l'IHM à jour en cas de changement de date de naissance
     * Utile également lors de la création d'un nouveau client
     * @param dateNaissance une date de naissance
     * @return le <code>SigneAstrologique</code> correspondant à la date de naissance
     */
    public SigneAstrologique calculerSigneAstro(GregorianCalendar dateNaissance)
    {
        SigneAstrologique signe = null;
        JpaUtil.openEntityManager();
        
        try
        {
            signe = signeDao.retrieve(dateNaissance);
            System.out.println("commit update ok");
        }
        catch (Exception e)
        {
            System.out.println("Erreur rencontrée au create : " + e.toString());
        }
        finally
        {
            JpaUtil.closeEntityManager();
            return signe;
        }
    }

    /**
     * Méthode permettant de récupérer tous les clients présent
     * dans le moyen de persistance
     * @return la liste de clients sous la forme d'une <code>List<Client></code> la valeur de
     * retour peut valoir null si une erreur s'est passée sinon contient au minimum une liste
     * vide.
     */
    public List<Client> getAllClients()    
    {
        JpaUtil.openEntityManager();
        
        List<Client> clients = null;
        
        try        
        {
            clients = clientDao.findAllClient();
        }        
        catch (Exception e)        
        {
            System.out.println("Erreur getAllClients:" + e.getMessage());
        }
        finally        
        {
            JpaUtil.closeEntityManager();
            return clients;
        }
    }
    
    public void updateClient(Client client)
    {
        verifierDateNaissance(client);
        JpaUtil.openEntityManager();
        EntityTransaction tx = null;
        
        try        
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            clientDao.update(client);
            tx.commit();
            System.out.println("commit update ok");
        }        
        catch (Exception e)        
        {
            System.out.println("Erreur rencontrée au create : " + e.toString());
            if (tx != null && tx.isActive())            
            {
                tx.rollback();
            }
        }
        finally        
        {
            JpaUtil.closeEntityManager();
        }
    }

    /**
     * Méthode vérifiant si la date de naissance a été mise à jour par 
     * l'utilisateur et le cas échéant met à jour son signe astrologique.
     * @param client Le client dont on veut vérifier la date de naissance
     */
    private void verifierDateNaissance(Client client)
    {
        Client autreVersionClient = retrieveClient(client.getNumClient());
        
        if (client.naissancemodifie(autreVersionClient))
        {
            calculerSigneAstro(client);
        }
    }

    /**
     * Méthode permettant de retrouver une occurence de <code>Client</code>
     * à partir de son identifiant
     * @param num l'identifiant sous la forme d'un int
     * @return le client <code>Client</code>
     */
    public Client retrieveClient(int num)
    {
        Client client = null;
        JpaUtil.openEntityManager();
        try
        {
            client = clientDao.retrieveClient(num);
            System.out.println("retrieve client ok");
        }
        catch (Exception e)
        {
            System.out.println("Erreur rencontrée au create : " + e.toString());
        }
        finally
        {
            JpaUtil.closeEntityManager();
            return client;
        }
    }
        
    /**
     * Méthode permettant de supprimer un client du moyen de persistance
     * @param client le client que l'on veut supprimer de la bd
     */
    public void supprimerClient(Client client)
    {
        JpaUtil.openEntityManager();
        
        EntityTransaction tx = null;
        
        try        
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            clientDao.deleteClient(client);
            tx.commit();
            System.out.println("Le delete s'est déroulé normalement");
        }        
        catch (Exception e)        
        {
            System.out.println("Erreur rencontrée au delete : " + e.toString());
            if (tx != null && tx.isActive())            
            {
                tx.rollback();
            }
        }        
        finally        
        {
            JpaUtil.closeEntityManager();
        }        
    }

    /**
     * Permet de persister un client en BD. Prend en charge l'attribution du client
     * ainsi que le calcul du signe astrologique
     * @param client 
     */
    public void createClient(Client client)    
    {
        //Ceci ne sera pas forcément utile, tout dépendra de l'IHM final.
        calculerSigneAstro(client);
        
        JpaUtil.openEntityManager();
        EntityTransaction tx = null;
        
        try
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            clientDao.create(client);
            tx.commit();
            System.out.println("commit ok");
        }
        catch (Exception e)
        {
            System.out.println("Erreur rencontrée au create : " + e.toString());
            if (tx != null && tx.isActive())
            {
                tx.rollback();
            }
        }
        finally
        {
            JpaUtil.closeEntityManager();
        }
    }

    /**UTILISER A LA PLACE updateClient()
     * Méthode permettant de créer un horoscope pour un client. Cette méthode
     * se charge de l'ajout de l'horoscope à la liste possédé par le client.
     * @param unClient
     * @param unHoro 
     */
    @Deprecated
    public void createHoro(Client unClient, Horoscope unHoro)
    {
        unClient.addHoroscope(unHoro);
        JpaUtil.openEntityManager();
        
        EntityTransaction tx = null;
        
        try
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            horoscopeDao.create(unHoro);
            clientDao.update(unClient);
            tx.commit();
            System.out.println("commit ok sur ajout horo");
        }
        catch (Exception e)
        {
            System.out.println("Erreur rencontrée au create : " + e.toString());
            if (tx != null && tx.isActive())
            {
                tx.rollback();
            }
        }
        finally
        {
            JpaUtil.closeEntityManager();
        }
    }

    /**
     * Méthode permettant de vérifier l'identité de l'employé
     * @param codeEmploye le code de l'employé
     * @param passwd hashed
     */
    public void verifierEmploye(int codeEmploye, String passwd)
    {
        JpaUtil.openEntityManager();
        try
        {
            if (employeDao.findEmploye(codeEmploye, passwd) != null)
            {
                //Connexion Ok, passage écran suivant
            }
            else
            {
                System.out.println("Erreur connexion, employe/mdp inexistant");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur technique : "+e.getMessage());
        }
        finally
        {
            JpaUtil.closeEntityManager();
        }   
    }
    
    /**
     * Méthode permettant de récuperer toutes les prédictions d'un type
     * particulier. Types acceptés:
     * <ul>
     *  <li>Service.TypePrediction.AMOUR</li>
     *  <li>Service.TypePrediction.TRAVAIL</li>
     *  <li>Service.TypePrediction.SANTE</li>
     *  <li>Service.TypePrediction.TOUS permet de récuperer tous les types
     * précédemment cités</li>
     * </ul>
     * @param type le type de prédiction souhaité, variable de type enum
     * @return la <code>liste de <code>Prediction</code>
     */
    public List<Prediction> getPrediction(TypePrediction type)
    {
        List<Prediction> predictions = null;
        
        JpaUtil.openEntityManager();
        try
        {
            switch(type)
            {
                case AMOUR : predictions = predictionDao.getAllPredictionAmour();
                    break;
                case TRAVAIL : predictions = predictionDao.getAllPredictionTravail();
                    break;
                case SANTE : predictions = predictionDao.getAllPredictionSante();
                    break;
                case TOUS : predictions = predictionDao.getllAllPredictions();
                    break;
                default:;
            }
        }
        catch (Exception e)
        {
            System.out.println("Erreur dans le getPrediction "+e.getMessage());
        }
        finally
        {
            JpaUtil.closeEntityManager();
            return predictions;
        }
        
    }
}
