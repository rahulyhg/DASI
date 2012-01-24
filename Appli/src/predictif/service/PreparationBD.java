/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityTransaction;
import predictif.dao.ClientDao;
import predictif.dao.EmployeDao;
import predictif.dao.MediumDao;
import predictif.dao.PredictionDao;
import predictif.dao.SigneAstrologiqueDao;
import predictif.modele.Employe;
import predictif.modele.Medium;
import predictif.modele.Predictions.AmourPrediction;
import predictif.modele.Predictions.SantePrediction;
import predictif.modele.Predictions.TravailPrediction;
import predictif.modele.SigneAstrologique;
import predictif.util.JpaUtil;

/**
 *
 * @author Pitou
 */
public class PreparationBD
{

    protected EmployeDao employeDao;
    protected MediumDao mediumDao;
    protected SigneAstrologiqueDao signeDao;
    protected PredictionDao predictionDao;
    protected ClientDao clientDao;

    public PreparationBD()
    {
        employeDao = EmployeDao.getInstance();
        mediumDao = MediumDao.getInstance();
        signeDao = SigneAstrologiqueDao.getInstance();
        predictionDao = PredictionDao.getInstance();
        clientDao = ClientDao.getInstance();
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

        ArrayList<Employe> employes = new ArrayList<Employe>();
        employes.add(new Employe("Thibaut", "Bernard", "hashedpasswd"));
        employes.add(new Employe("Boris", "Aloin", "hashedpasswd"));
        employes.add(new Employe("Jean", "Durand", "hashedpasswd"));
        employes.add(new Employe("Jean", "Dupond", "hashedpasswd"));
        employes.add(new Employe("Gosouep", "Dasilva", "hashedpasswd"));
        employes.add(new Employe("Icare", "Rodriguez", "hashedpasswd"));

        ArrayList<SigneAstrologique> signes = new ArrayList<SigneAstrologique>();
        signes.add(new SigneAstrologique("Bélier", 21, 19, 3, 4));
        signes.add(new SigneAstrologique("Taureau", 20, 20, 4, 5));
        signes.add(new SigneAstrologique("Gémeaux", 21, 21, 5, 6));
        signes.add(new SigneAstrologique("Cancer", 22, 22, 6, 7));
        signes.add(new SigneAstrologique("Lion", 23, 22, 7, 8));
        signes.add(new SigneAstrologique("Vierge", 23, 22, 8, 9));
        signes.add(new SigneAstrologique("Balance", 23, 22, 9, 10));
        signes.add(new SigneAstrologique("Scorpion", 23, 22, 10, 11));
        signes.add(new SigneAstrologique("Sagittaire", 23, 21, 11, 12));
        signes.add(new SigneAstrologique("Verseau", 21, 18, 1, 2));
        signes.add(new SigneAstrologique("Poisson", 19, 20, 2, 3));

        ArrayList<TravailPrediction> travailPredictions = new ArrayList<TravailPrediction>();
        travailPredictions.add(new TravailPrediction(1, "vide"));
        travailPredictions.add(new TravailPrediction(5, "vide1"));
        travailPredictions.add(new TravailPrediction(2, "vide2"));
        travailPredictions.add(new TravailPrediction(1, "vide3"));
        travailPredictions.add(new TravailPrediction(2, "vide4"));
        travailPredictions.add(new TravailPrediction(4, "vide5"));

        ArrayList<SantePrediction> santePredictions = new ArrayList<SantePrediction>();
        santePredictions.add(new SantePrediction(1, "vide"));
        santePredictions.add(new SantePrediction(2, "vide2"));
        santePredictions.add(new SantePrediction(4, "vide3"));
        santePredictions.add(new SantePrediction(3, "vide4"));
        santePredictions.add(new SantePrediction(2, "vide5"));

        List<AmourPrediction> amourPredictions = new ArrayList<AmourPrediction>();
        amourPredictions.add(new AmourPrediction(2, "Pas de chance en amour"));
        amourPredictions.add(new AmourPrediction(4, "En ce qui concerne votre vie sentimentale actuelle ou à venir, votre charme sera sûr d'opérer."));
        amourPredictions.add(new AmourPrediction(3, "L'amour arrive souvent là ou on s'y attend le moins, et vous en aurez un bel exemple aujourd'hui."));
        amourPredictions.add(new AmourPrediction(3, "Du nouveau au niveau de votre vie amoureuse qui se portera bien mieux que ces derniers jours."));

        ArrayList<Medium> mediums = new ArrayList<Medium>();
        mediums.add(new Medium("Madame Irma"));
        mediums.add(new Medium("Madame Soleil"));
        mediums.add(new Medium("Big Brother"));
        mediums.add(new Medium("Luna"));
        mediums.add(new Medium("Omni"));


        JpaUtil.openEntityManager();
        EntityTransaction tx = null;

        try
        {
            tx = JpaUtil.getEntityManagerTransaction();
            tx.begin();
            //Remplissage
            employeDao.create(employes);
            signeDao.create(signes);
            mediumDao.create(mediums);
            predictionDao.create(amourPredictions);
            predictionDao.create(travailPredictions);
            predictionDao.create(santePredictions);
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
