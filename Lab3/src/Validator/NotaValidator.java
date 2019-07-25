package Validator;

import Domain.Nota;
import Validator.Validator;

public class NotaValidator implements Validator<Nota> {
    /**
     *
     * @param nota Nota
     * @return mesaj cu erorile daca nu e nota valida,
     *          sau strigul vid daca nota e valida
     */
    @Override
    public void validate(Nota nota) throws ValidationException{
        String msg=new String();
        if(nota.getID().equals(" ") || nota.getIdStudent().equals(" ") || nota.getIdTema().equals(" "))
            msg+="id invalid";
        if(nota.getValoare()<1 && nota.getValoare()>10)
            msg+="nota trebuie sa fie intre 1 si 10";
        if(!msg.isBlank())
            throw new ValidationException(msg);
    }
}
