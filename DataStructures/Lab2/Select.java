/*

NOTE : To execute the program write : java Select n seed
                                        where n is the number of elements 
                                        and seed is the seed number

 */

import java.util.Arrays;
import java.util.Random;

class Select {

    // fill array A of doubles with random numbers in [0,100] 
    public static void randomArray(double[] A, int seed) {
        Random rand = new Random(seed);
        for (int i = 0; i < A.length; i++) {
            A[i] = 100*rand.nextDouble();
        }
    }
    
    // fill A A of doubles with a worst-case input 
    /*public static void badArray(double[] A) {
         //i xeiroteri periptosi einai O(n^2)
        for (int i = 0; i < A.length; i++)   
        {  
            for (int j = i + 1; j < A.length; j++)   
            {  
                double temp = 0;  
                if (A[i] > A[j])   
                {  
                temp = A[i];  
                A[i] = A[j];  
                A[j] = temp;  
                }  
            }  
        }
        int length = A.length;
		for(int i=0;i<length/2;i++) {
		    double temp = A[i];
			A[i] = A[length-i-1];
			A[length-i-1] = temp;
		}
    }*/
   
    // copy the elements of array A in array B
    public static void copyArray(double[] A, double[] B) {
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
    }
    
    /* partition the array A[l,r] with respect to its last element p=A[r]
       if A[l,r] has j-1 elements < p, then p is placed at position pos = l + (j-1) of A 
          -all elements of A[l,pos-1] are less than p=A[pos], and
          -all elements of A[pos+1,r] are greater than p=A[pos]
       returns the position pos of p in array A */
     public static int partition(double[] A,int l,int r) {
        /* enter you code! */
        double x=A[r];
        // i is where the partition happens
        int i=l-1;
        //swap
        for(int j=l;j<r;j++) 
        {
            if(A[j]<=x) 
            {
                i++; 
                double temp=A[j];
                A[j]=A[i];
                A[i]=temp;
            }
        }
        //
        double temp=A[i+1];
        A[i+1]=A[r];
        A[r]=temp;
        return i+1;

    }
   public static double rselect(double[] A, int l, int r, int k) {

        if(l==r)
        {
            return A[l];
        }
        int pos=partition(A,l,r);
        //counting element from left to pos-1
        int count=pos-l+1;
        if(count==k)
        {
            return A[pos];
        }
        else if(count>k)
        {
            return rselect(A,l,pos-1,k);
        }
        else if(count<k)
        {
            return rselect(A,pos+1,r,k-count);
        }
        else return -1; // change appropriately
    }

    public static double select(double[] A, int l, int r, int k) {
        
        k=k-1;
        int j;
        while(r>l)
        {
            j=partition(A,l,r);
            if(j==k)
            {
                return A[k];
            }
            else if(j>k)
            {
                r=j-1;
            }
            else{l=j+1;}
        }
        return A[k];
 // change appropriately
    }
    
    public static void main(String[] args) {
        long startTime, endTime, totalTime;
        int n = Integer.parseInt(args[0]);
        int seed = Integer.parseInt(args[1]);
        int k = n/2;
        System.out.println("number of elements = " + n);
        
        double[] A     = new double[n];
        double[] Acopy = new double[n];

        randomArray(Acopy,seed);
        //badArray(Acopy);
        //System.out.println("Initial array Acopy:");
        //System.out.println(Arrays.toString(Acopy));
        
        copyArray(Acopy, A);
        startTime = System.currentTimeMillis();
        System.out.println("executing recursive select");
        System.out.println(k+"-th smallest element in array : " + rselect(A, 0, n - 1, k));
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("total time for recursive select = " + totalTime);
        
        copyArray(Acopy, A);
        startTime = System.currentTimeMillis();
        System.out.println("executing non-recursive select");
        System.out.println(k+"-th smallest element in array : " + select(A, 0, n - 1, k));
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("total time for non-recursive select = " + totalTime);
        
        copyArray(Acopy, A);
        startTime = System.currentTimeMillis();
        System.out.println("executing sorting");
        Arrays.sort(A);
        System.out.println(k+"-th smallest element in array : " + A[k-1]);
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("total time for sorting = " + totalTime);
    }
}