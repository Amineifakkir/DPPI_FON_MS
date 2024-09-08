package ma.iam.dppi.fon.interne.services.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.iam.dppi.fon.communs.domain.TraceOperation;
import ma.iam.dppi.fon.communs.repository.TraceOperationRepository;
import ma.iam.dppi.fon.constants.ActionCode;
import ma.iam.dppi.fon.interne.dtos.CriteriaTraceConnexDto;
import ma.iam.dppi.fon.interne.dtos.TraceOperationDto;
import ma.iam.dppi.fon.interne.mappers.MapperTraceOperation;
import ma.iam.dppi.fon.interne.services.ITracabiliteOperationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
@Service
@Transactional
public class TracabiliteOperationServiceImpl implements ITracabiliteOperationService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TraceOperationRepository traceDaoImpl;
	
	@Autowired
	private MapperTraceOperation mapperTraceOperation;
	
	
	@Override
	public List<TraceOperationDto> getAllTraceOperationByCriteres(CriteriaTraceConnexDto critDto) throws ParseException{
		List<TraceOperationDto> dtos = new ArrayList<>();
		ActionCode actionCode = ActionCode.findById(critDto.getType());
		String code = "";
		if(actionCode != null) {
			code = actionCode.getName();
		}
		code = "".equals(code) ? null : code;
		String projet = "".equals(critDto.getProjet()) ? null : critDto.getProjet();
		
		Sort sort = Sort.by(Sort.Direction.DESC, "idt");
		Pageable pageable = PageRequest.of(critDto.getStart(), critDto.getEnd(), sort);
		List<TraceOperation> domains = null;
		if (critDto.getDateDebut() != null && !"".equals(critDto.getDateDebut()) 
				&& critDto.getDateFin() != null && !"".equals(critDto.getDateFin())) {
			
			String dDebut = critDto.getDateDebut() + " 00:00:00";
			String dFin = critDto.getDateFin() + " 23:59:00";
			SimpleDateFormat frenchFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date1 = frenchFormat.parse(dDebut);
			Date date2 = frenchFormat.parse(dFin);
			
			domains = traceDaoImpl.getListTraceOperationByCriteresDates(code,
					projet, date1, date2, pageable);
			
		} else {
			domains = traceDaoImpl.getListTraceOperationByCriteres(code,
					projet, pageable);
		}
		
		if(domains != null && !domains.isEmpty()) {
			dtos = mapperTraceOperation.toDtos(domains);
		}
		
		return dtos;
	}
	
	/**
	 * COUNT TRACE CONNEXION BY CRITERES
	 * @throws ParseException 
	 */
	@Override
	public Long getTotalTraceOperationByCriteres(CriteriaTraceConnexDto critDto) throws ParseException {
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
			
			total = traceDaoImpl.countListTraceOperationByCriteresDates(code, projet, dateFrom, dateTo);
			
		} else {
			total = traceDaoImpl.countListTraceOperationByCriteres(code, projet);
		}
		
		return total;
	}
	
}
