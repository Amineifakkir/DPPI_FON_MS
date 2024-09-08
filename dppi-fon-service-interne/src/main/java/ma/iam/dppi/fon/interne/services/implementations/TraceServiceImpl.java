package ma.iam.dppi.fon.interne.services.implementations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.Parametrage;
import ma.iam.dppi.fon.communs.domain.Profil;
import ma.iam.dppi.fon.communs.domain.TraceConnexion;
import ma.iam.dppi.fon.communs.domain.TraceOperation;
import ma.iam.dppi.fon.communs.domain.Utilisateur;
import ma.iam.dppi.fon.communs.repository.ParametrageRepository;
import ma.iam.dppi.fon.communs.repository.TraceConnexionRepository;
import ma.iam.dppi.fon.communs.repository.TraceOperationRepository;
import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.services.ITraceService;
import ma.iam.dppi.fon.interne.utils.Utils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class TraceServiceImpl implements ITraceService {

	@Autowired
	private TraceOperationRepository traceDaoImpl;
	
	@Autowired
	private ParametrageRepository parametrageDaoImpl;
	
	@Autowired
	private TraceConnexionRepository traceConnexionDaoImpl;
	
	@Autowired
	UtilisateurRepository utilisateurDaoImpl;
	
	public void traceOperation(ActionCode code, String description, 
			String operation, String typeOperation,
			String objetModifie,
			String oldValue, String newValue) {
		
		String profil = "";
		String login = "";
		String nom  = "";
		String prenom = "";
		String entite = "";
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurDaoImpl.findByLogin(username);
		if(utilisateur != null) {
			login = utilisateur.getLogin();
			nom = utilisateur.getNom();
			prenom = utilisateur.getPrenom();
			entite = utilisateur.getEntite() != null ? utilisateur.getEntite().getLabel() : "";
			if(utilisateur.getListProfils() != null 
					&& CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
				List<String> listP = new ArrayList<>();
				for(Profil p: utilisateur.getListProfils()) {
					listP.add(p.getLibelle());
				}
				profil = Utils.joinListString(listP, " / ");
			}
		}
		
		Date now = null;
		Parametrage parametrageGMT = parametrageDaoImpl.findByCode("VGMT");
		try {
			if(parametrageGMT != null){
				if("0".equalsIgnoreCase(parametrageGMT.getValeur())){
					now = Utils.getCurrentDateGMT();
				}else{
					now = Utils.getCurrentDateGMTPlus1();
				}
			}else{
				now = new Date();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TraceOperation trace = new TraceOperation(code.getName(), description, utilisateur, 
				now, operation, typeOperation, profil, "DPPI-FON",
				objetModifie, oldValue, newValue, "INTERNE", login, nom, prenom, entite, null);
		traceDaoImpl.save(trace);
	}
	
	public void traceConnexion(ActionCode code, String description, 
			String typeConnexion, String login) {
		String profil = "";
		String nom = "";
		String prenom = "";
		String username = "";
		String entite = "";
		if(login != null) {
			username = login;
		} else {
			username = SecurityContextHolder.getContext().getAuthentication().getName();
		} 
		Utilisateur utilisateur = utilisateurDaoImpl.findByLogin(username);
		if(utilisateur != null) {
			login = utilisateur.getLogin();
			nom = utilisateur.getNom();
			prenom = utilisateur.getPrenom();
			entite = utilisateur.getEntite() != null ? utilisateur.getEntite().getLabel() : "";
			if(utilisateur.getListProfils() != null 
					&& CollectionUtils.isNotEmpty(utilisateur.getListProfils())) {
				List<String> listP = new ArrayList<>();
				for(Profil p: utilisateur.getListProfils()) {
					listP.add(p.getLibelle());
				}
				profil = Utils.joinListString(listP, " / ");
			}
		}
		
		Date now = null;
		Parametrage parametrageGMT = parametrageDaoImpl.findByCode("VGMT");
		try {
			if(parametrageGMT != null){
				if("0".equalsIgnoreCase(parametrageGMT.getValeur())){
					now = Utils.getCurrentDateGMT();
				}else{
					now = Utils.getCurrentDateGMTPlus1();
				}
			}else{
				now = new Date();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		TraceConnexion trace = new TraceConnexion(code.getName(), description, login, nom, 
				prenom, utilisateur, now, typeConnexion, profil, "INTERNE", entite, null, "DPPI-FON");
		traceConnexionDaoImpl.save(trace);
	}

}
