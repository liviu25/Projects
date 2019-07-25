import java.util.ArrayList;
import java.util.List;

public class MessageTaskRepo extends AbstractJRepository<MessageTask,String> {
    public MessageTaskRepo(Validator<MessageTask> v) {
        super(v);
    }
    public List<MessageTask> filtrareMT(String s){
        List<MessageTask> list_m=new ArrayList<>();
        for(MessageTask m:findAll())
        {
            if(m.getDescriere().contain(s))
                list_m.add(m);
        }
        return list_m;
    }
}
