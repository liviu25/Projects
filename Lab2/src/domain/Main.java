package domain;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int v[]={7,6,5,2,9,3};
        SortingStrategy s=SortingStrategy.valueOf("BubbleSort");
        SortingTask st=new SortingTask("1","1",v,s);
        st.execute();
    }
}
