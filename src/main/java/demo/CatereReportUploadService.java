/**
 * 
 */
package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author N521745
 *
 */
 
public class CatereReportUploadService {

	private final Logger logger = LoggerFactory.getLogger(CatereReportUploadService.class);
	//@Value("${ssim.host}")
	private String hostName;
	//@Value("${ssim.port}")
	private int portNo;
	//@Value("${ssim.user}")
	private String username;
	//@Value("${ssim.password}")
	private String password;
	//@Value("${ssim.dir}")
	private String sftpLocation;

	private ChannelSftp setupJsch() throws JSchException {
		JSch jsch = new JSch();
		Session jschSession = jsch.getSession(username, hostName);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		jschSession.setConfig(config);
		jschSession.setPassword(password);
		jschSession.connect();
		return (ChannelSftp) jschSession.openChannel("sftp");
	}

//	public boolean uploadSftpFromPath(InputStream inputStream, String fileName) throws CatereReportException {
//		ChannelSftp channelSftp = null;
//		try {
//			channelSftp = setupJsch();
//		} catch (Exception e) {
//			logger.error(Constants.CALLING_ERROR + e);
//			throw new CatereReportException(e.getMessage());
//		}
//		try {
//			channelSftp.connect();
//		} catch (JSchException e) {
//			logger.error(Constants.CONNECTION_ERROR + e);
//			throw new CatereReportException(e.getMessage());
//		}
//		try {
//			channelSftp.put(inputStream, sftpLocation+fileName);
//			logger.info(Constants.UPLOAD);
//		} catch (SftpException e) {
//			logger.error(Constants.UPLOAD_ERROR + e);
//			throw new CatereReportException(e.getMessage());
//		}
//		channelSftp.exit();
//		return true;
//	}

}
