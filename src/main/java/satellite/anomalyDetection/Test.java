package satellite.anomalyDetection;

import java.util.ArrayList;

public class Test {
	/**
	 * ��ʼ�������ļ������Ż�
	 * @param filename
	 * @param alphabetSize
	 * @return
	 */
	String init(String filename, int alphabetSize, int winSize){
		ArrayList<Double> data = new ArrayList<Double>();	

		//���ļ��ж���ʱ����������
		IO input = new IO();
		data = input.readFromTxt(filename);
		System.out.println("original data points:��"+input.getLength());
		
		//ת��Ϊ����
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
	 * �������ʺ�׺��,�������쳣����,�����쳣�����кͼ�������е��쳣��λ��д���ļ�
	 * @param filename:������׺����������
	 * @param testfilename:�����������
	 * @param alphabetSize:��ĸ���С
	 * @param winSize:paa���ڴ�С�������ܱ��ļ���doubleֵ�ĸ���������
	 * @param level:��׺�����
	 * @param MinCount:��׺��ÿ�������С����
	 * @return
	 */
	ArrayList<Integer> anomalyDetection(String filename, String testfilename, String path, 
			int alphabetSize, int level, int MinCount,int winSize, double theta){
//		String path = ".\\output.txt";
		ArrayList<Integer> abnormalPoints = new ArrayList<Integer>();
		
		String data = init(filename, alphabetSize, winSize);		//ʱ����������
//		System.out.println(data.substring(0,100));
		String test = init(filename, alphabetSize, winSize);		//���������
		
		if(data!=null && test!=null){
			// ������ʺ�׺��
			PST tree = new PST();
			ArrayList<Node> T = tree.construct(data, level, alphabetSize, MinCount);

			// ����쳣
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
		int alphabetSize=3;	//��ĸ���С
		int winSize = 1;	//PAA���ڴ�С
		int level = 3;		//������������������ȡ����Ĳ���=level+1;
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
