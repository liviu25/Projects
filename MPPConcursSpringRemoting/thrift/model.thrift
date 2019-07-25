namespace java model
namespace csharp model

struct UserDTO{
    1: string id,
    2: string password
}

struct ParticipantDTO{
    1: i32 id,
    2: string nume,
    3: string prenume,
    4: i32 varsta
}

enum TipProbaDTO{
    desen=1,
    cautare_comori=2,
    poezie=3
}

struct ProbaDTO{
    1: i32 id,
    2: TipProbaDTO tipProba,
    3: i32 varstaMin,
    4: i32 varstaMax,
    5: i32 nrParticipanti
}

struct InscriereDTO{
    1: ParticipantDTO participant,
    2: ProbaDTO proba
}

struct ClientServerDTO{
    1:string host,
    2:i32 port
}

