package ma.iam.dppi.fon.interne.dtos;

import org.springframework.web.multipart.MultipartFile;

public class PieceJointeDemande {

	private Long idtDemande;
	
	private MultipartFile multipartFile;

	public Long getIdtDemande() {
		return idtDemande;
	}

	public void setIdtDemande(Long idtDemande) {
		this.idtDemande = idtDemande;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	
}
