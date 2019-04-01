import java.util.*;

public class MyLinkedList<E>{

  private class Node{
    private E data;
    private Node next,prev;

    public Node(E data, Node prev, Node next){
     this.data = data;
     this.prev = prev;
     this.next = next;
   }

    public E getData(){
      return data;
    }

    public E setData(E i){
      E hold = data;
      data = i;
      return hold;
    }

    public Node prev(){
      return prev;
    }

    public void setPrev(Node other){
      prev = other;
    }

    public Node next(){
      return next;
    }

    public void setNext(Node other){
      next = other;
    }

    public String toString(){
      return "" + data;
    }
  }

  private int len;
  private Node start,end;

  public MyLinkedList(){}

  public int size(){
    return len;
  }

  public String toString(){
    String ans = "[";
    Node hold = start;
    while (hold != null){
      ans += hold;
      hold = hold.next();
      if (hold != null) ans += ", ";
    }
    ans += "]";
    return ans;
  }

  public void clear(){
    len = 0;
    start = null;
    end = null;
  }

  public boolean add(E value){
    if (start == null){
      start = new Node(value, null, null);
      end = start;
    }
    else if (end == start){
      end = new Node(value, null, start);
      start.setNext(end);
      }
    else{
      Node hold = new Node(value, null, end);
      end.setNext(hold);
      end = hold;
    }
    len++;
    return true;
  }

  public void add(int idx, E value){
    if (idx < 0 || idx > size()) throw new IndexOutOfBoundsException("Cannot add, idx " + idx + " is out of bounds");
    if (idx == size()) add(value);
    else if (idx == 0){
      Node hold = new Node(value, start, null);
      start.setPrev(hold);
      start = hold;
      len++;
    }
    else{
      Node hold = new Node(value, getNthNode(idx), getNthNode(idx - 1));
      getNthNode(idx).setPrev(hold);
      getNthNode(idx - 1).setNext(hold);
      len++;
    }
  }

  public E removeFront(){
    if (size() == 0) throw new NoSuchElementException();
    E hold = start.getData();
    if (size() == 1){
      clear();
      return hold;
    }
    start = start.next();
    start.setPrev(null);
    len--;
    return hold;
  }

  public void extend(MyLinkedList<E> other){
        if (other.size() > 0){
          len += other.size();
          end.setNext(other.start);
          other.start.setPrev(end);
          end = other.end;
          other.len = 0;
          other.start = null;
          other.end = null;
        }
    }

  private Node getNthNode(int idx){
    int count = 0;
    Node hold = start;
    while (hold != null){
      if (count == idx){
        return hold;
      }
      count++;
      hold = hold.next();
    }
    return null;
  }
}
