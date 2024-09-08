package ma.iam.dppi.fon.constants;


/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public enum ActionCode {

	CONNEXION(1, "cnx"), DECONNEXION(2, "dcnx"), TENTATIVE_CONNEXION(3, "tcnx"),
	ADD(4, "add"), MODIF(5, "modif"), DELETE(6, "del"), CONSULTATION(7, "cnsl"),
	UPLOAD(8, "chargement"), EXPORT(9, "export"), DOWNLOAD(10, "download")
	;
	
	private int id;
	private String name;

	private ActionCode(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public static ActionCode findById(Integer id) {
		if (id == null) {
			return null;
		}
		for (ActionCode item : values()) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}
}
