import java.net.*;
import java.io.*;

public class Main {

    public static void main(String args[]) throws Exception {
        Socket c = new Socket("127.0.0.1", 1234);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a,s;
        //System.out.print("a = ");
        //a = Integer.parseInt(reader.readLine());
        //System.out.print("b = ");
        //b = Integer.parseInt(reader.readLine());
        a=2;
        DataInputStream socketIn = new DataInputStream(c.getInputStream());
        DataOutputStream socketOut = new DataOutputStream(c.getOutputStream());
        //ObjectOutputStream objOut = new ObjectOutputStream(c.getOutputStream());
        int v[]=new int[20];
        v[0]=2;
        v[1]=3;
        socketOut.writeShort(a);
        //socketOut.writeShort(a);
        //objOut.writeShort(a);
        //objOut.writeObject(v);

        s = socketIn.readShort();
        System.out.println("s = " + s);

        reader.close();
        c.close();
    }

}