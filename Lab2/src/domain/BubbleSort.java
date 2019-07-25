package domain;

public class BubbleSort extends AbstractSorter{
    public BubbleSort(int[] v) {
        super(v);
    }

    @Override
    public void sort() {
        boolean ok=true;
        int aux;
        do{
            ok=false;
            for(int i=0; i<v.length-1; i++)
            {
                if(v[i]>v[i+1])
                {
                    aux=v[i];
                    v[i]=v[i+1];
                    v[i+1]=aux;
                    ok=true;
                }
            }
        }while (ok);
    }
}
