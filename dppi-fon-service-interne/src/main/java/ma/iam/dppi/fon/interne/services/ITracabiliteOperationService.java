package ma.iam.dppi.fon.interne.services;

import java.text.ParseException;
import java.util.List;

import ma.iam.dppi.fon.interne.dtos.CriteriaTraceConnexDto;
import ma.iam.dppi.fon.interne.dtos.TraceOperationDto;


/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface ITracabiliteOperationService {
	
	List<TraceOperationDto> getAllTraceOperationByCriteres(CriteriaTraceConnexDto critDto) throws ParseException;
	Long getTotalTraceOperationByCriteres(CriteriaTraceConnexDto critDto) throws ParseException;
}
