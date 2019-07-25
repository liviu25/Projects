package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int ok=1;
        for(int i=1; i<args.length; i=i+2)
        {
            if(!(args[i].equals("+") || args[i].equals("-") || args[i].equals("*") || args[i].equals("/")))
            {
                ok=0;
            }
        }
        if(ok==0)
            System.out.println("Expresie incorecta");
        else {
        int a=0,b=0,re=0,im=0,sumA,sumB,rez=0;
        double P=0;
        NumarComplex nr,n;
        nr=new NumarComplex(args[0]);
        sumA=nr.getReal();
        sumB=nr.getImaginar();

        for(int i=2; i<args.length; i=i+2)
        {
            re=a;
            im=b;
            //n=nr;
            //nr=new NumarComplex(args[i]);
            a=nr.getReal();
            b=nr.getImaginar();

            if(args[i-1].equals("+"))
            {
                nr.adunare(new NumarComplex(args[i]));

            }
            if(args[i-1].equals("-"))
            {
                nr.scadere(new NumarComplex(args[i]));
            }

            if(args[i-1].equals("*"))
            {
                sumA=sumA-re+re*a-im*b;
                sumB=sumB-im+re*b+im*a;
            }

            P=P+Math.sqrt((a-re)*(a-re)+(b-im)*(b-im));

        }

            if (sumB < 0)
                System.out.println("rezultat: " + nr.getReal() + "-" + nr.getImaginar() + "i");
            else
                System.out.println("rezultat: " + nr.getReal() + "+" + nr.getReal() + "i");

            System.out.println("perimetru: " + P);
        }
        }
}
