/*

We want to implement a method to find the 𝑘-th smallest number in an unsorted array 𝐴[0:𝑛−1]=[𝑎0 𝑎1 … 𝑎𝑛−1], 
without first sorting it. Let 𝑎𝑖 be the 𝑘-th smallest number in 𝐴. Furthermore, let 𝐴′=[𝑎0′ 𝑎1′ … 𝑎𝑛−1′] be the array obtained 
by sorting 𝐴 in ascending order, i.e., 𝑎0′≤𝑎1′≤⋯≤𝑎𝑛−1′. Then, 𝑎𝑖=𝑎𝑘−1′. For example, the 5th smallest number in the array 
𝐴=[12 2 43 15 50 14 88 75 7 20] is 15. In other words, we have 𝑎3=𝑎4′=15, since the corresponding sorted array is 
𝐴′=[2 7 12 14 𝟏𝟓 20 43 50 75 88].

NOTE:

This implementation was created for the Data Structures course at the University of Ioannina.

 */


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int[] Ar=new int[nums1.length+nums2.length];
        mergeArrays(nums1,nums2,nums1.length-1,nums2.length-1,Ar);
        
        if(Ar.length%2==0)
        {
            int nu1=select(Ar,0,Ar.length-1,Ar.length/2);
            int nu2=select(Ar,0,Ar.length-1,(Ar.length/2)+1);

            System.out.println("n1:"+nu1);
            System.out.println("n2:"+nu2);
            

            return (nu1+nu2)/2.0;
        }
        else return select(Ar,0,Ar.length-1,Ar.length/2);
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
    
    public static void mergeArrays(int[] arr1, int[] arr2, int n1,
                                int n2, int[] arr3)
    {
        int i = 0, j = 0, k = 0;
     
        // Traverse both arrayS
        while (i<n1 && j <n2)
        {
            /* 
             Check if the current element of the first
             array is smaller than the current element
             of the second array. If yes, store first
             array element and increment first array's
             index. Otherwise do the same with the second array
            */
            if (arr1[i] < arr2[j])
                arr3[k++] = arr1[i++];
            else
                arr3[k++] = arr2[j++];
        }
     
        // Store remaining elements of first array
        while (i < n1)
            arr3[k++] = arr1[i++];
     
        // Store remaining elements of second array
        while (j < n2)
            arr3[k++] = arr2[j++];
        }
        public static void main(String[] args) {
            int[] A={1,2};
            int[] B={3,4};
            Solution solution=new Solution();
            System.out.print(solution.findMedianSortedArrays(A,B));
        }
}


