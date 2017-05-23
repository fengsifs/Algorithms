package shell;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by FengSi on 2017/05/22 at 16:09.
 */
public class ShellTest {
    public static void main(String[] args) {
        JSch jSch = new JSch();
        try {
            jSch.addIdentity("C:\\Users\\FengSi\\Desktop\\originkey.pem");
            Session session = jSch.getSession("ubuntu", "10.141.220.81", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand("source /etc/profile\n sqoop export " +
                    "--connect jdbc:postgresql://10.141.209.111:5432/root " +
                    "--username root --password mima --table auditnew " +
                    "--export-dir /user/hive/warehouse/dataqualitytest.db/audit_rename " +
                    "--input-fields-terminated-by '\\001' --lines-terminated-by '\\n'");
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);
            channelExec.connect();
            InputStream in = channelExec.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                System.out.println(buf);
            }
            reader.close();
            channelExec.disconnect();
            session.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }

    }
}
