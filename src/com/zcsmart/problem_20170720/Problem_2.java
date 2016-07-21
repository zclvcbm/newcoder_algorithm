package com.zcsmart.problem_20170720;

/**
 * Created by Admin on 2016/7/20.
 */
/*
2.给定一个非负数的数组，代表一个容器。例如数组[0,1,0,2,1,0,1,3,2,1,2,1]，
就是以下图形中黑色的部分。如果用这个容器接水的话，请问可以接多少水？
还以这个数组为例，可以接6格水，就是以下图形中蓝色的部分。
要求：实现时间复杂度O(N)，额外空间复杂度O(1)的解法
 */
public class Problem_2 {

    public static void main(String[] args){
        Problem_2 p = new Problem_2();
//        int arr[] = {0,1,0,2,1,0,1,3,2,1,2,1};
//        p.area4(arr);
        p.area1(generateRandomArray());
        p.area2(generateRandomArray());
        p.area3(generateRandomArray());
        p.area4(generateRandomArray());
//        int[] array = {5,2,3};
//        p.area1(array);
    }

    public static int[] generateRandomArray() {
        int[] array = new int[(int)(Math.random()*8)+2];
        for(int i=0;i<array.length;i++){
            array[i] = (int)(Math.random()*8)+2;
        }
        return array;
    }

    // 时间复杂度为O(n2)
    public int area1(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int water = 0;
        int n = arr.length;
        int lmax = arr[0];
        int rmax = arr[n-1];
        for(int i=1;i<n-1;i++){
            for(int j=1;j<i;j++){
                if(arr[j]>lmax)
                    lmax = arr[j];
            }
            for(int k=i+1;k<n-1;k++){
                if(arr[k]>rmax)
                    rmax = arr[k];
            }
            if(lmax>arr[i] && rmax>arr[i]) {
                water += lmax > rmax ? (rmax - arr[i]) : (lmax - arr[i]);
                //System.out.println(lmax > rmax ? (rmax - arr[i]) : (lmax - arr[i]));
            }
        }
        System.out.println(water);
        return water;
    }

    //时间复杂度为O(N) 空间复杂度为O(N)
    public int area2(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int water = 0;
        int n = arr.length;
        int[] lmaxs = new int[n];
        int[] rmaxs = new int[n];
        lmaxs[0]=arr[0];
        rmaxs[n-1] = arr[n-1];
        for(int i=1;i<n;i++){
            if(arr[i]>lmaxs[i-1])
                lmaxs[i]=arr[i];
            else
                lmaxs[i]=lmaxs[i-1];
        }

        for(int j=n-2;j>=0;j--){
            if(arr[j]>rmaxs[j+1])
                rmaxs[j]=arr[j];
            else
                rmaxs[j]=rmaxs[j+1];
        }

        for(int k=1;k<n-1;k++){
            if(lmaxs[k]>arr[k] && rmaxs[k]>arr[k])
                water += lmaxs[k]<rmaxs[k]?(lmaxs[k]-arr[k]):(rmaxs[k]-arr[k]);
        }
        System.out.println(water);
        return water;
    }

    //时间复杂度为O(N) 空间复杂度为O(N)
    public int area3(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int n = arr.length;
        int water = 0;
        int rmaxs[] = new int[n];
        rmaxs[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--){
            if(arr[i]>rmaxs[i+1])
                rmaxs[i] = arr[i];
            else
                rmaxs[i] = rmaxs[i+1];
        }
        int lmax = arr[0];
        for(int j=1;j<n;j++){
            if(lmax<arr[j]) {
                lmax = arr[j];
            }
            if(rmaxs[j]>arr[j])
                water += lmax<rmaxs[j]?(lmax-arr[j]):(rmaxs[j]-arr[j]);
        }
        System.out.println(water);
        return water;
    }

    // 时间复杂度O(N) 空间复杂度O(1)
    public int area4(int[] arr){
        if(arr==null || arr.length==0)
            return 0;
        int left = 1;
        int right = arr.length-2;
        int water = 0;
        int lmax = arr[0];
        int rmax = arr[arr.length-1];
        while(left<=right){
            int value=0;
            if(lmax<=rmax){
                value = Math.max(0,lmax-arr[left]);
                lmax = Math.max(lmax,arr[left]);
                water += value;
                left++;
            }else{
                value = Math.max(0,rmax-arr[right]);
                rmax = Math.max(rmax,arr[right]);
                water += value;
                right--;
            }
        }
        System.out.println(water);
        return  water;
    }
}
