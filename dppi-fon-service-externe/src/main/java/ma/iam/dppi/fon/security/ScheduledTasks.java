package ma.iam.dppi.fon.security;

import java.util.Map;

import ma.iam.dppi.fon.services.IUtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Z.BELGHAOUTI
 *
 */
@Component
@EnableScheduling
public class ScheduledTasks {
	
	@Value("${session.timeout}")
	private String sessionTimeOut;
	
	@Autowired 
    private IUtilisateurService utilisateurService;
	

	@SuppressWarnings("rawtypes")
	@Scheduled(fixedRateString = "1000")
	public void sessionManagement() {
		Map<String, Integer> expiredMap = DBAuthenticationProvider.expiredMap;
		for (Map.Entry mapentry : expiredMap.entrySet()) {
			String key = (String) mapentry.getKey();
			
			if(!key.startsWith("expireMT_")) {
				int compt = (int) mapentry.getValue();
				int defaultSession = Integer.parseInt(sessionTimeOut);
				if (compt >= defaultSession) {
					utilisateurService.consulterEtModifierSessionIDParLogin(key);
					expiredMap.remove(key);
					expiredMap.put("expireMT_" + key, compt);
				} else {
					compt++;
					expiredMap.put(key, compt);
				}
			}
        }
	}
}
