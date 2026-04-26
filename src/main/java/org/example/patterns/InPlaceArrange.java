package org.example.patterns;

public class InPlaceArrange {

    //rotateby k array : O(n) time, O(1) space
    //nextPermutation : O(n) time, O(1) space
    //sort012 (Dutch Flag) : O(n) time, O(1) space

    /**
     * In-place / Rearrangement Pattern
     */

    /**
     * Next permutation
     * Input: arr[] = [2, 4, 1, 7, 5, 0]
     * Output: [2, 4, 5, 0, 1, 7]
     * @param arr
     */
    void nextPermutation(int arr[]){
        // code here
        int N = arr.length;
        int last= N-2;
        for(int i=N-1; i>0; i--){
            //find the first lowest index ele from right
            if(arr[i]<=arr[i-1])
                last--;//2->1
            else
                break;
        }
        if(last==-1){
            //means high ele is at 0th index and all are lower on in descedning order
            //so just reverese and return
            reverse(arr,0);
            return;
        }
        //find nextIndex to replace last(lowest element) with next greater element
        int nextIndex=-1;
        for(int i=N-1; i>0; i--){
            if(arr[i]>arr[last]){
                nextIndex = i;
                break;
            }
        }

        swap(arr,last,nextIndex);
        reverse(arr,last+1);
    }

    public static void swap (int arr[], int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void reverse(int arr[],int index){
        int n = arr.length - 1;
        while(index < n) {
            swap(arr,index,n);
            index++;
            n--;
        }
    }

    /**
     * Rotate array by K
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;

        k = k % n;   // important
        //n=5,k=7 , 7 % 5 = 2
        //So rotating 7 steps is same as rotating 2 steps.

        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }
    private void reverse(int[] nums, int left, int right){
        while(left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Dutch flag
     */

    public static void sort012(int a[], int n) {
        int lo=0,mid=0, h=n-1,temp=0;

        while(mid<=h){
            switch(a[mid]) {
                case 0 :
                    temp = a[lo];
                    a[lo]= a[mid];
                    a[mid]=temp;

                    mid++;
                    lo++;
                    break;

                case 1 :
                    mid++;
                    break;

                case 2:
                    temp = a[mid];
                    a[mid] = a[h];
                    a[h] = temp;
                    h--;
                    break;
            }
        }
    }
}
