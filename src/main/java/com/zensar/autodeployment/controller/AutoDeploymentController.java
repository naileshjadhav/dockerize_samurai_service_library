package com.zensar.autodeployment.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zensar.autodeployment.model.AwsDeploymentDto;
import com.zensar.autodeployment.model.AzureDeploymentDto;
import com.zensar.autodeployment.model.ResponseMessage;
import com.zensar.autodeployment.service.FilesStorageService;
import com.zensar.autodeployment.util.PublicKeySshSession;

@RestController
@CrossOrigin(origins = "*")
public class AutoDeploymentController {

	private static final Logger log = LoggerFactory.getLogger(AutoDeploymentController.class);

	@Autowired
	FilesStorageService storageService;
	private final Path root = Paths.get("uploads");

	@Value("${sshPort}")
	private int sshPort;
	@Value("${local.file.location}")
	private String filePath;
	@Value("${local.file.name}")
	private String localFileName;
	@Value("${remote.dir}")
	private String remoteDirectory;

	/**
	 * @param file
	 * @return message
	 */
	@PostMapping(value = "/aws/deploy")
	public ResponseEntity<ResponseMessage> deployAwsSamuraiAndOtherTools(
			@RequestParam(value = "privateKeyFile") MultipartFile privateKeyFile, @RequestParam String hostAddress,
			@RequestParam String hostUserName) {

		log.info("Aws deployment started...");
		String message = "";
		AwsDeploymentDto dto = new AwsDeploymentDto(hostAddress, hostUserName);
		log.info(dto.toString());
		try {
			storageService.init();
			long value = storageService.save(privateKeyFile);
			// message = "Uploaded the file successfully: " +
			// privateKeyFile.getOriginalFilename();
			if (value > 0) {
				String fileName = Files.list(root)
						.filter(e -> e.getFileName().toFile().getName()
								.equalsIgnoreCase(privateKeyFile.getOriginalFilename()))
						.findFirst().orElseThrow(() -> new Exception("PrivateKey not found!")).toString();
				log.info("PrivateKeyFile name {}", fileName);
				final PublicKeySshSession session = new PublicKeySshSession.Builder(hostAddress, hostUserName, sshPort,
						fileName).build();
				if (session == null) {
					throw new Exception("Not able to get session!");
				}
				session.setFileLocation(filePath);
				session.setFileName(localFileName);
				session.setRemoteDir(remoteDirectory);

				try {
					session.execute(
							"echo \"Sit down, relax, mix yourself a drink and enjoy the show...\" >> /tmp/test.out");
				} catch (Exception e) {
					throw new Exception("Not able to execute command!");
				}
			}
			message = "Deployment successful !!!";
			log.info("Aws deployment finished...");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			log.error("Exception caught {}", e);
			message = e.getMessage();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/azure/deploy")
	public void deployAzureSamuraiAndOtherTools(@RequestBody AzureDeploymentDto deploymentDto) {

	}
}
