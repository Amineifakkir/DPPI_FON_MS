package ma.iam.dppi.fon.interne.services.implementations;

import java.util.List;

import ma.iam.dppi.fon.communs.repository.ProfilRepository;
import ma.iam.dppi.fon.interne.dtos.LibelleDto;
import ma.iam.dppi.fon.interne.mappers.MapperProfil;
import ma.iam.dppi.fon.interne.services.IProfilService;
import ma.iam.dppi.fon.interne.utils.Utils;

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
@Service
@Transactional
public class ProfilServiceImpl implements IProfilService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProfilRepository profilDaoImpl;
	
	@Override
	public List<LibelleDto> getProfilsInternes() {
		List<LibelleDto> dtos = MapperProfil.convertToDto(profilDaoImpl.getAllProfilInternes());
		if(dtos != null){
			logger.info(Utils.getLogParam()
					+ "Recuperation de la liste des profils internes de tailles: " + dtos.size());
		}
		return dtos;
	}
	
	@Override
	public List<LibelleDto> getProfilsExternes() {
		List<LibelleDto> profilsExternes = MapperProfil.convertToDto(profilDaoImpl.getAllProfilExternes());
		if(profilsExternes != null){
			logger.info(Utils.getLogParam()
					+ "Recuperation de la liste des profils externes de tailles: " + profilsExternes.size());
		}
		return profilsExternes;
	}
}
