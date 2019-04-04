import java.util.*;
@SuppressWarnings({"unchecked", "rawtypes"})

public class Radix{

  public static void main(String[] args) {
      System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
      int[]MAX_LIST = {1000000000,500,10};
      for(int MAX : MAX_LIST){
        for(int size = 31250; size < 2000001; size*=2){
          long qtime=0;
          long btime=0;
          //average of 5 sorts.
          for(int trial = 0 ; trial <=5; trial++){
            int []data1 = new int[size];
            int []data2 = new int[size];
            for(int i = 0; i < data1.length; i++){
              data1[i] = (int)(Math.random()*MAX);
              data2[i] = data1[i];
            }
            long t1,t2;
            t1 = System.currentTimeMillis();
            Radix.radixsort(data2);
            t2 = System.currentTimeMillis();
            qtime += t2 - t1;
            t1 = System.currentTimeMillis();
            Arrays.sort(data1);
            t2 = System.currentTimeMillis();
            btime+= t2 - t1;
            if(!Arrays.equals(data1,data2)){
              System.out.println("FAIL TO SORT!");
              System.exit(0);
            }
          }
          System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
        }
        System.out.println();
      }
    }

    public static void radixsort(int[] data){
      int max = 0;
      for (int i : data) {
        if (i > max) {
          max = i;
        }
      }
      MyLinkedList[] buckets = new MyLinkedList[20];
      MyLinkedList<Integer> hold = new MyLinkedList<Integer>();
      for (int i = 0; i < buckets.length; i++) {
        buckets[i] = new MyLinkedList<Integer>();
      }
      for (int i = 1; i <= (""+max).length(); i++) {
        if (i == 1) {
          for (int x = 0; x < data.length; x++) {
            buckets[digit(data[x],i)+9].add(data[x]);
          }
          hold.clear();
          for (int y = 0; y < 20; y++) {
            hold.extend(buckets[y]);
          }
          for (MyLinkedList<Integer> c : buckets) {
            c.clear();
          }
        }else{
          for (int x = 0; x < data.length; x++) {
            int num = hold.removeFront();
            buckets[digit(num, i)+9].add(num);
          }

          hold.clear();
          for (int y = 0; y < 20; y++) {
            hold.extend(buckets[y]);
          }
          for (MyLinkedList<Integer> c : buckets) {
            c.clear();
          }
        }
      }
      for (int i = 0; i < data.length; i++) {
        data[i] = hold.removeFront();
      }
     }

  public static int digit(int a, int b){
    return (int)(a / (Math.pow(10,b-1))) % 10;
  }
}
