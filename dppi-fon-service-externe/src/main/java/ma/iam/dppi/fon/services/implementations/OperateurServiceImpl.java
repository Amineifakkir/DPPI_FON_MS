/**
 * 
 */
package ma.iam.dppi.fon.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.iam.dppi.fon.communs.domain.Operateur;
import ma.iam.dppi.fon.communs.repository.OperateurRepository;
import ma.iam.dppi.fon.dtos.OperateurDto;
import ma.iam.dppi.fon.services.IOperateurService;
import ma.iam.dppi.fon.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class OperateurServiceImpl implements IOperateurService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OperateurRepository operateurDaoImp;

	@Override
	public Operateur getOperateur(Long idt) {
		Optional<Operateur> optional = operateurDaoImp.findById(idt);
		Operateur operateur = null;
		if (optional.isPresent()) {
			operateur = optional.get();
		}
		return operateur;
	}

	public List<OperateurDto> getOperateurById(Long idt) {
		List<OperateurDto> listOperateurDto = new ArrayList<>();
		if (idt == 0) {
			listOperateurDto = this.getAllOperateurs();
		} else {
			OperateurDto dto = new OperateurDto();
			Optional<Operateur> optionalOper = operateurDaoImp.findById(idt);
			if (optionalOper.isPresent()) {
				Operateur oper = optionalOper.get();
				if (oper != null) {
					BeanUtils.copyProperties(oper, dto);
					listOperateurDto.add(dto);
				}
			}
		}
		logger.info(Utils.getLogParam()
				+ "Recuperation la liste des operateurs de tailles: "
				+ listOperateurDto.size() + " par IDT: " + idt);
		return listOperateurDto;
	}

	public List<OperateurDto> getAllOperateurs() {
		List<Operateur> operateurs = operateurDaoImp.findAll();
		List<OperateurDto> listOperateurDto = new ArrayList<>();
		
		if (!operateurs.isEmpty()) {
			for (Operateur oper : operateurs) {
				OperateurDto dto = new OperateurDto();
				BeanUtils.copyProperties(oper, dto);
				listOperateurDto.add(dto);
			}
		}
		logger.info(Utils.getLogParam()
				+ "Recuperation la liste des operateurs de tailles: " + listOperateurDto.size());
		return listOperateurDto;
	}

	public List<String> getListeLibelleOperateurs() {
		List<OperateurDto> operateursDto = getAllOperateurs();
		OperateurDto opDto = new OperateurDto();
		opDto.setLibelle("Tous");
		opDto.setIdt(0l);
		operateursDto.add(opDto);
		List<String> listLibellesOperateurs = new ArrayList<String>();
		for (OperateurDto o : operateursDto)
			listLibellesOperateurs.add(o.getLibelle());
		logger.info(Utils.getLogParam()
				+ "Recuperation la liste des libelles des operateurs: "
				+ listLibellesOperateurs);
		return listLibellesOperateurs;
	}
}
