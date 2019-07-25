package concurs.repository;

import concurs.model.Participant;

public interface IParticipantRepository extends JRepository<Participant ,Integer> {
    Iterable<Participant> findParticipantiByProba(Integer idProba);
}
