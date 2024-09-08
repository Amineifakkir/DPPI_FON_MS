package ma.iam.dppi.fon.services;

import java.util.List;

import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.dtos.OperateurDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IOperateurService {
	
	abstract List<OperateurDto> getAllOperateurs();
	
	abstract List<OperateurDto> getOperateurById(Long idt);
   
	abstract List<String> getListeLibelleOperateurs();
	Operateur getOperateur(Long idt );
}

