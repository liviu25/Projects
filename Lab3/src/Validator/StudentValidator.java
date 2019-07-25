package Validator;

import Domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     *
     * @param student Student
     * @return mesaj cu erorile daca studentul nu e valid,
     *          sau strigul vid daca studentul e valid
     */
    @Override
    public void validate(Student student) throws ValidationException{
        String msg=new String();
        System.out.println(msg);
        if(student.getID()==null || student.getID().equals(" "))
        {
            msg+="Id invalid";
        }
        if(!msg.isBlank())
            throw new ValidationException(msg);

    }
}
