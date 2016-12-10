package satellite.anomalyDetection;

import java.util.ArrayList;


public class PST {
	private int k;
	private int L;
	ArrayList<Node> lastLevel = new ArrayList<Node>(); // 当前层的所有结点
	ArrayList<Node> levelNode = new ArrayList<Node>(); // 下一层的所有结点
	ArrayList<Node> T = new ArrayList<Node>(); // 树结点
	Mapper map = new Mapper();

	void init(int level, int table) {
		k = 2; // 当前层
		L = level; // 树的最大深度
		// 初始化lastLevel和T,将树的根节点设为空
		T.add(null);
		for(int i=0; i< table; i++){
			Node node = new Node(new int[table], 0, 0.0,map.mapToString(i));
			lastLevel.add(node);
			T.add(node);
		}
			
	}

	// 建树
	ArrayList<Node> construct(String S, int level, int table, int MinCount) {

		init(level, table);

		while (k <= L) { // 树的每一层
			for (int i = 0; i<lastLevel.size(); i++) {
				if(lastLevel.get(i) != null){
					for(int j=0; j<table; j++){
						Node node = new Node(new int[table], 0, 0.0,map.mapToString(j)+lastLevel.get(i).getValue());
						T.add(node);
						levelNode.add(node);
					}
				}
			}
			lastLevel.clear();
			for (int i = 0; i < levelNode.size(); i++) {
				lastLevel.add(levelNode.get(i));
			}
			levelNode.clear();
			k++;
		}

		for(int i=1; i<S.length(); i++){
			for(int j=1; j<=L; j++){
				if(i-j >=0){
					String sub = S.substring(i-j, i);
					
					// traverse PST to find the corresponding node
					int currentNode = map.mapToInteger(""+sub.charAt(sub.length()-1)) + 1;
					int position = currentNode;
					for(int m=sub.length()-1; m>=0; m--){
						for(int n=currentNode; n<currentNode+table; n++){
							if(T.get(n).getValue().equals(sub.substring(m, sub.length()))){
								position = n;
								currentNode = n * table + 1;
								break;
							}
						}
					}
					
					Node tem = T.get(position);
					int count = tem.getCount();
					count++;
					tem.setCount(count);
					int[] suffix = tem.getNextSymbol();
					suffix[map.mapToInteger(""+S.charAt(i))]++;
					tem.setNextSymbol(suffix);
					T.set(position, tem);
				}
				else
					break;
			}
		}
		
		for(int i=0; i<T.size(); i++){
			Node node = T.get(i);
			if(node != null){
				double[] probability = new double[table];
				for(int j=0; j<table; j++){
					probability[j] = node.getNextSymbol()[j] * 1.0 / node.getCount();
				}
				node.setbranchingProbability(probability);
				T.set(i, node);
			}
		}
		

		// 打印树的非空结点
		// System.out.println("print the non-empty node of the tree:");
		int size = 0;
		for (int i = 0; i < T.size(); i++) {
			if (T.get(i) != null){
				size++;
				//System.out.println(T.get(i).getValue()+": "+T.get(i).getCount());
				//System.out.println(T.get(i));
			}
		}
		// 打印结点数
		System.out.println("number of nodes: " + size);

		return T;
	}

	public static void main(String[] args) {
		String S = "abababbbbbbbbbbbbbbcbcbccc";
		int level = 3; // 除根结点以外的树的深度。树的层数=level+1;
		int MinCount = 1;
		int table = 3;
		PST tree = new PST();
		ArrayList<Node> TR = tree.construct(S, level, table, MinCount);
		for (int i = 0; i < TR.size(); i++) {
			System.out.println(TR.get(i));
		}
	}
}
