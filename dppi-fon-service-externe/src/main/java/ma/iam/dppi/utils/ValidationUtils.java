package ma.iam.dppi.utils;// package ma.iam.dppi.utils;
// 
// import java.text.MessageFormat;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import ma.iam.dppi.fon.communs.domain.Utilisateur;
//import ma.iam.dppi.fon.communs.repository.UtilisateurRepository;
//import ma.iam.dppi.fon.constants.ErrorConstants;
//import ma.iam.dppi.fon.services.IUtilisateurService;
//import ma.iam.dppi.fon.utils.ExcelHelper;
//import ma.iam.dppi.fon.utils.ExcelParserInfo;
// 
// @Service
// public class ValidationUtils {
// 	
// 	@Value("${role.operateur_externe}")
// 	private String roleOperateurExterne;
// 
// 	@Value("${role.consultation_erpt}")
// 	private String roleConsultationErpt;
// 
//
// 
//
// 
//
//
//	@Autowired
//	private ExcelHelper excelHelper;
//
//	@Autowired
//	private IUtilisateurService userService;
//
//	@Autowired
//	private UtilisateurRepository utilisateurRepository;
//
//
//	public boolean validateUser(ExcelParserInfo parserInfo) {
//
//		String currentUserLogin = Utils.getCurrentUserLogin();
//		parserInfo.setLogin(currentUserLogin);
//		Utilisateur user = utilisateurRepository.findUserExterneByLogin(currentUserLogin);
//
//		if (user == null) {
//			excelHelper.addErrorLogRow(parserInfo, ErrorConstants.MESSAGE_ERROR_USER_INTROUVABLE,new MessageFormat(
//					ErrorConstants.MESSAGE_ERROR_USER_LOGIN_INTROUVABLE).format(new Object[] {currentUserLogin}));
//			return false;
//		}
//
//		List<String> userRoles = userService.getUserRoles();
//
//		if (!userRoles.contains(roleOperateurExterne)) {
//			excelHelper.addErrorLogRow(parserInfo, ErrorConstants.MESSAGE_ERROR_USER_DROIT,new MessageFormat(
//					ErrorConstants.MESSAGE_ERROR_USER_LOGIN_DROIT).format(new Object[] {currentUserLogin}));
//			return false;
//		}
//
//		parserInfo.setUser(user);
//		return true;
//	}
//	/*
//	 * public boolean validateUser(ExcelParserInfo parserInfo) {
//	 * 
//	 * String currentUserLogin = Utils.getCurrentUserLogin();
//	 * parserInfo.setLogin(currentUserLogin); Utilisateur user =
//	 * utilisateurRepository.findUserExterneByLogin(currentUserLogin);
//	 * 
//	 * if (user == null) { excelHelper.addErrorLogRow(parserInfo,
//	 * ErrorConstants.MESSAGE_ERROR_USER_INTROUVABLE,new MessageFormat(
//	 * ErrorConstants.MESSAGE_ERROR_USER_LOGIN_INTROUVABLE).format(new Object[]
//	 * {currentUserLogin})); return false; }
//	 * 
//	 * List<String> userRoles = userService.getUserRoles();
//	 * 
//	 * if (!userRoles.contains(roleOperateurExterne)) {
//	 * excelHelper.addErrorLogRow(parserInfo,
//	 * ErrorConstants.MESSAGE_ERROR_USER_DROIT,new MessageFormat(
//	 * ErrorConstants.MESSAGE_ERROR_USER_LOGIN_DROIT).format(new Object[]
//	 * {currentUserLogin})); return false; }
//	 * 
//	 * parserInfo.setUser(user); return true; }
//	 */
// 
// }
