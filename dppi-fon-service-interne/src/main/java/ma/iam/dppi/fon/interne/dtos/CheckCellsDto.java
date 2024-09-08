package ma.iam.dppi.fon.interne.dtos;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class CheckCellsDto {

	private int rowCells;
	private String errorMessage;
	private String value;
	private boolean isEmpty;

	public int getRowCells() {
		return rowCells;
	}

	public void setRowCells(int rowCells) {
		this.rowCells = rowCells;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

}
