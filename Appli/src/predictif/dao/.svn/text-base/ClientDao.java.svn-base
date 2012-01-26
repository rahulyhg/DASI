/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package predictif.dao;

import java.util.List;
import javax.persistence.Query;
import predictif.modele.Client;
import predictif.util.JpaUtil;

/**
 *
 * @author Administrateur
 */
public class ClientDao {

    public void create(Client client)
    {
        JpaUtil.getEntityManager().persist(client);
    }

    public List<Client> findAllClient()
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT c FROM client c");
        return q.getResultList();
    }

    public void update(Client client)
    {
        JpaUtil.getEntityManager().merge(client);
    }
}
