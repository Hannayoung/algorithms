package brutuceForce.nayoung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author hny48
 * boj 1759 가능한 암호 모두를 구하는 문제 <pre>
 * 입력
 * 1) L: 구성될 암호의 개수 
 * 2) C: 주어질 알파벳 소문자 개수(서로 다른) -> 순열
 * 3) 3<= L <= C <= 15
 * 조건
 * 1) 모음: 최소 한 개, 자음: 최소 두 개
 * 2) 사전식 구성(오름 차순)
 * 최악의 경우
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
			// 뒤에 있는 문자가 앞에 있는 문자보다 작을 때
			for (int a = 1; a < list.size(); a++) {
				if (list.get(a).charAt(0) < list.get(a - 1).charAt(0)) {
					return;
				}
			}
		}

		if (list.size() == r) {
			tempBuilder1.setLength(0);

			int vowelCount = 0;	// 모음
			int consonantCount = 0;	// 자음
			
			for (String next : list) {
				tempBuilder1.append(next);
			}
			
			// 모음 개수와 자음 개수를 구하는 부분
			for (int i = 0; i < tempBuilder1.length(); i++) {
				char next = tempBuilder1.charAt(i);
				if (next == 'a' || next == 'e' || next == 'i' || next == 'o' || next == 'u') {
					vowelCount++;
				} else {
					consonantCount++;
				}
			}
			
			// 모음이 1글자 이상이고 자음이 2글자 이상이면
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
