package Repository;

import Model.HasID;
import Model.Participant;

public interface IParticipantRepository extends JRepository<Participant ,Integer> {
    Iterable<Participant> findParticipantiByProba(Integer idProba);
}
