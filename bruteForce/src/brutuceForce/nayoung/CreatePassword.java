package brutuceForce.nayoung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author hny48
 * boj 1759 ������ ��ȣ ��θ� ���ϴ� ���� <pre>
 * �Է�
 * 1) L: ������ ��ȣ�� ���� 
 * 2) C: �־��� ���ĺ� �ҹ��� ����(���� �ٸ�) -> ����
 * 3) 3<= L <= C <= 15
 * ����
 * 1) ����: �ּ� �� ��, ����: �ּ� �� ��
 * 2) ������ ����(���� ����)
 * �־��� ���
 * nPr= n*...*(n-r+1) -> 15 * ... *(15 - 15 + 1) = 15!
 */
public class CreatePassword {
	
	public static String[] password;
	public static StringBuilder tempBuilder1 = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int r = sc.nextInt();
		int n = sc.nextInt();

		password = new String[n];
		ArrayList<String> tempList = new ArrayList<String>();

		for (int i = 0; i < n; i++) {
			tempList.add(sc.next());
		}

		Collections.sort(tempList);
		int i = 0;
		for (String next : tempList) {
			password[i] = next;
			i++;
		}

		LinkedList<String> list = new LinkedList<String>();
		int[] check = new int[n];
		permutation(n, r, list, check);
	}

	public static void permutation(int n, int r, LinkedList<String> list, int[] check) {
		if (list.size() >= 2) {
			// �ڿ� �ִ� ���ڰ� �տ� �ִ� ���ں��� ���� ��
			for (int a = 1; a < list.size(); a++) {
				if (list.get(a).charAt(0) < list.get(a - 1).charAt(0)) {
					return;
				}
			}
		}

		if (list.size() == r) {
			tempBuilder1.setLength(0);

			int vowelCount = 0;	// ����
			int consonantCount = 0;	// ����
			
			for (String next : list) {
				tempBuilder1.append(next);
			}
			
			// ���� ������ ���� ������ ���ϴ� �κ�
			for (int i = 0; i < tempBuilder1.length(); i++) {
				char next = tempBuilder1.charAt(i);
				if (next == 'a' || next == 'e' || next == 'i' || next == 'o' || next == 'u') {
					vowelCount++;
				} else {
					consonantCount++;
				}
			}
			
			// ������ 1���� �̻��̰� ������ 2���� �̻��̸�
			if (vowelCount >= 1 && consonantCount >= 2) {
				System.out.println(tempBuilder1.substring(0));
			}
			return;
		}

		for (int i = 0; i < n; i++) {
			if (check[i] == 0) {
				list.add(password[i]);
				check[i] = 1;
				permutation(n, r, list, check);
				list.removeLast();
				check[i] = 0;
			}
		}
	}
}
