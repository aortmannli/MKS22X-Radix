public class radix{

  public static void radixsort(int[]data){
    MyLinkedList<Integer> ans;
    MyLinkedList<Integer>[] buckets = new MyLinkedList<Integer>[10];
    int max = 0;
    for(int i : data){
      ans.add(i);
      if(i > max) max = i;
    }
    
  }

  public int digit(int a, int b){
    return (a/(10*b)) % 10;
  }
}
