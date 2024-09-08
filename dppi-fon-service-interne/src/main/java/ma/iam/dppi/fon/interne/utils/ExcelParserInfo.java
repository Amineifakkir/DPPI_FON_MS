package ma.iam.dppi.fon.interne.utils;

import ma.iam.dppi.fon.communs.domain.Utilisateur;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Z.BELGHAOUTI
 *
 */
public class ExcelParserInfo {

	private int index;

	private Row currentRow;

	private XSSFWorkbook workbook;

	private XSSFSheet sheet;

	private String login;

	private Utilisateur user;

	public int getIndex() {
		return index++;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public int getRowNum() {
		return currentRow == null ? 0 : currentRow.getRowNum() + 1;
	}

	public Row getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(Row currentRow) {
		this.currentRow = currentRow;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}
}
