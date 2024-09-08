package ma.iam.dppi.fon.interne.services;

import java.util.List;

import ma.iam.dppi.fon.interne.dtos.ParametreBoDto;
import ma.iam.dppi.fon.interne.dtos.ParametresDto;
import ma.iam.dppi.fon.interne.dtos.ParametresStrDto;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public interface IParametrageService {
	ParametresDto getAllParameters();
	List<ParametreBoDto> getParameterByCode(String code);
	Long changeParameter(ParametreBoDto parametreBoDto);
	Long changeParameters(ParametresStrDto parameters);
	Long resetToDefaultSettings();
}
