package daos;

import persistence.model.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import persistence.model.User;

import java.util.List;

public class ItemDAO extends AbstractDAO<Item> {

    private static ItemDAO instance;
    private static EntityManagerFactory emf;

    private ItemDAO(EntityManagerFactory _emf, Class<Item> entityClass) {
        super(_emf, entityClass);
    }

    public static ItemDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ItemDAO(emf, Item.class);
        }
        return instance;
    }

    public List<Item> getAllByEmail(String email) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Item> query = em.createQuery("SELECT h FROM Item h WHERE h.user.email =:email", Item.class);
            query.setParameter("email", email);
            return query.getResultList();
        }
    }

    public List<Item> getAll() {
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<Item> query = em.createQuery("SELECT h FROM Item h", Item.class);
            return query.getResultList();
        }
    }

    @Override
    public int delete(int id) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Item itemFound = em.find(Item.class, id);

            if (itemFound != null) {
                User user = itemFound.getUser();
                if (user != null) {
                    user.getItems().remove(itemFound);
                }
                itemFound.setUser(null);
                em.remove(itemFound);
                em.getTransaction().commit();
                return 1;
            }
            return 0;
        }
    }
}
