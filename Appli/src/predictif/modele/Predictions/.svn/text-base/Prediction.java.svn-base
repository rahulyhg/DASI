/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.modele.Predictions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Administrateur
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected int niveau;
    static protected char sigle;
    protected String contenu;
}
