/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.dao;

import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Query;
import predictif.modele.SigneAstrologique;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class SigneAstrologiqueDao
{

    static private SigneAstrologiqueDao instance = null;

    public static SigneAstrologiqueDao getInstance()
    {
        if (instance == null)
        {
            instance = new SigneAstrologiqueDao();
        }
        return instance;
    }

    public void create(SigneAstrologique signe)
    {
        JpaUtil.getEntityManager().persist(signe);
    }

    public void create(List<SigneAstrologique> signes)
    {
        for (SigneAstrologique signe : signes)
        {
            create(signe);
        }
    }

    public SigneAstrologique retrieve(GregorianCalendar date)
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT s FROM SigneAstrologique s WHERE (:extractedDay >= s.jourDebut AND :extractedMonth = s.moisDebut) OR (:extractedMonth = s.moisFin AND :extractedDay <= s.jourFin)");
        q.setParameter("extractedDay", date.get(GregorianCalendar.DATE));
        q.setParameter("extractedMonth", date.get(GregorianCalendar.MONTH));

        return (SigneAstrologique) q.getSingleResult();
    }

    private SigneAstrologiqueDao()
    {
    }

}
