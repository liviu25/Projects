package concurs.repository;

import concurs.model.Proba;

public interface IProbaRepository extends JRepository<Proba,Integer>{
    Iterable<Proba> findProbeAndNrParticipanti();
}
