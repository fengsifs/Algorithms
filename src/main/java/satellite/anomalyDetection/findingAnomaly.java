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
	 * @param S:���������
	 * @param T����׺��
	 * @param level������
	 * @param table����ĸ���С
	 * @return �쳣�������׷��ŵ���ԭʼ��������е��±�
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
		int level = 4;		//����������������
		int MinCount = 1;		//ÿ�������ֵ���С����
		int table = 3;			//���ű��С
		double delta = 0.1;
		
		//����suffix tree
		PST tree = new PST();
		ArrayList<Node> T = tree.construct(S, level, table,MinCount);
		System.out.println("suffix Tree:");
		for(int i=0; i<T.size(); i++){
			if(T.get(i) != null)
				System.out.println(T.get(i));
		}
		System.out.println();

		//����쳣
		findingAnomaly A = new findingAnomaly();
		abnormalPoints = A.detectAnomaly(test, T, table,level,delta,MinCount);
		System.out.println("anomaly");
		for(int i=0; i<abnormalPoints.size(); i++){
			System.out.println(abnormalPoints.get(i));
		}		
	}
	
}
