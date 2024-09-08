package ma.iam.dppi.fon.interne.services.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.TraceConnexion;
import ma.iam.dppi.fon.communs.repository.TraceConnexionRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.dtos.CriteriaTraceConnexDto;
import ma.iam.dppi.fon.interne.dtos.TraceConnexDto;
import ma.iam.dppi.fon.interne.mappers.MapperTraceConnexion;
import ma.iam.dppi.fon.interne.services.ITracabiliteConnexionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class TracabiliteConnexionServiceImpl implements ITracabiliteConnexionService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TraceConnexionRepository traceConnexionDaoImpl;
	
	@Autowired
	private MapperTraceConnexion mapperTraceConnexion;
	
	/**
	 * @throws ParseException 
	 */
	@Override
	public List<TraceConnexDto> getAllTraceConnexionByCriteres(CriteriaTraceConnexDto critDto) throws ParseException{
		List<TraceConnexDto> dtos = new ArrayList<>();
		
		ActionCode actionCode = ActionCode.findById(critDto.getType());
		String code = "";
		if(actionCode != null) {
			code = actionCode.getName();
		}
		code = "".equals(code) ? null : code;
		String projet = "".equals(critDto.getProjet()) ? null : critDto.getProjet();
		
		Pageable pageable = PageRequest.of(critDto.getStart(), critDto.getEnd(), Direction.DESC, "idt");
		
		List<TraceConnexion> domains = null;
		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) 
				&& critDto.getDateFin() != null && !"".equals(critDto.getDateFin())) {
			
			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);
			
			domains = traceConnexionDaoImpl.getTraceConnexionsByCriteresDates(code,
					projet, date1, date2, pageable);
			
		} else {
			domains = traceConnexionDaoImpl.getTraceConnexionsByCriteres(code,
					projet, pageable);
		}
		
		if(domains != null && !domains.isEmpty()) {
			dtos = mapperTraceConnexion.toDtos(domains);
		}
		
		return dtos;
	}
	
	/**
	 * COUNT TRACE CONNEXION BY CRITERES
	 * @throws ParseException 
	 */
	@Override
	public Long getTotalTraceConnexionByCriteres(CriteriaTraceConnexDto critDto) throws ParseException {
		ActionCode actionCode = ActionCode.findById(critDto.getType());
		String code = "";
		if(actionCode != null) {
			code = actionCode.getName();
		}
		code = "".equals(code) ? null : code;
		String projet = "".equals(critDto.getProjet()) ? null : critDto.getProjet();
		long total = 0;
		
		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) 
				&& critDto.getDateFin() != null && !"".equals(critDto.getDateFin())) {
			
			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			SimpleDateFormat englishFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);
			String dateFrom = englishFormat.format(date1);
			String dateTo = englishFormat.format(date2);
			
			total = traceConnexionDaoImpl.countListTraceConnexionByCriteresDates(code, projet, dateFrom, dateTo);
			
		} else {
			total = traceConnexionDaoImpl.countListTraceConnexionByCriteres(code, projet);
		}
		
		return total;
	}
	
}
