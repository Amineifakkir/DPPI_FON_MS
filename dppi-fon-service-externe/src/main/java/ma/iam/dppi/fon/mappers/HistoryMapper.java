package ma.iam.dppi.fon.mappers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.domain.ImportHistory;
import ma.iam.dppi.fon.dtos.HistoryDto;
import ma.iam.dppi.fon.services.IUtilisateurService;
import ma.iam.dppi.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author K.ELBAGUARI
 *
 */
@Service
public class HistoryMapper {
	
	@Autowired
	private IUtilisateurService userService;
	
	public HistoryDto toDto(ImportHistory history) {
		HistoryDto dto = new HistoryDto();
		dto.setIdt(history.getIdt());
		dto.setDateImport(DateUtils.toDateTime(history.getDateImport()));
		dto.setDateFinImport(DateUtils.toDateTime(history.getDateFinImport()));
		dto.setImportedBy(history.getImportedBy());
		dto.setSuccess(history.getSuccess());
		dto.setType(history.getType() != null ? history.getType().getName() : "");
		dto.setFile(history.getFile());
		dto.setLog(history.getLog());
		dto.setRoles(splitRoles(history.getRoles()));
		
		if(history.getEntite() != null && !"".equals(history.getEntite())) {
			dto.setEntite(history.getEntite());
		} else {
			if(history.getImportedBy() != null) {
				Utilisateur user = userService.getUserByLogin(history.getImportedBy());
				if(user != null) {
//					dto.setEntite(user.getEntite()
							
				}
			}
		}
		
		return dto;
	}

	private List<String> splitRoles(String roles) {
		List<String> result = new ArrayList<>();
		if (StringUtils.isBlank(roles)) {
			return result;
		}

		return Arrays.asList(roles.split(","));
	}

}
