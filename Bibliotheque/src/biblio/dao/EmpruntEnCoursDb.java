package biblio.dao;

public class EmpruntEnCoursDb {
	private int idUtil;
	private int idExemp;
	
	public EmpruntEnCoursDb(int idU,int idE) {
		setIdExemp(idE);
		setIdUtil(idU);		
	}

	
	public int getIdUtil() {
		return idUtil;
	}

	public void setIdUtil(int idUtil) {
		this.idUtil = idUtil;
	}

	public int getIdExemp() {
		return idExemp;
	}

	public void setIdExemp(int idExemp) {
		this.idExemp = idExemp;
	}

	
}
