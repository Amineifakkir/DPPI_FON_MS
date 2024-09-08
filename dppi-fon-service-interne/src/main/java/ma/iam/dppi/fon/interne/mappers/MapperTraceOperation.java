package ma.iam.dppi.fon.interne.mappers;

import java.util.ArrayList;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.TraceOperation;
import ma.iam.dppi.fon.interne.dtos.TraceOperationDto;
import ma.iam.dppi.fon.interne.utils.DateUtils;
import ma.iam.dppi.fon.interne.utils.Utils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Z.BELGHAOUTI
 * 
 */
@Service
public class MapperTraceOperation {
	
	
	public TraceOperationDto toDto(TraceOperation t) {
		TraceOperationDto dto = null;
		if(t != null) {
			dto = new TraceOperationDto();
			
			dto.setIdt(t.getIdt());
			if(t.getDateOperation() != null) {
				dto.setDate(DateUtils.dateToStringDateSlash(t.getDateOperation()));
				dto.setHeure(DateUtils.dateToStringTimeComplet(t.getDateOperation()));
			}
			if(t.getLogin() != null && !"".equals(t.getLogin())) {
				dto.setLogin(t.getLogin());
			} else {
				if(t.getUtilisateur() != null) {
					dto.setLogin(t.getUtilisateur().getLogin());
				}
			}
			
			if(t.getNom() != null && !"".equals(t.getNom())) {
				dto.setNom(t.getNom());
			} else {
				if(t.getUtilisateur() != null) {
					dto.setNom(t.getUtilisateur().getNom());
				}
			}
			
			if(t.getPrenom() != null && !"".equals(t.getPrenom())) {
				dto.setPrenom(t.getPrenom());
			} else {
				if(t.getUtilisateur() != null) {
					dto.setPrenom(t.getUtilisateur().getPrenom());
				}
			}
			
			if("add".equalsIgnoreCase(t.getCode())) {
				dto.setTypeOperation("AJOUT");
			} else if("modif".equalsIgnoreCase(t.getCode())){
				dto.setTypeOperation("MODIFICATION");
			} else if("del".equalsIgnoreCase(t.getCode())) {
				dto.setTypeOperation("SUPPRESSION");
			} else if("cnsl".equalsIgnoreCase(t.getCode())) {
				dto.setTypeOperation("CONSULTATION");
			} else if("chargement".equalsIgnoreCase(t.getCode())) {
				dto.setTypeOperation("CHARGEMENT");
			} else if("export".equalsIgnoreCase(t.getCode())) {
				dto.setTypeOperation("EXPORT");
			} else if("download".equalsIgnoreCase(t.getCode())) {
				dto.setTypeOperation("TELECHARGEMENT");
			} else {
				dto.setTypeOperation(t.getTypeOperation());
			}
			dto.setApplication(t.getApplication());
			dto.setModule("DPPI-FON");
			if("INTERNE".equalsIgnoreCase(t.getApplication())) {
				if(t.getEntite() != null && !"".equals(t.getEntite())) {
					dto.setEntite(t.getEntite());
				} else {
					if(t.getUtilisateur() != null && t.getUtilisateur().getEntite() != null) {
						dto.setEntite(t.getUtilisateur().getEntite().getLabel());
					}
				}
			} else if ("EXTERNE".equalsIgnoreCase(t.getApplication())) {
				if(t.getOperateur() != null && !"".equals(t.getOperateur())) {
					dto.setOperateur(t.getOperateur());
				} else {
					if(t.getUtilisateur() != null && t.getUtilisateur().getOperateur() != null) {
						dto.setOperateur(t.getUtilisateur().getOperateur().getLabel());
					}
				}
			}
			
			if(t.getProfil() != null && !"".equals(t.getProfil())) {
				dto.setProfils(t.getProfil());
			} else {
				if(t.getUtilisateur() != null && t.getUtilisateur().getListProfils() != null 
						&& CollectionUtils.isNotEmpty(t.getUtilisateur().getListProfils())) {
					List<String> listP = new ArrayList<>();
					for(Profil p: t.getUtilisateur().getListProfils()) {
						listP.add(p.getLibelle());
					}
					String profil = Utils.joinListString(listP, " / ");
					dto.setProfils(profil);
				}
			}
			dto.setDescription(t.getDescription());
			dto.setOperation(t.getOperation());
			dto.setObjetModifie(t.getObjetModifie());
			dto.setValeurAvant(t.getOldValue());
			dto.setValeurApres(t.getNewValue());
		}
		return dto;
	}
	
	public List<TraceOperationDto> toDtos(List<TraceOperation> entities) {
		List<TraceOperationDto> dtos = new ArrayList<>();
		if(entities != null && !entities.isEmpty()) {
			for(TraceOperation entity : entities) {
				dtos.add(toDto(entity));
			}			
		}
		return dtos;
	}
	
}
