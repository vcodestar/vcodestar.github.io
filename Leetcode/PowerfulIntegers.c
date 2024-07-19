// https://leetcode.com/problems/powerful-integers/description/

// used in built-in qsort() function
int compare(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

// checks if the number to be added, is already inside the array
int checkDup(int num, int * array, int size)
{
    for(int k = 0; k < size; k++)
    {
        if(num == array[k])
        {
            return 1;
        }
    }

    return 0;
}

int* powerfulIntegers(int x, int y, int bound, int* returnSize) {
    long long max_i = (x == 1) ? 0 : (long long)(log(bound) / log(x)); 
    long long max_j = (y == 1) ? 0 : (long long)(log(bound) / log(y));


    int* powerful_nums = (int*)malloc((bound + 1) * sizeof(int)); // malloc size needed
    int count = 0; // count how many elements are inside the array

    for (int i = 0; i <= max_i; i++) {
        for (int j = 0; j <= max_j; j++) {
            int num = (int)pow(x, i) + (int)pow(y, j);
            if (num <= bound) {


                if(checkDup(num, powerful_nums, count) == 0) // if num is not already inside the array then place it
                {
                    powerful_nums[count] = num;
                    count++; // increment number of elements inside the arrat
                }

            }
        }
    }

    *returnSize = count; // set size of array so caller can access it 
    
    qsort(powerful_nums, count, sizeof(int), compare); // use built-in quicksort to put numbers in order

    return powerful_nums;
}

