/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import predictif.modele.Employe;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class EmployeDao {
    
    static private EmployeDao instance = null;

    public static EmployeDao getInstance() {
        if(instance == null)
        {
            instance = new EmployeDao();
        }
        return instance;
    }

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

    public Employe findEmploye(int codeEmploye, String passwd)
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM employe e WHERE e.codeEmploye = :codeEmploye AND e.password = :mdp");
        q.setParameter("codeEmploye", codeEmploye);
        q.setParameter("mdp", passwd);
        return (Employe)q.getSingleResult();
    }
    
    public Employe findMinusEmploye() throws Exception
    {
        throw new Exception("A implémenter");
    }
    
    protected EmployeDao(){}

    public void create(List<Employe> employes)
    {
        for(Employe employe : employes)
        {
            create(employe);
        }
    }
}
