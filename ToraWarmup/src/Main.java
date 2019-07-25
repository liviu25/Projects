import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src\\one_transaction.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\one_transaction.out"));

        int nrTests= Integer.parseInt(reader.readLine());
        for(int test=1; test<=nrTests; test++)
        {
            Integer n= Integer.parseInt(reader.readLine());
            String[] x=reader.readLine().split(" ");
            int[] list=new int[n];
            for(int j=0; j<n; j++)
            {
                list[j]=Integer.valueOf(x[j]);
            }

            int diff[]=new int[n];

            for (int i=0; i < n-1; i++)

                diff[i] = list[i+1] - list[i];

            int max_diff = diff[0];

            for (int i=1; i<n-1; i++)

            {

                if (diff[i-1] > 0)

                    diff[i] += diff[i-1];

                if (max_diff < diff[i])

                    max_diff = diff[i];

            }

            if(max_diff<0)
                max_diff=0;

            String outputString="TEST #"+test+": "+max_diff+"\n";
            writer.append(outputString);

            System.out.println(outputString);

        }
        reader.close();
        writer.close();
    }
}
