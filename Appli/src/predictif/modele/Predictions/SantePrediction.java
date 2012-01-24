/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.modele.Predictions;

import javax.persistence.Entity;

/**
 *
 * @author Administrateur
 */
@Entity
public class SantePrediction extends Prediction {

    public SantePrediction() {
    }

    public SantePrediction(int niveau, String contenu) {
        this.niveau = niveau;
        this.sigle = '#';
        this.contenu = contenu;
    }

}
