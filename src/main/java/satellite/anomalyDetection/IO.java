package satellite.anomalyDetection;

import java.io.*;
import java.util.ArrayList;

public class IO {
	private int length;
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	/**���봦��:��TXT�ļ��ж���double��ֵ������ArrayList<Double>
	 * @return double[]
	 */
	ArrayList<Double> readFromTxt(String path){
		length = 0;
		ArrayList<Double> value = new ArrayList<Double>();
		try{
	//		String path = ".\\input.txt";		
			File filename = new File(path);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			
			String line = "";
			
			while((line = br.readLine()) != null){	
				if(!line.trim().equals("")){		//�������в�����
					String[] tem = line.split(" ");		//�Կո�Ϊ�ָ������ָ��double��ֵ
					for (String i : tem) {
						if (!i.equals("")){			//����Ϊ�������������
							value.add(Double.parseDouble(i));	
							length++;
						}
					}
				}
				
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return value;
	}
	
	void writeToFile(String path, ArrayList<Integer> data){
		try{
			//		String path = ".\\output.txt";		
					File filename = new File(path);
					filename.createNewFile();
					BufferedWriter out = new BufferedWriter(new FileWriter(filename));
					out.write("abnormal data points:");
					out.write("\r\n");
					for (int i=0; i<data.size(); i++) {
						out.write(""+data.get(i)+", ");
				//		out.write("\r\n");
					}
					out.close();
				}catch(Exception e){
					e.printStackTrace();
				}
	}
	
	public static void main(String[] args){
		//���ļ�����
		String path = ".\\input.txt";
		IO input = new IO(); 
		ArrayList<Double> data = new ArrayList<Double>();
		data = input.readFromTxt(path);
		for(Double i : data){
			System.out.println(i);
		}	
		System.out.println(input.length);
		
/*		//д�ļ�����
		String path = ".\\output.txt";
		ArrayList<String> data = new ArrayList<String>();
		for(int i=70; i<90; i++){
			data.add(""+(char)i);
		}
		IO output = new IO();
		output.writeToFile(path, data);*/
	}
}
