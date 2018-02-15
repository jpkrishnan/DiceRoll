import java.util.HashMap;
import java.util.Map;


public class Solution {

	public Solution() {
		
	}

	public int findTopDigit(int[] A) {
		HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
		for (int i=0; i<A.length; i++) {
			int count = (map.get(A[i]) == null) ? 0 : map.get(A[i]);
			if(count != 0)
				map.put(A[i], ++count);
			else 
				map.put(A[i], 1);
		}		
		
		map =  reduceArray(map, A.length);
		int matchArr[] = {0,0,0};	
		for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
			if(entry.getValue() <= 0 ) {
				int digit = entry.getKey();
				int oppDigit = findOppositeDigit(digit);
				if(oppDigit == 1 || oppDigit == 6) matchArr[0] = 1;
				if(oppDigit == 2 || oppDigit == 5) matchArr[1] = 1;
				if(oppDigit == 3 || oppDigit == 4) matchArr[2] = 1;
			}
		}
		int max = 0;
		int dupDigit = 0;	
		for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
			if(entry.getValue() > 0 ) {
				int digit = entry.getKey();
				if((digit == 1 || digit == 6) && matchArr[0] > 0) { //check if it is 1-6 combination that we eliminated
					if(entry.getValue() > max) {
						max = entry.getValue();
						dupDigit = entry.getKey();
					}
				}
				else 
					return digit;
				if((digit == 2 || digit == 5) && matchArr[1] > 0) { //check if it is 2-5 combination that we eliminated
					if(entry.getValue() > max) {
						max = entry.getValue();
						dupDigit = entry.getKey();
					}
				}
				else 
					return digit;
				if((digit == 3 || digit == 4) && matchArr[2] > 0) { //check if it is 3-4 combination that we eliminated
					if(entry.getValue() > max) {
						max = entry.getValue();
						dupDigit = entry.getKey();
					}
				}
				else 
					return digit;
			} 
		}
	
		return dupDigit;
	}
	
	public HashMap<Integer,Integer> reduceArray(HashMap<Integer,Integer> map, int size) {

		for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
			int oppDigit = findOppositeDigit(entry.getKey());
			
			if(map.get(oppDigit) != null) {
				int count = entry.getValue();
				map.put(entry.getKey(), --count);
				int bCount = map.get(oppDigit);
				map.put(oppDigit, --bCount);
			}
		}

		return map;
	}
	
	public int findOppositeDigit(int n) {
		if(n == 1) return 6;
		if(n == 6) return 1;
		if(n == 2) return 5;
		if(n == 5) return 2;
		if(n == 3) return 4;
		if(n == 4) return 3;
		
		return 0;
		
	}
	public int getMoves(int[] A) {
		
		if(A.length == 0) return 0;
		
		if(A.length == 1) return 0;
		
		if(A.length == 2) {
			if((A[0] == 1 && A[1] == 6 ) || (A[0] == 6 && A[1] == 1 )) return 2;
			if((A[0] == 2 && A[1] == 5 ) || (A[0] == 5 && A[1] == 2 )) return 2;
			if((A[0] == 3 && A[1] == 4 ) || (A[0] == 4 && A[1] == 3 )) return 2;
			
			return 1;
		}
		
		int topDigit = findTopDigit(A);
		if(topDigit == 0) topDigit = A[0];
		
		int oppDigit = findOppositeDigit(topDigit);
		int moves = 0;
		for (int i=0; i<A.length; i++) {
			if(A[i] == topDigit) continue;
			if(A[i] == oppDigit) 
				moves += 2;
			else
				moves += 1;
		}
		
		return moves;
	}
	
	/**
	 * @param args
	 * Find the minimum number of moves required to make all the dices show the same number after rolling N dices
	 * example when you roll 3 dice and it results in dice showing {1,2,3} the minimum number of moves required to make them all show same digit would be 2
	 * Similarly {1,1,6} would be 2 moves
	 * {1,6,2,3} would be 3 moves
	 * Remember changing 1 -> 6 is two moves. Similarly 2 -> 5 is two moves, 3 -> 4 is two moves. They are the opposite sides of a die
	 */
	public static void main(String[] args) {
		Solution sol = new Solution();
		int A[] = {1,1,1,2};
		System.out.println("The minimum number of moves = " + sol.getMoves(A));

	}

}
