// https://leetcode.com/problems/median-of-two-sorted-arrays/
package Leetcode.MedianOfTwoSortedaArrays;

//Vaggelis Chasanis
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
       

        int[] Ar=new int[nums1.length+nums2.length];
        mergeArrays(nums1, nums2,nums1.length,nums2.length,Ar);
        
        if(Ar.length%2==0)
        {
            int res=(select(Ar,0,Ar.length-1,Ar.length/2)+select(Ar,0,Ar.length-1,(Ar.length/2)+1));
            System.out.println(res/2.0);
            return res/2.0;
        }
        else {
            System.out.println(select(Ar,0,Ar.length-1,(Ar.length/2)+1));
            return select(Ar,0,Ar.length-1,(Ar.length/2)+1);
            }
        
    }
    
    public static int partition(int[] A,int l,int r) {
        
        int x=A[r];
        
        int i=l-1;
        //swap
        for(int j=l;j<r;j++) 
        {
            if(A[j]<=x) 
            {
                i++; 
                int temp=A[j];
                A[j]=A[i];
                A[i]=temp;
            }
        }
        //
        int temp=A[i+1];
        A[i+1]=A[r];
        A[r]=temp;
        return i+1;

    }
    
    public static int select(int[] A, int l, int r, int k) {
        
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
    }

    public static void mergeArrays(int[] arr1, int[] arr2, int nu1,
                                int nu2, int[] arr3)
    {
        int i = 0, j = 0, k = 0;
       
        while (i<nu1 && j <nu2)
        {
            if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
     
        while (i < nu1)
            arr3[k++] = arr1[i++];
     
        while (j < nu2)
            arr3[k++] = arr2[j++];
    }

   
}