package satellite;

/**
 * Created by FengSi on 2016/12/19 at 12:54.
 */
public class SensorData {

    public static void main(String[] args) {
        SensorData[] sensorDatas = new SensorData[10];
        System.out.println(sensorDatas[0]);
        System.out.println(sensorDatas[0] == null);
//        sensorDatas[0].setAnomalyName("a");
        sensorDatas[0] = new SensorData();
        System.out.println(sensorDatas[0].getAnomalyName());
    }


    private Object[][] data;
    private Object[][] anomalies;
    private String dataName;
    private String anomalyName;


    public Object[][] getData() {
        return data;
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public Object[][] getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(Object[][] anomalies) {
        this.anomalies = anomalies;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getAnomalyName() {
        return anomalyName;
    }

    public void setAnomalyName(String anomalyName) {
        this.anomalyName = anomalyName;
    }
}
