
public class ValidatorMessage implements Validator<MessageTask> {
    @Override
    public String validate(MessageTask m) {
        if(m.getID()==null || m.getID().equals(" ")){
            return "Id invalid";
        }
        return " ";
    }
}
