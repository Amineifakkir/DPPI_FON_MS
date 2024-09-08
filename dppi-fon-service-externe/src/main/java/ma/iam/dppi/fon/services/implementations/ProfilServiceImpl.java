package ma.iam.dppi.fon.services.implementations;

import java.util.List;

import ma.iam.dppi.fon.communs.repository.ProfilRepository;
import ma.iam.dppi.fon.dtos.LibelleDto;
import ma.iam.dppi.fon.mappers.MapperProfil;
import ma.iam.dppi.fon.services.IProfilService;
import ma.iam.dppi.fon.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Transactional
@Service
public class ProfilServiceImpl implements IProfilService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProfilRepository profilDaoImpl;
	
	
	public List<LibelleDto> getProfilsExternes() {
		List<LibelleDto> profilsExternes = MapperProfil.convertToDto(profilDaoImpl.getAllProfilExternes());
		if(profilsExternes != null){
			logger.info(Utils.getLogParam()
					+ "Recuperation de la liste des profils externe de tailles: " + profilsExternes.size());
		}
		return profilsExternes;
	}
}
