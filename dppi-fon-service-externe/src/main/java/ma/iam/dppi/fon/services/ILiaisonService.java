package ma.iam.dppi.fon.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ma.iam.dppi.fon.domain.Demande;
import ma.iam.dppi.fon.domain.Liaison;
import ma.iam.dppi.fon.dtos.AddDemandeDevisDto;
import ma.iam.dppi.fon.dtos.DemandeAccesCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeGcCriteresDto;
import ma.iam.dppi.fon.dtos.DemandeOperationCriteresDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeAddDto;
import ma.iam.dppi.fon.dtos.LiaisonDemandeUppdateDto;
import ma.iam.dppi.fon.dtos.LiaisonDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */

public interface ILiaisonService {
	
	List<LiaisonDto> getListDemandeGcByParams(DemandeGcCriteresDto critDto) throws ParseException;
	Long getCountDemandeGcByParams(DemandeGcCriteresDto critDto) throws ParseException;
	Long saveDemande(LiaisonDemandeAddDto demandeGcDto)throws ParseException;
	Long saveDemandeByType(DemandeOperationCriteresDto demandeDto)throws ParseException;
	Long saveDemandeAcces(DemandeAccesCriteresDto demandeDto)throws ParseException;

	Long suppDemande( Long idt );
	LiaisonDemandeUppdateDto update(LiaisonDemandeUppdateDto demandeGcDto)throws ParseException;
	//Operation
	Demande saveOperationDemande (DemandeOperationCriteresDto criteresDto)throws ParseException;
	public Long uploadFile(MultipartFile file);
	Long archiveDemande(Long idt);
	

	public void enregistereDemande(DemandeAccesCriteresDto demandeUpdateDto) throws ParseException;
	
	public void saveDemandeWithIntervenant(DemandeAccesCriteresDto demandeDto) throws ParseException;
	public List<LiaisonDto> getListLiaison();
	
	public void addDemandeSignalisation(DemandeAccesCriteresDto demandeDto) throws ParseException;
	public List<LiaisonDto> getListLiaisonFaisable();
	void addDemandeDevis(AddDemandeDevisDto demandeUpdateDto) throws ParseException;
}

