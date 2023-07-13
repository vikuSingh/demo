package demo;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Test1 {

	private static final String REMOTE_HOST = "ftp.baplc.com";
	private static final String USERNAME = "avaviana";
	private static final String PASSWORD = "Av$716j4";
	private static final int REMOTE_PORT = 1122;
	private static final int SESSION_TIMEOUT = 10000;
	private static final int CHANNEL_TIMEOUT = 5000;

	public static void main(String args[]) {
		String localFile = "C:\\Users\\n521745\\Documents\\one.text";
		String remoteFile = "/to-ba/one.text";
		Session jschSession = null;
		try {
			JSch jsch = new JSch();
			jsch.setKnownHosts("/home/javatpoint/.ssh/known_hosts");
			jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
			jschSession.setPassword(PASSWORD);

			jschSession.connect(SESSION_TIMEOUT);
			Channel sftp = jschSession.openChannel("sftp");
			sftp.connect(CHANNEL_TIMEOUT);
			ChannelSftp channelSftp = (ChannelSftp) sftp;
			channelSftp.put(localFile, remoteFile);
			channelSftp.exit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jschSession != null) {
				jschSession.disconnect();
			}
		}
		System.out.println("Done");
	}
}
