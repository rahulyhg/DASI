/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package predictif.dao;

import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Query;
import predictif.modele.Horoscope;
import predictif.Util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class HoroscopeDao
{

    static private HoroscopeDao instance = null;

    public static HoroscopeDao getInstance()
    {
        if (instance == null)
        {
            instance = new HoroscopeDao();
        }
        return instance;
    }

    public void create(Horoscope unHoroscope)
    {
        JpaUtil.getEntityManager().persist(unHoroscope);
    }

    public Horoscope getHoroscope(int num)
    {
        Query q= JpaUtil.getEntityManager().createQuery("SELECT h FROM Horoscope h WHERE numHoroscope = :num");
        q.setParameter("num", num);
        return (Horoscope) q.getSingleResult();
    }

    public List<Horoscope> getHoroscopes(GregorianCalendar dateInsertion)
    {
        Query q= JpaUtil.getEntityManager().createQuery("SELECT h FROM Horoscope h WHERE dateInsertion = :dateInsertion");
        q.setParameter("dateInsertion", dateInsertion);
        return q.getResultList();
    }
    
    private HoroscopeDao()
    {
    }


}
