package ma.iam.dppi.fon.domain;

/**
 * 
 * @author Z.BELGHAOUTI
 *
 */
public enum ImportType {
	COMMANDESM(1, "CommandeSM"),
	;
	
	private int id;
	private String name;

	private ImportType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public static ImportType findById(Integer id) {
		if (id == null) {
			return null;
		} else {
			ImportType[] var1 = values();
			int var2 = var1.length;

			for (int var3 = 0; var3 < var2; ++var3) {
				ImportType item = var1[var3];
				if (item.getId() == id) {
					return item;
				}
			}

			return null;
		}
	}

	public String getName() {
		return this.name;
	}


}
