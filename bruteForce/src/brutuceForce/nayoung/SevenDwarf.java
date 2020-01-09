package brutuceForce.nayoung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author hny48
 * boj 2309 <pre>
 * 조건: 난쟁이 7명의 몸무게 합이 100
 * 출력: 9명중 7명의 몸무게 합이 100인 경우(여러 개가 해당된다)중 하나만
 */
public class SevenDwarf {

   private static int[] height = new int[9];
   private static int count = 0;

   public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);

      int[] combi = new int[9];
      for (int i = 0; i < height.length; i++) {
         height[i] = sc.nextInt();
      }
      sc.close();

      Arrays.sort(height);
      combination(combi, 9, 7, 0, 0);
   }

   /**
    * 
    * @param combi 케이스에 만족하는 난쟁이 몸무게의 번호를 담기 위한 배열
    * @param n 전체 개수
    * @param r 뽑고자 하는 개수
    * @param index 케이스 중 하나
    * @param target 난쟁이 몸무개의 인덱스 번호 
    * <pre>
    * {@code
    * 인덱스		: 0  1   2   3   4.... 
    * 난쟁이의 몸무게: 5, 10, 10, 15, 15, 20, 25, 30, 80
    * combi[0] ~ combi[6] 인덱스 8까지 할당되어 있지만 r이 0일 때 조건 검사를 하므로 6까지만 값이 할당된다.
    * combi -> { 0, 1, 2, 3, 4, 5, 6, 7 }
    * heightSum = 5 + 10 + 10 + 15 + 15 + 20 + 25}
    */
   public static void combination(int[] combi, int n, int r, int index, int target) {
      if (count != 0) {
         return;
      }
      if (r == 0) {
         int heightSum = 0;
         
         for (int i = 0; i < index; i++) {
            heightSum += height[combi[i]];
         }
         if (heightSum == 100) {
            ArrayList<Integer> dwarfHeightList = new ArrayList<Integer>();
            for (int i = 0; i < index; i++) {
               dwarfHeightList.add(height[combi[i]]);
            }
            Collections.sort(dwarfHeightList);
            for (int dwarfHeight : dwarfHeightList) {
               System.out.println(dwarfHeight);
            }
            count++;
         } else {
            // heigthSum이 100을 넘으면 
            return;
         }
      } else if (target == n) {
         return;
      } else {
         combi[index] = target;
         // 해당 난쟁이로 뽑은 경우
         combination(combi, n, r - 1, index + 1, target + 1);
         // 뽑지 않은 경우
         combination(combi, n, r, index, target + 1);
      }
   }

}