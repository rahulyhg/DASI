/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.dao;

import java.util.List;
import javax.persistence.Query;
import predictif.modele.Predictions.Prediction;
import predictif.Util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class PredictionDao
{

    static private PredictionDao instance;

    public static PredictionDao getInstance()
    {
        if (instance == null)
        {
            instance = new PredictionDao();
        }

        return instance;

    }

    private PredictionDao()
    {
    }

    public void create(Prediction prediction)
    {
        JpaUtil.getEntityManager().persist(prediction);
    }

    public void create(List<? extends Prediction> predictions)
    {
        for (Prediction prediction : predictions)
        {
            create(prediction);
        }
    }

    public List<Prediction> getAllPredictionAmour()
    {
        Query q = JpaUtil.getEntityManager().createQuery(
                "SELECT p FROM AmourPrediction p");
        return q.getResultList();
    }

    public List<Prediction> getAllPredictionTravail()
    {
        Query q = JpaUtil.getEntityManager().createQuery(
                "SELECT p FROM TravailPrediction p");
        return q.getResultList();
    }

    public List<Prediction> getAllPredictionSante()
    {
        Query q = JpaUtil.getEntityManager().createQuery(
                "SELECT p FROM SantePrediction p");
        return q.getResultList();
    }

    public List<Prediction> getllAllPredictions()
    {
        Query q = JpaUtil.getEntityManager().createQuery(
                "SELECT p FROM Prediction p");
        return q.getResultList();

    }

}
