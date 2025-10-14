import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class dict {
    public static  void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        int maxVal=0,maxCnt=0;
        for(int i=0;i<n;i++){
            int x=sc.nextInt();
            int cnt = map.getOrDefault(x, 0) + 1;
            map.put(x, cnt);
            if(cnt>maxCnt){
                maxCnt=cnt;
                maxVal=x;
            }
        }
        System.out.println("maxVal:" + maxVal);
        System.out.println("macCnt" + maxCnt);
    }
}
