package brutuceForce.nayoung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author hny48
 * boj 2309 <pre>
 * ����: ������ 7���� ������ ���� 100
 * ���: 9���� 7���� ������ ���� 100�� ���(���� ���� �ش�ȴ�)�� �ϳ���
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
    * @param combi ���̽��� �����ϴ� ������ �������� ��ȣ�� ��� ���� �迭
    * @param n ��ü ����
    * @param r �̰��� �ϴ� ����
    * @param index ���̽� �� �ϳ�
    * @param target ������ �������� �ε��� ��ȣ 
    * <pre>
    * {@code
    * �ε���		: 0  1   2   3   4.... 
    * �������� ������: 5, 10, 10, 15, 15, 20, 25, 30, 80
    * combi[0] ~ combi[6] �ε��� 8���� �Ҵ�Ǿ� ������ r�� 0�� �� ���� �˻縦 �ϹǷ� 6������ ���� �Ҵ�ȴ�.
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
            // heigthSum�� 100�� ������ 
            return;
         }
      } else if (target == n) {
         return;
      } else {
         combi[index] = target;
         // �ش� �����̷� ���� ���
         combination(combi, n, r - 1, index + 1, target + 1);
         // ���� ���� ���
         combination(combi, n, r, index, target + 1);
      }
   }

}