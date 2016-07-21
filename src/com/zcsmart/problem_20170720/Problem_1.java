package com.zcsmart.problem_20170720;

/**
 * Created by Admin on 2016/7/21.
 */
/*
3.给定一个非负数的数组，数组中的每个值代表一个柱子的高度，
柱子的宽度是1。两个柱子之间可以围成一个面积，规定：
面积＝两根柱子的最小值＊两根柱子之间的距离。比如数组[3,4,2,5]。
3和4之间围成的面积为0，因为两个柱子是相邻的，中间没有距离。
3和2之间围成的面积为2，因为两个柱子的距离为1，且2是最短的柱子，
所以面积＝1*2。3和5之间围成的面积为6，因为两个柱子的距离为2，
且3是最短的柱子，所以面积＝3*2。求在一个数组中，哪两个柱子
围成的面积最大，并返回值。
要求：实现时间复杂度O(N)，额外空间复杂度O(1)的解法
 */
public class Problem_1 {

    public static void main(String[] args){
        Problem_1 p = new Problem_1();
        int[] array = {2,1,5,3,6,4,8,9,7};
        p.printArray(p.list1(array));
        p.printArray(p.list2(array));
        p.printArray(p.list1(null));
        p.printArray(p.list2(null));
    }

    public void printArray(int[] arr){
        if(arr==null)
            return;
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    // 时间复杂度O(N*N)，额外空间复杂度O(N)
    public int[] list1(int[] arr){
        if(arr==null || arr.length==0)
            return null;
        int[] dp = getdp1(arr);
        return generateLIS(arr,dp);
    }

    public int[] getdp1(int[] arr){
        int n = arr.length;
        int[] dp = new int[arr.length];
        for(int i=0;i<n;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(arr[j]<arr[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
        }
        return dp;
    }

    public int[] generateLIS(int[] arr, int[] dp){
        int len = 0;
        int index = 0;
        for(int i=0;i<dp.length;i++)
        {
            if(dp[i]>len)
            {
                len = dp[i];
                index = i;
            }
        }
        int[] list = new int[len];
        list[--len] = arr[index];
        for(int j=index;j>=0;j--){
            if(arr[j]<arr[index] && dp[index] == dp[j]+1)
            {
                list[--len] = arr[j];
                index = j;
            }
        }
        return list;
    }

    // 时间复杂度O(N*log(N))，额外空间复杂度O(N)
    public int[] list2(int arr[]){
        if(arr==null || arr.length==0)
            return null;
        int[] dp = getdp2(arr);
        return generateLIS(arr,dp);
    }

    public int[] getdp2(int arr[]){
        int n =arr.length;
        int[] dp = new int[n];
        int[] ends = new int[n];
        int l=0;
        int r=0;
        int right=0;
        dp[0] = 1;
        ends[0] = arr[0];
        for(int i=1;i<n;i++){
                l = 0;
                r = right;
            while(l<=r){
                int mid = (l+r)/2;
                if(arr[i]>ends[mid]){
                    l = mid+1;
                }
                else{
                    r = mid-1;
                }
            }
            right = Math.max(right,l);
            ends[l] = arr[i];
            dp[i] = l+1;
        }
        return dp;
    }
}
