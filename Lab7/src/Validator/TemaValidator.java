package Validator;

import Domain.Tema;

public class TemaValidator implements Validator<Tema> {
    /**
     *
     * @param tema Tema
     * @return mesaj cu erorile daca nu e tema valida,
     *          sau strigul vid daca tema e valida
     */
    @Override
    public void validate(Tema tema) throws ValidationException{
        String msg=new String();
        if(tema.getId().equals(" "))
            msg+="Id invalid";
        if(tema.getDeadline()<0 || tema.getDeadline()>14 || tema.getNrSaptamanaPrimire()<0 || tema.getNrSaptamanaPrimire()>14)
            msg+="Saptamana incorecta";
        if(!msg.isEmpty())
            throw new ValidationException(msg);
    }
}
