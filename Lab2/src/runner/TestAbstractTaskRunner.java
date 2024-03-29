package runner;

import domain.MessageTask;
import factory.Strategy;

public class TestAbstractTaskRunner {
    public static void main(String[] args) {
        MessageTask m1,m2;
        m1=new MessageTask("1","1","1","1","1");
        m2=new MessageTask("2","2","2","2","2");
        Strategy s= Strategy.valueOf("FIFO");
        StrategyTaskRunner sr=new StrategyTaskRunner(s);
        sr.addTask(m1);
        sr.addTask(m2);
        sr.executeAll();

        DelayTaskRunner dtr=new DelayTaskRunner(sr);
        dtr.addTask(m1);
        dtr.addTask(m2);
        dtr.executeAll();

        PrintTaskRunner ptr=new PrintTaskRunner(dtr);
        ptr.addTask(m1);
        ptr.addTask(m2);
        ptr.executeAll();
    }

}
