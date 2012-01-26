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
public class ClientDao
{

    static private ClientDao instance = null;

    static public ClientDao getInstance()
    {
        if (instance == null)
        {
            instance = new ClientDao();
        }
        return instance;
    }

    /**
     * A la création, value également l'attribut referent de type employé.
     * Rappel : le referent est celui qui possède le moins de clients.
     * Si il faut persister sans valuer cet attribuer, voir createSansRef
     * @param client 
     */
    public void create(Client client)
    {
        JpaUtil.getEntityManager().persist(client);
    }

    public List<Client> findAllClient()
    {
        Query q = JpaUtil.getEntityManager().createQuery("SELECT c FROM Client c");
        return q.getResultList();
    }

    public void update(Client client)
    {
        JpaUtil.getEntityManager().merge(client);
    }

    public Client retrieveClient(int num)
    {
        return (Client) JpaUtil.getEntityManager().find(Client.class, num);
    }

    public void deleteClient(Client client)
    {
        JpaUtil.getEntityManager().remove(JpaUtil.getEntityManager().merge(client));
    }

    private ClientDao()
    {
    }

}
