package ma.iam.dppi.fon.dtos;

import java.util.Date;



public class OperationTronconDto {

 
	private Long idt;

	private Long idtTronconDto;
	
	private Long idtDemandeDto;
	
	private Date dateDebut;
	
	private Date dateRetour;

	private Double distanceSousTubage;

	public Long getIdt() {
		return idt;
	}

	public void setIdt(Long idt) {
		this.idt = idt;
	}

	



	public Long getIdtTronconDto() {
		return idtTronconDto;
	}

	public void setIdtTronconDto(Long idtTronconDto) {
		this.idtTronconDto = idtTronconDto;
	}

	public Long getIdtDemandeDto() {
		return idtDemandeDto;
	}

	public void setIdtDemandeDto(Long idtDemandeDto) {
		this.idtDemandeDto = idtDemandeDto;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Double getDistanceSousTubage() {
		return distanceSousTubage;
	}

	public void setDistanceSousTubage(Double distanceSousTubage) {
		this.distanceSousTubage = distanceSousTubage;
	}
	
	
	
	
}
