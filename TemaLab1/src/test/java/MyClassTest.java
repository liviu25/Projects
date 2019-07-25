import org.junit.Before;

import static org.junit.Assert.*;

public class MyClassTest {


    @org.junit.Test
    public void getAtr1() {
        MyClass myClass=new MyClass("asdf");
        assertTrue(myClass.getAtr1().equals("asdf"));
    }

    @org.junit.Test
    public void setAtr1() {
        MyClass myClass=new MyClass("asdf");
        myClass.setAtr1("qwer");
        assertTrue(myClass.getAtr1().equals("qwer"));
    }
}