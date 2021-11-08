package adeo.leroymerlin.cdp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.delete(id);
    }

    /**
     * Ici on filtre les events en fonction d'une query en entrée.
     * On itère sur les events, bands puis members pour touver une correspondance avec la query
     * Si on en trouve une alors on l'ajoute à la liste des events qui seront retournées.
     * Comme il peut y avoir plusieurs membres dans différents band de l'event qui match
     * il faut faire un distinct sur la liste des events.
     * - Ici pour soit passer par un optional et vérifier la présence de celui-ci pour l'ajout ou non
     * - Soit passer par un anyMatch qui retourne un boolean.
     * <p>
     * Une fois les events filtrés on rajoute le nombre d'enfant pour l'event (nbre de band)
     * puis de le nombre d'enfant pour le band (nbre de membre)
     *
     * @param query la query pour filtrer les membres
     * @return une liste d'events filtrés en fonctions d'un pattern.
     */
    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();
        List<Event> eventsFiltered = new ArrayList<>();
        events.forEach(event -> event.getBands().forEach(band -> {
//            Optional<Member> membre =
//                    band.getMembers().stream().filter(member -> member.getName().contains(query)).findFirst();
//            if (membre.isPresent()) {
//                eventsFiltered.add(event);
//            }

            boolean membrePresent = band.getMembers().stream().anyMatch(member -> member.getName().contains(query));
            if (membrePresent) {
                eventsFiltered.add(event);
            }
        }));

        return eventsFiltered.stream().distinct().map(this::changeTitleOfEvent).collect(Collectors.toList());
    }


    /**
     * Ici on appelle le dao pour y passer le commentaire et/ou le nombre d'étoile et l'id
     *
     * @param event Evenement à modifier
     * @param id    id de l'event à modifier
     */
    public void update(Event event, Long id) {
        eventRepository.update(event.getComment(), event.getNbStars(), id);
    }

    /**
     * Ici on crée un nouvel objet Event pour y modifier les titres.
     *
     * @param event Objet event à modifier
     * @return un Objet event modifié
     */
    private Event changeTitleOfEvent(Event event) {
        Event eventChange = new Event();
        eventChange.setId(event.getId());
        eventChange.setTitle(event.getTitle() + " [" + event.getBands().size() + "]");
        eventChange.setImgUrl(event.getImgUrl());
        eventChange.setNbStars(event.getNbStars());
        eventChange.setComment(event.getComment());

        Set<Band> bands = event.getBands().stream().map(this::changeTitleOfBand).collect(Collectors.toSet());
        eventChange.setBands(bands);
        return eventChange;
    }

    /**
     * Permet de créer un nouvel objet band avec le titre modifié pour éviter de
     * modifié la même référence qui modifierai 2 fois le même band.
     *
     * @param band Objet Band à modifier
     * @return un Band modifié
     */
    private Band changeTitleOfBand(Band band) {
        Band bandChange = new Band();
        bandChange.setMembers(band.getMembers());
        bandChange.setName(band.getName() + " [" + band.getMembers().size() + "]");
        return bandChange;
    }
}
