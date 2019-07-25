class AA<E,T>{
    private E e;
    private T t;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

public class Test{


    public static void main(String []args){
        AA bb =new AA<Integer,String>();
        bb.setE("adsdf");
        //bb.setE(123);
        System.out.println(bb.getE()+" "+bb.getT());
    }
}
