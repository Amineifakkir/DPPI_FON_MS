package ma.iam.dppi.fon.interne.services;

import java.util.List;

import ma.iam.dppi.fon.interne.dtos.OperateurDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IOperateurService {
	
	abstract List<OperateurDto> getAllOperateurs();
	
	abstract List<OperateurDto> getOperateurById(Long idt);
   
	abstract List<String> getListeLibelleOperateurs();
}

