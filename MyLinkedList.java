import java.util.*;

public class MyLinkedList<E>{
  private int length;
  private Node start,end;

  public MyLinkedList(){}

  public int size(){
    return length;
  }

  public String toString(){
    String ans = "[";
    // String ans equals open brackets
    Node temp = start;
    // temp is equal to start
    while (temp != null){
      // while temp is not null
      ans += temp;
      // toString of temp is added to ans
      temp = temp.next();
      // temp is equal to temp's next
      if (temp != null) ans += ", ";
      // if temp's next is not null, ", " is added to ans
    }
    ans += "]";
    // ending bracket is added to ans
    return ans;
  }

  public void clear(){
    length = 0;
    start = null;
    end = null;
  }

  public boolean add(E value){
    if (start == null){
      // if there is no start Node
      start = new Node(value, null, null);
      // start gets new Node with data equal to value
      end = start;
      // end also equals start
    }
    else if (end == start){
      // else if there is no end Node
      end = new Node(value, null, start);
      // end gets new Node with data value and prev start
      start.setNext(end);
      // start's next is set to end
    }
    else{
      // else if there is a start and end Node
      Node temp = new Node(value, null, end);
      // a temp Node is initialized with data value and prev end
      end.setNext(temp);
      // end's next is set to temp
      end = temp;
      // end is assigned to temp
    }
    length++;
    // increase length by one
    return true;
  }

  public void add(int index, E value){
    if (index < 0 || index > size()) throw new IndexOutOfBoundsException("Cannot add, index " + index + " is out of bounds");
    // if index is less than zero or is greater than size, throw IndexOutOfBoundsException
    if (index == size()){
      // if index is equal to the size
      add(value);
      // use the boolean add(value) method
    }
    else if (index == 0){
      // if index is zero
      Node temp = new Node(value, start, null);
      // temp variable is assigned to a new Node with data value and next start
      start.setPrev(temp);
      // start's prev is set to temp
      start = temp;
      // temp becomes start
      length++;
      // length is increased by one
    }
    else{
      // if index is between 0 and size
      Node temp = new Node(value, getNthNode(index), getNthNode(index - 1));
      // temp variable is assigned to a new Node with data value, next the Node at index, prev the Node at index - 1
      getNthNode(index).setPrev(temp);
      // the prev of the Node at index is set to temp
      getNthNode(index - 1).setNext(temp);
      // the next of the Node at index - 1 is set to temp
      length++;
      // length increases by one
    }
  }

  public void extend(MyLinkedList<E> other){
        //in O(1) runtime, move the elements from other onto the end of this
        //The size of other is reduced to 0
        //The size of this is now the combined sizes of both original lists
        if (size() == 0){
          start = other.start;
          end = other.end;
          length += other.size();
          other.clear();
        }
        else if (other.size() > 0){
          // if the size of other is greater than 0
          length += other.size();
          // length increases by the size of other
          end.setNext(other.start);
          // end is set to the start Node of other
          other.start.setPrev(end);
          // the prev of other's start Node is set to end
          end = other.end;
          // end gets the end Node of other
          other.clear();
        }
    }

  private Node getNthNode(int index){
    int count = 0;
    // count starts at 0
    Node temp = start;
    // temp is equal to start
    while (temp != null){
      // while temp is not null
      if (count == index){
        // if the count equals the index
        return temp;
        // returns the Node temp
      }
      count++;
      // count increases by one
      temp = temp.next();
      // temp becomes the next Node after temp
    }
    return null;
    // return null if index is out of bounds
  }

  public E removeFront(){
    if (size() == 0) throw new NoSuchElementException();
    // if there are no elements in the MyLinkedList, throw NoSuchElementException
    E temp = start.getData();
    // return value (temp) is set to the value of start
    if (size() == 1){
      // if the length of the MyLinkedList is 1
      clear();
      // clear MyLinkedList
      return temp;
      // return temp value
    }
    start = start.next();
    // start is set to the next Node
    start.setPrev(null);
    // set the prev of the new start to null
    length--;
    // decrease the length by one
    return temp;
    // return the temp value
  }

  private class Node{
    private E data;
    private Node next,prev;

    public Node(E value, Node fwd, Node bk){
      data = value;
      next = fwd;
      prev = bk;
    }

    public E getData(){
      return data;
    }

    public E setData(E i){
      E temp = data;
      data = i;
      return temp;
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
}
