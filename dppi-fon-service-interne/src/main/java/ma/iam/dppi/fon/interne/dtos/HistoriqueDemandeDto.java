package ma.iam.dppi.fon.interne.dtos;

import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class HistoriqueDemandeDto {

	private List<TraceCommentaireDto> listTraceCommentaire;
	private List<InteractionDto> listInteraction;
	private List<TraceEtatDto> listTraceEtat;

	public List<TraceCommentaireDto> getListTraceCommentaire() {
		return listTraceCommentaire;
	}

	public void setListTraceCommentaire(
			List<TraceCommentaireDto> listTraceCommentaire) {
		this.listTraceCommentaire = listTraceCommentaire;
	}

	public List<InteractionDto> getListInteraction() {
		return listInteraction;
	}

	public void setListInteraction(List<InteractionDto> listInteraction) {
		this.listInteraction = listInteraction;
	}

	public List<TraceEtatDto> getListTraceEtat() {
		return listTraceEtat;
	}

	public void setListTraceEtat(List<TraceEtatDto> listTraceEtat) {
		this.listTraceEtat = listTraceEtat;
	}

}
