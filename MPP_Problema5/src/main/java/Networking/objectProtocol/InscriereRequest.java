package Networking.objectProtocol;

import Networking.dto.InscriereDTO;

public class InscriereRequest implements Request {
    private InscriereDTO inscriereDTO;

    public InscriereRequest(InscriereDTO inscriereDTO) {
        this.inscriereDTO = inscriereDTO;
    }

    public InscriereDTO getInscriereDTO() {
        return inscriereDTO;
    }
}
