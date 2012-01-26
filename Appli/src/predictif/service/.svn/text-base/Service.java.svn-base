/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.persistence.EntityTransaction;
import predictif.dao.ClientDao;
import predictif.dao.EmployeDao;
import predictif.dao.MediumDao;
import predictif.dao.PredictionDao;
import predictif.dao.SigneAstrologiqueDao;
import predictif.modele.Client;
import predictif.modele.Employe;
import predictif.modele.Medium;
import predictif.modele.Predictions.AmourPrediction;
import predictif.modele.SigneAstrologique;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class Service {

    protected ClientDao clientDao;
    protected EmployeDao employeDao;
    protected MediumDao mediumDao;
    protected SigneAstrologiqueDao signeDao;
    protected PredictionDao predictionDao;

    public Service() {
        clientDao = new ClientDao();
        employeDao = new EmployeDao();
        mediumDao = new MediumDao();
        signeDao = new SigneAstrologiqueDao();
        predictionDao = new PredictionDao();
    }

    public void calculerSigneAstro(Client client)
    {
        client.setSigneAstrologique(signeDao.retrieve(client.getDateNaissance()));
    }

    public void createClient(Client client)
    {
        JpaUtil.openEntityManager();

        EntityTransaction tx = null;

        try
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            clientDao.create(client);
            tx.commit();
            System.out.println("commit ok");
        } catch (Exception e) {
            System.out.println("Erreur rencontrée au create : " + e.toString());
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }
        finally {
           JpaUtil.closeEntityManager();
        }
    }

    /**
     * Remplissage de la BD en dur :
     * <ul>
     *  <li>Prédictions</li>
     *  <li>Signes astro</li>
     *  <li>Employés</li>
     *  <li>Médiums</li>
     * </ul>
     */
    public void preparerBD()
    {
        Employe employe = new Employe("Jean", "Titi");
        Medium medium = new Medium("Madame Irma");
        ArrayList<SigneAstrologique> signes = new ArrayList<SigneAstrologique>();
        signes.add(new SigneAstrologique("Bélier",21,19,3,4));
        signes.add(new SigneAstrologique("Taureau",20,20,4,5));
        signes.add(new SigneAstrologique("Gémeaux",21,21,5,6));
        signes.add(new SigneAstrologique("Cancer",22,22,6,7));
        signes.add(new SigneAstrologique("Lion",23,22,7,8));
        signes.add(new SigneAstrologique("Vierge",23,22,8,9));
        signes.add(new SigneAstrologique("Balance",23,22,9,10));
        signes.add(new SigneAstrologique("Scorpion",23,22,10,11));
        signes.add(new SigneAstrologique("Sagittaire",23,21,11,12));
        signes.add(new SigneAstrologique("Verseau",21,18,1,2));
        signes.add(new SigneAstrologique("Poisson",19,20,2,3));

        GregorianCalendar date = new GregorianCalendar(1991,10,21);
        AmourPrediction amour = new AmourPrediction(2, '+', "Pas de chance en amour");
        JpaUtil.openEntityManager();
        EntityTransaction tx = null;

        try
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            //Remplissage
            employeDao.create(employe);
            mediumDao.create(medium);
            signeDao.create(signes);
            tx.commit();
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            Client client = new Client("Toto", "Toto", "20 avenue", "jean@", "0202020202",date,signeDao.retrieve(date),null);
            clientDao.create(client);
            predictionDao.create(amour);
            tx.commit();

        } 
        catch (Exception e)
        {
            System.out.println("Erreur rencontrée à preparerBD : " + e.toString());
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

}
