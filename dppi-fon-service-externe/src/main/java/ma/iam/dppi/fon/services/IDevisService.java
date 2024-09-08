package ma.iam.dppi.fon.services;

import java.text.ParseException;
import java.util.List;

import ma.iam.dppi.fon.domain.Devis;
import ma.iam.dppi.fon.dtos.DevisDto;

public interface IDevisService {

	public DevisDto saveDevis(DevisDto devisDto) throws ParseException;
	
	public Boolean devisExistans(Long idtDemande);
	
	public List<DevisDto> getDevisByDemande(Long idtDemande);

	Devis getDevisById(Long idtDevis);
}
