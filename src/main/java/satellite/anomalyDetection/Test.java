package satellite.anomalyDetection;

import java.util.ArrayList;

public class Test {
	/**
	 * 初始化：读文件，符号化
	 * @param filename
	 * @param alphabetSize
	 * @return
	 */
	String init(String filename, int alphabetSize, int winSize){
		ArrayList<Double> data = new ArrayList<Double>();	

		//从文件中读出时间序列数据
		IO input = new IO();
		data = input.readFromTxt(filename);
		System.out.println("original data points:　"+input.getLength());
		
		//转换为符号
		int length = input.getLength();
		if(length % winSize != 0){
			for(int i=0; i<length%winSize; i++){
//				System.out.println(data.get(length - length%winSize));
				data.remove(length - length%winSize);
			}
		}
		return ConvertToSymbol.convertToSym(data, alphabetSize, winSize);
	}
	
	/**
	 * 构建概率后缀树,检测出的异常序列,并将异常子序列和检测序列中的异常点位置写入文件
	 * @param filename:构建后缀树所用数据
	 * @param testfilename:检测所用数据
	 * @param alphabetSize:字母表大小
	 * @param winSize:paa窗口大小（必须能被文件中double值的个数整除）
	 * @param level:后缀树深度
	 * @param MinCount:后缀树每个结点最小计数
	 * @return
	 */
	ArrayList<Integer> anomalyDetection(String filename, String testfilename, String path, 
			int alphabetSize, int level, int MinCount,int winSize, double theta){
//		String path = ".\\output.txt";
		ArrayList<Integer> abnormalPoints = new ArrayList<Integer>();
		
		String data = init(filename, alphabetSize, winSize);		//时间序列数据
//		System.out.println(data.substring(0,100));
		String test = init(filename, alphabetSize, winSize);		//待检测数据
		
		if(data!=null && test!=null){
			// 构造概率后缀树
			PST tree = new PST();
			ArrayList<Node> T = tree.construct(data, level, alphabetSize, MinCount);

			// 检测异常
			findingAnomaly A = new findingAnomaly();
			abnormalPoints = A.detectAnomaly(test, T, level, alphabetSize,theta, MinCount);

			System.out.println("number of anomaly points: "+abnormalPoints.size());
			System.out.println();
			
			Fmeasure f1 = new Fmeasure();
			f1.computeFmeasure(abnormalPoints);
			System.out.println("P: "+f1.getP()+",	R: "+f1.getR()+",	F-Measure: "+f1.computeFmeasure(abnormalPoints));
			
			IO output = new IO();
			output.writeToFile(path, abnormalPoints);
			
			return abnormalPoints;
		}
		else{
			return null;
		}
				
		
	}
		
	public static void main(String[] args){
		long start = System.currentTimeMillis();
		int alphabetSize=3;	//字母表大小
		int winSize = 1;	//PAA窗口大小
		int level = 3;		//除根结点以外的树的深度。树的层数=level+1;
		int MinCount = 30;
		double theta = 0.05;
		String filename = ".\\input.txt";
		String testfilename = ".\\input.txt";
		String path = ".\\output.txt";
		ArrayList<Integer> abnormalPoints = new ArrayList<Integer>();
		
		Test temp = new Test();
		abnormalPoints = temp.anomalyDetection(filename, testfilename, path, alphabetSize, level, MinCount, winSize, theta);
		
		long stop = System.currentTimeMillis();
		long time = (stop - start) / 1000;
		long millis = (stop - start) % 1000;
		System.out.println("runtime: "+time+"second, "+millis+"millisecond");
		if(abnormalPoints != null){
			System.out.println("anomaly: "+abnormalPoints.size());
			for (Integer i : abnormalPoints) {
				System.out.println(i);
			}
		}
		
	}

	
}
