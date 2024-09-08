package ma.iam.dppi.fon.interne.dtos;

import java.util.List;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public class HistoriqueTronconDto {

	private List<TraceCommentaireSousLiaisonDto> listTraceCommentaire;
	private List<TraceEtatSousLiaisonDto> listTraceEtat;

	public List<TraceCommentaireSousLiaisonDto> getListTraceCommentaire() {
		return listTraceCommentaire;
	}

	public void setListTraceCommentaire(
			List<TraceCommentaireSousLiaisonDto> listTraceCommentaire) {
		this.listTraceCommentaire = listTraceCommentaire;
	}

	public List<TraceEtatSousLiaisonDto> getListTraceEtat() {
		return listTraceEtat;
	}

	public void setListTraceEtat(List<TraceEtatSousLiaisonDto> listTraceEtat) {
		this.listTraceEtat = listTraceEtat;
	}

}
