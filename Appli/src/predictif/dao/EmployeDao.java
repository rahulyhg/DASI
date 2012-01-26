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
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM Employe e");
        return q.getResultList();
    }

    public void update(Employe employe)
    {
        JpaUtil.getEntityManager().merge(employe);
    }

    public Employe findEmploye(int codeEmploye, String passwd)
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM Employe e WHERE e.codeEmploye = :codeEmploye AND e.password = :mdp");
        q.setParameter("codeEmploye", codeEmploye);
        q.setParameter("mdp", passwd);
        return (Employe)q.getSingleResult();
    }
    
    public Employe findMinusEmploye()
    {
        Employe employeMin = null;
        List<Employe> employes = findAllEmploye();
        if(!employes.isEmpty())
        {
            employeMin = employes.get(0);
            
            for(Employe employe : employes)
            {
                if(employeMin.getClients().size() > employe.getClients().size())
                {
                    employeMin = employe;
                }
            }
        }
        return employeMin;
//        System.out.println("Entré dans findMinus");
//        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM Employe e LEFT JOIN FETCH e.referent");
//
////        Query q2 = JpaUtil.getEntityManager().createQuery("SELECT e.codeEmploye, COUNT(c.numCLient) AS \"nombre de clients\" "
////                + "FROM Employe e LEFT JOIN Client c ON e.codeEmploye=c.referent_codeEmploye "
////                + "GROUP BY e.codeEmploye ORDER BY COUNT(c.numClient) ASC");
//        List<Employe> employes = q.getResultList();
////        Employe employe = (Employe)q.getSingleResult();
//        for(Employe employe : employes)
//        {
//           System.out.println("nom de l'employé min :"+employe.getNom());
//            return employe;
//        }
    }
    
    protected EmployeDao(){}

    public void create(List<Employe> employes)
    {
        for(Employe employe : employes)
        {
            create(employe);
        }
    }

    public Employe findEmploye(int num)
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT e FROM Employe e WHERE e.codeEmploye = :num");
        q.setParameter("num", num);
        return (Employe)q.getSingleResult();
    }
}
