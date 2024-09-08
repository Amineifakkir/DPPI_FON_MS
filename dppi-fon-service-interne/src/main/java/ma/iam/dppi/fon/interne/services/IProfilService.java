package ma.iam.dppi.fon.interne.services;

import java.util.List;

import ma.iam.dppi.fon.interne.dtos.LibelleDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IProfilService {

	List<LibelleDto> getProfilsInternes();
	List<LibelleDto> getProfilsExternes();
}
