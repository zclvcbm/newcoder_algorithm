package com.zcsmart.problem_20170720;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Admin on 2016/7/21.
 */
public class RussianDollEnvelopers {

    public static class Dot{
        public int w;
        public int h;
        public Dot(int w, int h){
            this.w = w;
            this.h = h;
        }
    }

    public static class DotComparator implements Comparator<Dot>{
        @Override
        public int compare(Dot dot1, Dot dot2) {
            if(dot1.w<dot2.w){
                return -1;
            }else if(dot1.w==dot2.w){
                if(dot1.h>dot2.h)
                    return -1;
                else if(dot1.h==dot2.h)
                    return 0;
                else
                    return 1;
            }else{
                return 1;
            }
        }
    }

    public static int maxEnvelopes(int[][] ens){
        if(ens==null || ens.length==0 || ens[0]==null || ens[0].length != 2)
            return 0;
        int n = ens.length;
        Dot[] dots = new Dot[n];
        for(int i=0;i<dots.length;i++){
            dots[i] = new Dot(ens[i][0],ens[i][1]);
        }
        int[] dp = new int[n];
        Dot[] ends = new Dot[n];
        dp[0] = 1;
        ends[0] = dots[0];
        int l=0;
        int r=0;
        int right = 0;
        Arrays.sort(dots,new DotComparator());
        for(int i=1;i<n;i++){
            r = right;
            while(l<=r){
                int mid = (r+l)/2;
                if(dots[i].h>ends[mid].h)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            right = Math.max(right,l);
            ends[l] = dots[i];
        }
        return right+1;
    }

    public static void main(String[] args){
        int[][] test = { { 4, 3 }, { 1, 2 }, { 5, 7 }, { 5, 3 }, { 1, 1 }, { 4, 9 } };
        System.out.println(maxEnvelopes(test));
    }
}
