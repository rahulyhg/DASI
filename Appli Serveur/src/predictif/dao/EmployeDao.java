/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.dao;

import java.util.List;
import javax.persistence.Query;
import predictif.modele.Employe;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class EmployeDao {

    public void create(Employe employe)
    {
        JpaUtil.getEntityManager().persist(employe);
    }

    public List<Employe> findAllEmploye()
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM employe e");
        return q.getResultList();
    }

    public void update(Employe employe)
    {
        JpaUtil.getEntityManager().merge(employe);
    }

}
