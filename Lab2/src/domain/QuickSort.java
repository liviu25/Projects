package domain;

public class QuickSort extends AbstractSorter {
    public QuickSort(int[] v) {
        super(v);
    }
    public int[] quickSort(int left,int right,int arr[]) {
        if(right <= left){
            return arr;
        }

        int mid = (left + right) / 2;
        int pivot = arr[mid];
        int i = left, j = right;

        while(i < j){
            while(arr[i] < pivot){
                i ++;
            }
            while(arr[j] > pivot){
                j --;
            }

            if(i < j){
                int aux=arr[i];
                arr[i]=arr[j];
                arr[j]=aux;
            }
        }

        quickSort(left, j - 1, arr);
        quickSort(j + 1, right, arr);

        return arr;
    }
    @Override
    public void sort() {
        v=this.quickSort(0,v.length-1,v);
    }
}
