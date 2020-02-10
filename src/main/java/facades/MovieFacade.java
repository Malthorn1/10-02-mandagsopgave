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
    
    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        MovieFacade facade = MovieFacade.getMovieFacade(emf); 
                
        Movie movie1 = new Movie ("Hobitten", 2020, "test"); 
        Movie movie2 = new Movie ("Titanic", 2000, "test2"); 
        
        facade.addMovie(movie1); 
        facade.addMovie(movie2); 

    }
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
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
