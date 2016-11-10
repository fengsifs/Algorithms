package satellite.anomalyDetection;

import java.util.ArrayList;

public class findingAnomaly {
	private Mapper map = new Mapper();

	int findNode(ArrayList<Node> T, String sub, int table){
		int currentNode = map.mapToInteger(""+sub.charAt(sub.length()-1)) + 1;
		int position = currentNode;
		for(int m=sub.length()-1; m>=0; m--){
			for(int n=currentNode; n<currentNode+table; n++){
				if(T.get(n).getValue().equals(sub.substring(m, sub.length()))){
					position = currentNode;
					currentNode = n * table + 1;
					break;
				}
			}
		}
		return position;
	}

	/**
	 * 
	 * @param S:待检测序列
	 * @param T：后缀树
	 * @param level：树深
	 * @param table：字母表大小
	 * @return 异常子序列首符号的在原始检测序列中的下标
	 */
	ArrayList<Integer> detectAnomaly(String S, ArrayList<Node> T, int level, int table ,double delta, int minCount){
		ArrayList<Integer> abnormalPoints = new ArrayList<Integer>();
		for(int t=1; t<S.length(); t++){
			for(int l=1; l<=level && (t-l)>=0; l++){
				String sub = S.substring(t-l, t);
				int index = findNode(T, sub, table);
				if(T.get(index).getbranchingProbability()[map.mapToInteger(""+S.charAt(t))] < delta){					
					abnormalPoints.add(t);
					break;
				}

			}
		}
		return abnormalPoints;
	}//detectAnomaly
	
	public static void main(String[] args){
		String S = "abcabcabc";
		String test = "abbbbabc";
		ArrayList<Integer> abnormalPoints = new ArrayList<Integer>();
		int level = 4;		//除根结点以外的树深
		int MinCount = 1;		//每个结点出现的最小次数
		int table = 3;			//符号表大小
		double delta = 0.1;
		
		//构建suffix tree
		PST tree = new PST();
		ArrayList<Node> T = tree.construct(S, level, table,MinCount);
		System.out.println("suffix Tree:");
		for(int i=0; i<T.size(); i++){
			if(T.get(i) != null)
				System.out.println(T.get(i));
		}
		System.out.println();

		//检测异常
		findingAnomaly A = new findingAnomaly();
		abnormalPoints = A.detectAnomaly(test, T, table,level,delta,MinCount);
		System.out.println("anomaly");
		for(int i=0; i<abnormalPoints.size(); i++){
			System.out.println(abnormalPoints.get(i));
		}		
	}
	
}
