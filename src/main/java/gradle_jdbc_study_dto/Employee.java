package gradle_jdbc_study_dto;

import java.util.Date;

public class Employee {
	private int empNo;
	private String empName;
	private Title tno;
	private int salary;
	private String gender;
	private Department dno;
	private Date hireDate;

	public Employee(int empNo) {
		super();
		this.empNo = empNo;
	}

	public Employee(int empNo, String empName, Title tno, int salary, String gender, Department dno,
			Date hireDate) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.tno = tno;
		this.salary = salary;
		this.gender = gender;
		this.dno = dno;
		this.hireDate = hireDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Title getTno() {
		return tno;
	}

	public void setTno(Title tno) {
		this.tno = tno;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public Date getHiredate() {
		return hireDate;
	}

	public void setHiredate(Date hiredate) {
		this.hireDate = hiredate;
	}

	@Override
	public String toString() {
		return tno.getTitname();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNo;
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
		Employee other = (Employee) obj;
		if (empNo != other.empNo)
			return false;
		return true;
	}
	
	public Object[] toArray() {
		String dstr = hireDate.toString();
		return new Object[]{String.format("E%s%03d",dstr.substring(1, 4), empNo), empName, tno.getTitname(),String.format("%,d", salary),gender,String.format("%s(%d층)", dno.getDeptName(),dno.getFloor()),hireDate};
	}

}
