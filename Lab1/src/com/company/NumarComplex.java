package com.company;

public class NumarComplex {
    private int a,b;
    public NumarComplex(String nr)
    {
        if(nr.contains("+"))
        {
            a=Integer.valueOf(nr.split("\\+")[0]);
            if(nr.split("\\+")[1].length() <2 )
            {
                b=1;
            }
            else {
                b = Integer.valueOf(nr.split("\\+")[1].split("i")[0]);
            }
        }
        if(nr.contains("-"))
        {
            a=Integer.valueOf(nr.split("\\-")[0]);
            if(nr.split("\\-")[1].length() <2 )
            {
                b=1;
            }
            else {
                b = Integer.valueOf(nr.split("\\-")[1].split("i")[0]);
            }
            b=-1*b;
        }
    }
    public int getReal(){
        return a;
    }
    public int getImaginar(){
        return b;
    }
    public void adunare(NumarComplex nr)
    {
        a=a+nr.getReal();
        b=b+nr.getImaginar();
    }
    public void scadere(NumarComplex nr)
    {
        a=a-nr.getReal();
        b=b-nr.getImaginar();
    }
}
