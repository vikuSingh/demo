package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Test2 {

	private ChannelSftp setupJsch() throws JSchException {
		String username = "avaviana";
		String password = "Av$716j4";
		String host = "ftp.baplc.com";
		JSch jsch = new JSch();
		Session jschSession = jsch.getSession(username, host);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		jschSession.setConfig(config);
		jschSession.setPassword(password);
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}

//	private ChannelSftp setupJsch1() throws JSchException {
//	    JSch jsch = new JSch();
//	    jsch.setKnownHosts("/Users/john/.ssh/known_hosts");
//	    Session jschSession = jsch.getSession(username, remoteHost);
//	    jschSession.setPassword(password);
//	    jschSession.connect();
//	    return (ChannelSftp) jschSession.openChannel("sftp");
//	}

//	private SSHClient setupSshj() throws IOException {
//		SSHClient client = new SSHClient();
//		client.addHostKeyVerifier(new PromiscuousVerifier());
//		client.connect("ftp.baplc.com");
//		client.authPassword("avaviana", "Av$716j4");
//		return client;
//	}
//
//	private String test(String localFile, String remoteDir) throws Exception {
//		SSHClient sshClient = setupSshj();
//		SFTPClient sftpClient = sshClient.newSFTPClient();
//		sftpClient.put(localFile, remoteDir + "one.text");
//		sftpClient.close();
//		sshClient.disconnect();
//		return "Success";
//	}

	public boolean uploadSftpFromPath(InputStream localFile, String sftpFile) {
		ChannelSftp channelSftp = null;
		try {
			channelSftp = setupJsch();
		} catch (JSchException e) {
			e.printStackTrace();
		}
		try {
			channelSftp.connect();
		} catch (JSchException e) {
			e.printStackTrace();
		}
		try {
			channelSftp.put(localFile, sftpFile);
			System.out.println("Upload Complete");
		} catch (SftpException e) {
			e.printStackTrace();
		}
		channelSftp.exit();
		return true;
	}

	public static void main(String[] args) throws Exception {
		String localFile = "./two.text";
		File file = new File(localFile);
		//System.out.println(file);
		InputStream inputStream = new FileInputStream(file.getAbsolutePath());
		String remoteFile = "to-ba/two.text";
		//System.out.println(new Test2().test(localFile, remoteFile));
		System.out.println(new Test2().uploadSftpFromPath(inputStream, remoteFile));
	}
}
