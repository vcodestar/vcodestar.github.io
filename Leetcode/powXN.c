//https://leetcode.com/problems/powx-n/description/

double myPow(double x,int n)
{
    int i = 0;
    double res = 1;

    long longN = (long) n ; // to avoid overflow if  n == -2147483648

   if (longN == 0)
   {
       return 1;
   }

   if(longN < 0)
   {
       x = 1 / x;
       longN = -longN;
   }

   while(longN > 0)
   {
       if(longN % 2 == 1)
       {
           res *= x;
       }

       x *= x;
       longN /= 2;
   }

   return res;
}

