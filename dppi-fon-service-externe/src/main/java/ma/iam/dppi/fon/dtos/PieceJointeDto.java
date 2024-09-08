package ma.iam.dppi.fon.dtos;


public class PieceJointeDto {

	private Long idt;

	private String fileName;

	private String originFileName;
	
	private DemandeDto demandeDto;

	
	public DemandeDto getDemandeDto() {
		return demandeDto;
	}

	public void setDemandeDto(DemandeDto demandeDto) {
		this.demandeDto = demandeDto;
	}

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	
	
}
