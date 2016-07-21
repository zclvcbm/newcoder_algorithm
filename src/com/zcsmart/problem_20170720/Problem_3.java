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
且3是最短的柱子，所以面积＝3*2。求在一个数组中，哪两个柱子围成
的面积最大，并返回值。
要求：实现时间复杂度O(N)，额外空间复杂度O(1)的解法
 */
public class Problem_3 {

    public static void main(String[] args){
        Problem_3 p = new Problem_3();
        int[] arr = {3,4,2,5,1};
        p.area(arr);
        p.area(generateRandomArray());
    }

    public static int[] generateRandomArray() {
        int[] array = new int[(int)(Math.random()*8)+2];
        for(int i=0;i<array.length;i++){
            array[i] = (int)(Math.random()*8)+2;
        }
        return array;
    }

    public int area(int[] arr) {
        if(arr==null || arr.length==0)
            return 0;
        int max = 0;
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            int value;
            if(arr[left]<=arr[right]){
                value = arr[left]*(right-left-1);
                if(value>max)
                    max = value;
                left++;
            }
            else{
                value = arr[right]*(right-left-1);
                if(value>max)
                    max = value;
                right--;
            }
        }
        System.out.println(max);
        return max;
    }
}
