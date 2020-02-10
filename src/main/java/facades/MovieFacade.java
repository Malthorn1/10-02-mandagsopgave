package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    
    public Movie addMovie (Movie m) { 
        EntityManager em = emf.createEntityManager(); 
        try { 
            em.getTransaction(); 
            em.persist(m);
            em.getTransaction().commit();
            return m; 
        }
        finally {
            em.close();
        } 
    }
    
    public List<Movie> getAllMovies () {
        EntityManager em = emf.createEntityManager(); 
        try {
            TypedQuery<Movie> query = em.createQuery("Select m from Movie m", Movie.class); 
            return query.getResultList(); 
        }
        finally {
            em.close();
        }
    }
    
}