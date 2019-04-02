public class radix{

  public static void radixsort(int[]data){
    MyLinkedList<Integer> ans;
    MyLinkedList<Integer>[] buckets = new MyLinkedList<Integer>[10];
    int max = 0;
    int dig = 0;
    int[] hold = new int[data.length];
    for(int i : data){
      ans.add(i);
      if(i > max) max = i;
    }
    while(!(max < 10)){
      max /= 10;
      dig++;
    }
    for(int i = dig; i > 0; i--){
      for(int x : data){
        int a = digit(x,i);
        buckets[a].add(x);
      }
      for(int i = 0; i < buckets.length; i++){
        ans.extend(buckets[i]);
      }
      buckets.clear();
    }

  }

  public int digit(int a, int b){
    return (a/(10*b)) % 10;
  }
}
