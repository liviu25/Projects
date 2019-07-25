package Repository;

import Model.Proba;
import javafx.util.Pair;

public interface IProbaRepository extends JRepository<Proba,Integer>{
    Iterable<Proba> findProbeAndNrParticipanti();
}
