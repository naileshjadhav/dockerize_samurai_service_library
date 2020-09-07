package com.zensar.autodeployment.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class PublicKeySshSession {

	static final Logger logger = LoggerFactory.getLogger(PublicKeySshSession.class);
	final Session session;
	private String remoteDir;
	private String fileLocation;
	private String fileName;

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public PublicKeySshSession(final Builder builder) {
		this.session = builder.jschSession;
	}

	public void execute(String command) throws Exception {
		if (session == null) {
			throw new IllegalArgumentException("Session object is null.");
		}

		if (command != null && command.isEmpty()) {
			throw new IllegalArgumentException("SSH command is blank.");
		}

			session.connect();
			ChannelSftp sftpChanel = (ChannelSftp) session.openChannel("sftp");
			sftpChanel.connect();
			String localFile = fileLocation + fileName;
			logger.info("remoteDir: {}", remoteDir);
			try {
				sftpChanel.put(localFile, remoteDir);
			} catch (SftpException e) {
				throw new Exception("Not able to copy file from source to destination!");
			}
			sftpChanel.exit();
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			((ChannelExec) channel).setPty(false);
			// Get an InputStream from this channel and read messages, generated
			// by the executing command, from the remote side.
			// create the execution channel over the session
			ChannelExec channelExec = (ChannelExec) channel;
			channelExec.setCommand("sh" + " " + remoteDir + fileName);
			InputStream in = channelExec.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			channel.connect();
			while ((line = reader.readLine()) != null) {
				logger.info("line {}" + line);
			}
			// Command execution completed here.Retrieve the exit status command
			int exitStatus = channelExec.getExitStatus();
			if (exitStatus > 0) {
				logger.info("Remote script exec error! {}" + exitStatus);
			}
			logger.info("Sh file executed......");
			logger.info("Removing Sh file ......");
			channel.connect();
			channelExec.setCommand("sudo rm " + remoteDir + fileName);
			in = channelExec.getInputStream();
			// Command execution completed here.Retrieve the exit status command
			exitStatus = channelExec.getExitStatus();
			if (exitStatus > 0) {
				logger.info("Removing sh script exec error! {}" + exitStatus);
			} else
				logger.info("Removing Sh file completed ......");
			channel.disconnect();
			session.disconnect();		
	}

	public static class Builder {
		private String host;
		private String username;
		private int port;
		private String privateKeyPath;
		private Session jschSession;

		public Builder(String host, String username, int port, String path) {
			this.host = host;
			this.username = username;
			this.port = port;
			this.privateKeyPath = path;
		}

//		private void validate() {
//			if (port != 22) {
//				throw new IllegalArgumentException("SSH Port number must be 22.");
//			}
//		}

		public PublicKeySshSession build() {
			//validate();
			JSch jsch = new JSch();
			Session session = null;
			try {
				jsch.addIdentity(privateKeyPath);
				session = jsch.getSession(username, host, port);
				session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
			} catch (JSchException e) {
				throw new RuntimeException("Failed to create Jsch Session object.", e);
			}
			this.jschSession = session;
			return new PublicKeySshSession(this);
		}
	}
}
