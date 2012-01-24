/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.dao;

import predictif.modele.Horoscope;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class HoroscopeDao {

    static private HoroscopeDao instance = null;
    
    public static HoroscopeDao getInstance()
    {
        if(instance == null)
        {
            instance = new HoroscopeDao();
        }
        return instance;
    }
    
    public void create(Horoscope unHoroscope)
    {
        JpaUtil.getEntityManager().persist(unHoroscope);
    }
    
    private HoroscopeDao(){}
}
