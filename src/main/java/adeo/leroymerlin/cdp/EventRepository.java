package adeo.leroymerlin.cdp;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * le read only true: la transaction reste tant qu'on ne rafraichit pas la page et quand elle
 * est rafraichit on revient à l'état primaire.
 */
@Transactional
public interface EventRepository extends Repository<Event, Long> {

    void delete(Long eventId);

    List<Event> findAllBy();

    /**
     * Ici on crée une méthode qui va modifier le commentaire et/ou le nombre d'étoiles dans le bon event
     *
     * @param comment le commentaire de l'event
     * @param nbStars Nombre de stars pour l'event
     * @param id      l'id de l'event
     */
    @Modifying
    @Query("UPDATE Event e SET e.comment = :comment, e.nbStars = :nbStars WHERE e.id = :id")
    void update(@Param("comment") String comment, @Param("nbStars") Integer nbStars, @Param("id") Long id);
}
