include "model.thrift"
namespace java com.service
namespace csharp com.service

exception ConcursException{
    1: string message
}
service ConcursService{
    void login(1: model.UserDTO user,2: model.ClientServerDTO client) throws (1: ConcursException ex),
    void logout(1: model.UserDTO user,2: model.ClientServerDTO client) throws (1: ConcursException ex),
    list<model.ProbaDTO> getProbeAndNrParticipanti() throws (1: ConcursException ex),
    list<model.ParticipantDTO> getParticipantiByProba(1: i32 idProba) throws (1: ConcursException ex),
    void inscriereParticipant(1: model.ParticipantDTO participant,2: string tipProba1,3: string tipProba2) throws (1: ConcursException ex),
}