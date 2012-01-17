/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.dao;

import predictif.modele.Predictions.AmourPrediction;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class PredictionDao {

    public void create(AmourPrediction amour) {
        JpaUtil.getEntityManager().persist(amour);
    }

}
