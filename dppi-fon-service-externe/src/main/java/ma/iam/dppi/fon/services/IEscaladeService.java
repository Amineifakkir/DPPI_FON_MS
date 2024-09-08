package ma.iam.dppi.fon.services;

import java.text.ParseException;

import ma.iam.dppi.fon.dtos.EscaladeDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IEscaladeService {
	
	 EscaladeDto saveEscalade(EscaladeDto escaladeDto) throws ParseException;

}

