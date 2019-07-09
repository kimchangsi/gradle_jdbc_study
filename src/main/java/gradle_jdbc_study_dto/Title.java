package gradle_jdbc_study_dto;

public class Title {
	private int titNo;
	private String titName;

	public Title(int titno) {
		super();
		this.titNo = titno;
	}

	public Title(int titno, String titname) {
		super();
		this.titNo = titno;
		this.titName = titname;
	}

	public int getTitno() {
		return titNo;
	}

	public void setTitno(int titno) {
		this.titNo = titno;
	}

	public String getTitname() {
		return titName;
	}

	public void setTitname(String titname) {
		this.titName = titname;
	}

	@Override
	public String toString() {
		return String.format("%s", titName);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + titNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		if (titNo != other.titNo)
			return false;
		return true;
	}

	public Object[] toArray() {
		
		return new Object[]{String.format("T%03d", titNo), titName};
	}
	
	

}
