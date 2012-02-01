/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.dao;

import java.util.List;
import javax.persistence.Query;
import predictif.modele.Employe;
import predictif.Util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class EmployeDao
{

    static private EmployeDao instance = null;

    public static EmployeDao getInstance()
    {
        if (instance == null)
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
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM Employe e");
        return q.getResultList();
    }

    public void update(Employe employe)
    {
        JpaUtil.getEntityManager().merge(employe);
    }

    public Employe findMinusEmploye()
    {
        Employe employeMin = null;
        List<Employe> employes = findAllEmploye();
        if (!employes.isEmpty())
        {
            employeMin = employes.get(0);

            for (Employe employe : employes)
            {
                if (employeMin.getClients().size() > employe.getClients().size())
                {
                    employeMin = employe;
                }
            }
        }
        return employeMin;
    }

    protected EmployeDao()
    {
    }

    public void create(List<Employe> employes)
    {
        for (Employe employe : employes)
        {
            create(employe);
        }
    }

    public Employe findEmploye(int num)
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM Employe e WHERE e.codeEmploye = :num");
        q.setParameter("num", num);
        return (Employe) q.getSingleResult();
    }

}
