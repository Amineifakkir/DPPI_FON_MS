package ma.iam.dppi.fon.interne.services;

import java.text.ParseException;
import java.util.List;

import ma.iam.dppi.fon.interne.dtos.CriteriaTraceConnexDto;
import ma.iam.dppi.fon.interne.dtos.TraceConnexDto;


/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface ITracabiliteConnexionService {
	
	List<TraceConnexDto> getAllTraceConnexionByCriteres(CriteriaTraceConnexDto critDto) throws ParseException;
	Long getTotalTraceConnexionByCriteres(CriteriaTraceConnexDto critDto) throws ParseException;
}
