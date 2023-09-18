/*

The purpose of this exercise is to implement a doubly linked list that stores strings (objects of type String). 
Specifically, each node of the doubly linked list,of type Node,stores :
    a string 𝑠𝑡𝑟, 
    the count of occurrences of 𝑠𝑡𝑟 in the input file (int count), 
    a reference to the next node in the list (Node next), 
    and a reference to the previous node in the list (Node previous).

NOTE:

This implementation was created for the Data Structures course at the University of Ioannina.

To execute the program write : java WordList < TomSawyerB.txt

*/

import java.io.*;

public class WordList {

    private class Node {
        String str;
        int count;      // occurrences of str in input file
        Node next;      // next node in doubly linked list
        Node previous;  // previsou node in doubly linked list

        Node(String str) {
            this.str = str;
            this.next = null;
            this.previous = null;
            this.count = 1;
        }
    }

    private Node first,tail;     // first node of the doubly linked list
    private int nodeCount;  // number of nodes

    public WordList() {
        this.nodeCount = 0;
    }

    int nodeCount() {
        return nodeCount;
    }

    // return the number of occurrences of word s in the input file
    public int contains(String s) {
        
        Node current=first;
        while(current!=null) 
        {    
            if(current.str.equals(s))
            {
                return current.count;
            }
            current=current.next;
        }
        return 0;
    }

    // add one more occurrence of word s; insert new node if s is not in the doubly linked list
    // the list should be adjusted so that the words appear in decreasing number of occurrences 
    public void insert(String s) {
 
        Node newNode = new Node(s);

        if(first == null)
        {
            first=tail=newNode;
            nodeCount++;
        }
        
        else
        {

            Node current=first;
            while(current!=null)
          {
            if(current.str.equals(s)==true)
            {
                current.count++;
////////////////Sorting////////////////////
                Node curr;
                Node index;
                int tempInt;
                String tempString;

                if(first==null)
                {
                    System.out.println("first is null");
                    return;
                }

            
                for(index=current;index.previous!=null;index=index.previous)
                {
                    if(current.count>index.count)
                    {
                        tempInt=current.count;
                        tempString=current.str;
                        current.count=index.count;
                        current.str=index.str;
                        index.count=tempInt;
                        index.str=tempString;
                    }
             
                }
                    
                    
///////////////////////////////////////////
                return;
            }
            else
            {
                current=current.next;
            }
           }
            
        tail.next=newNode;
        newNode.previous=tail;
        tail=newNode;
        nodeCount++;
     }
     
        
    }

    // delete word s from the doubly linked list
    public void delete(String s) {
   
        Node current=first;
        while(current.next!= null)
        {
            if(current.next.str.equals(s))
            {
                current.next=current.next.next;
                return;
            }
            else
            {
                current=current.next;
            }
        }
    }
 
    // sort doubly linked list in lexicographic order of words 
    public void lexOrder() {
    
        Node current,index=null;
        String tempString;
        int tempCount;
        if(first==null)
        {
            System.out.println("first is null");
            return;
        }
        else
        {
            for(current=first; current.next!=null; current=current.next)
            {
                for(index=current.next; index!=null; index=index.next)
                {
                    if(current.str.compareTo(index.str)>0)
                    {
                        tempString=current.str;
                        current.str=index.str;
                        index.str=tempString;
                        tempCount=current.count;
                        current.count=index.count;
                        index.count=tempCount;
                    }
                }
            }
        }
    }
    
    // find the k-th word in the doubly linked list
    public String select(int k) {
        
        Node current=first;
        int steps=0;
        while(current!=null && steps!=k-1)
        {
            steps++;
            current=current.next;
        }
        if(current==null) return null;
        else return current.str;
         // change appropriately
    }
    
    // print first k strings of the doubly linked list
    public void print(int k) {
        
        Node current=first;
        int steps=0;
        if(first==null) 
        {
            System.out.println("Doubly Linked List is Empty");

        }
        while(current!=null && steps<k)
        {
            System.out.println("     "+current.str+"("+current.count+")");
            steps++;
            current=current.next;
        }
  
    }
    

    public static void main(String[] args) {
        System.out.println("Test WordList");

        WordList L = new WordList();

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            if (s.isEmpty()) continue;
            L.insert(s);
        }
        long endTime = System.currentTimeMillis();
        long listTime = endTime - startTime;
        System.out.println("linked list construction time = " + listTime);
        System.out.println("number of linked list nodes = " + L.nodeCount());
        System.out.println("");

        System.out.println("contains 'and' " + L.contains("and") + " times");
        System.out.println("contains 'astonished' " + L.contains("astonished") + " times");
        System.out.println("contains 'boat' " + L.contains("boat") + " times");
        System.out.println("contains 'path' " + L.contains("path") + " times");
        System.out.println("contains 'the' " + L.contains("the") + " times");
        System.out.println("contains 'train' " + L.contains("train") + " times");
        System.out.println("contains 'tom' " + L.contains("tom") + " times");
        System.out.println("contains 'wondered' " + L.contains("wondered") + " times");
        System.out.println("");

        System.out.println("\nthe 10 most frequent words are:");
        L.print(10);
        
        String s = L.select(9);
        System.out.println("deleting '" + s +"'");
        L.delete(s);
  
        s = L.select(8);
        System.out.println("deleting '" + s +"'");
        L.delete(s);
        
        s = L.select(7);
        System.out.println("deleting '" + s +"'");
        L.delete(s);
        
        System.out.println("\nthe remaining 10 most frequent words are:");
        L.print(10);
        
        System.out.println("\nsorting words in lexicographical order");
        L.lexOrder();
        System.out.println("first 10 words in lexicographical order are:");
        L.print(10);
        
        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("\ntotal running time = " + totalTime);
    }
}