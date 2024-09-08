package ma.iam.dppi.fon.services;

import java.io.FileNotFoundException;



import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import ma.iam.dppi.fon.dtos.HistoryDto;
import ma.iam.dppi.fon.exception.AppException;
/**
 * @author K.ELBAGUARI
 *
 */
public interface IImportDemandeService {

	ResponseEntity<HistoryDto> importCommandesSMFile(MultipartFile file);

	Resource loadFileAsResource(Long historyId, Boolean isLog) throws FileNotFoundException, AppException;

}
