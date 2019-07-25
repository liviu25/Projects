package domain;

public class TestMessageTask {
    public static void main(String[] args) {
        MessageTask[] tasks=new MessageTask[5];
        for(int i=0; i<5; i++)
        {
            tasks[i] =new MessageTask("1","1","1","1","1");
        }
        for(MessageTask t:tasks)
        {
            System.out.println(t);
        }
    }

}
