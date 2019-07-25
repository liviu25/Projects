import java.io.*;



public class Main {



    static int findMinPoz(int list[],int start, int end)
    {
        int min=list[0];
        int pozMin=0;
        for(int i=start; i<end; i++)
        {
            if(list[i]<min)

            {
                min=list[i];
                pozMin=i;
            }
        }
        return pozMin;

    }

    static int findMaxPoz(int list[],int start, int end)
    {
        int max=-1;
        int pozMax=0;
        for(int j=start; j<end; j++)
        {
            if(list[j]>max)
            {
                max=list[j];
                pozMax=j;
            }
        }
        return pozMax;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\input_v1.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\input_v1.out"));

        int nrTests= Integer.parseInt(reader.readLine().split("=")[1]);
        for(int test=0; test<nrTests; test++)
        {
            reader.readLine();
            Integer s= Integer.valueOf(reader.readLine().split("=")[1]);
            Integer p= Integer.valueOf(reader.readLine().split("=")[1]);
            Integer d= Integer.valueOf(reader.readLine().split("=")[1]);
            Integer tp= Integer.valueOf(reader.readLine().split("=")[1].split("%")[0]);

            int list[]=new int[d];

            String[] ci=reader.readLine().split("=")[1].split(" ");
            for(int i=0; i<d; i++)
            {
                list[i]= Integer.parseInt(ci[i]);

            }

            int i=0;
            int rez[]=new int[d];

            if((tp/100)*p*list[i]>s) {
                rez[i] = (tp / 100) * p;
                p += (tp / 100) * p;
                s -= (tp / 100) * p * list[i];
            }
            while(i<d-1) {
                while (i < d - 1 && (list[i + 1] - list[i]) > 0) {
                    i++;
                }
                if((tp/100)*p*list[i]>s) {
                    rez[i] = -(tp / 100) * p;
                    p -= (tp / 100) * p;
                    s +=(tp/100)*p*list[i];
                }
                while(i < d-1 && (list[i + 1] - list[i]) < 0){
                    i++;
                }
                if((tp/100)*p*list[i]>s) {
                    rez[i] = (tp / 100) * p;
                    p += (tp / 100) * p;
                    s -= (tp/100)*p*list[i];
                }
            }
            String outputString="";
            for (i=0; i<d-1;i++) {
                outputString+=rez[i]+" ";
            }
            outputString+=rez[d-1];

            writer.append(outputString+"\n");



        }
        reader.close();
        writer.close();
    }
}
