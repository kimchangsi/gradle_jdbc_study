package gradle_jdbc_study_ui_info;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;


import gradle_jdbc_study_dto.Department;
import gradle_jdbc_study_dto.Employee;
import gradle_jdbc_study_dto.Title;

@SuppressWarnings("serial")
public class PanelEmloyee extends JPanel {
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfHireDate;
	private JRadioButton rdbGender1;
	private JRadioButton rdbGender2;
	private JComboBox<Title> cmbTname;
	private JComboBox<Department> cmDno;
	private DefaultComboBoxModel<Title> empCmbModel;
	private DefaultComboBoxModel<Department> deptCmbModel;
	private Employee nextEmp;
	private JLabel lblEmpNo;
	private JLabel lblEmpName;
	private JLabel lblTname;
	private JLabel lblSalary;
	private JSpinner spSalary;
	private JPanel panelGender;
	private JLabel lblGender;
	private JLabel lblDno;
	private JLabel lblHireDate;
	private Employee searchNo;
	private SpinnerNumberModel numberModel;

	public void setSearchNo(Employee searchNo) {
		this.searchNo = searchNo;
	}

	public PanelEmloyee() {
		setLayout(new GridLayout(0, 2, 10, 10));

		lblEmpNo = new JLabel("\uBC88\uD638");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpNo);

		tfEmpNo = new JTextField();
		add(tfEmpNo);
		tfEmpNo.setColumns(20);

		lblEmpName = new JLabel("\uC0AC\uC6D0\uBA85");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEmpName);

		tfEmpName = new JTextField();
		add(tfEmpName);
		tfEmpName.setColumns(20);

		lblTname = new JLabel("\uC9C1\uCC45");
		lblTname.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTname);

		cmbTname = new JComboBox<Title>();
		cmbTname.setPreferredSize(new Dimension(225, 21));
		cmbTname.setMinimumSize(new Dimension(100, 100));
		add(cmbTname);

		lblSalary = new JLabel("\uAE09\uC5EC");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSalary);

		numberModel = new SpinnerNumberModel(1500000, 1000000, 5000000, 100000);
		spSalary = new JSpinner(numberModel);
		spSalary.setPreferredSize(new Dimension(225, 22));
		add(spSalary);

		lblGender = new JLabel("\uC131\uBCC4");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblGender);

		panelGender = new JPanel();
		add(panelGender);

		rdbGender1 = new JRadioButton("\uB0A8\uC790");
		panelGender.add(rdbGender1);
		buttonGroup.add(rdbGender1);
		rdbGender1.setPreferredSize(new Dimension(60, 23));

		rdbGender2 = new JRadioButton("\uC5EC\uC790");
		panelGender.add(rdbGender2);
		buttonGroup.add(rdbGender2);
		rdbGender2.setPreferredSize(new Dimension(60, 23));

		lblDno = new JLabel("\uBD80\uC11C");
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDno);

		cmDno = new JComboBox<Department>();
		add(cmDno);

		lblHireDate = new JLabel("\uC785\uC0AC\uC77C");
		lblHireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblHireDate);

		tfHireDate = new JTextField();
		add(tfHireDate);
		tfHireDate.setColumns(10);

	}

	public void setTf(List<Employee> list) {
		Date today = new Date();
		SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd");
		if (list.size() == 0) {
			int num = 1;
			tfEmpNo.setText(String.format("E%s%03d", t.format(today).substring(1, 4), num));
		} else {
			nextEmp = list.get(list.size() - 1);
			String d = nextEmp.getHiredate().toString();
			tfEmpNo.setText(String.format("E%s%03d", d.substring(1, 4), nextEmp.getEmpNo() + 1));
		}
		tfEmpNo.setEditable(false);
		tfEmpName.setText("");
		spSalary.setValue(1500000);
		;
		tfHireDate.setText(t.format(today));
		rdbGender1.setSelected(true);
		cmbTname.setSelectedIndex(-1);
		cmDno.setSelectedIndex(-1);

	}

	public void setTf(Employee e) {
		String d = e.getHiredate().toString();
		tfEmpNo.setText(String.format("E%s%03d", d.substring(1, 4), e.getEmpNo()));
		tfEmpNo.setEditable(false);
		tfEmpName.setText(e.getEmpName());
		cmbTname.setSelectedItem(e.getTno());
		spSalary.setValue(e.getSalary());
		if (e.getGender().equals("남자")) {
			rdbGender1.setSelected(true);
		} else if (e.getGender().equals("여자")) {
			rdbGender2.setSelected(true);
		}
		cmDno.setSelectedItem(e.getDno());

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Date hDate = e.getHiredate();
		tfHireDate.setText(s.format(hDate));

	}

	public void setCmbManager(List<Department> deptItem, List<Title> empItem) {
		deptCmbModel = new DefaultComboBoxModel<Department>(new Vector<Department>(deptItem));
		cmDno.setModel(deptCmbModel);
		empCmbModel = new DefaultComboBoxModel<Title>(new Vector<Title>(empItem));
		cmbTname.setModel(empCmbModel);
		cmbTname.setSelectedIndex(-1);
		cmDno.setSelectedIndex(-1);
	}

	public Employee getTf() {
		int empNo = 0;
		try {
			empNo = nextEmp.getEmpNo() + 1;
		} catch (NullPointerException e) {
			empNo = 1;
		}

		String empName = tfEmpName.getText().trim();
		Title tno = (Title) cmbTname.getSelectedItem();
		int salary = (int) spSalary.getValue();
		String gender = "";
		if (rdbGender1.isSelected()) {
			gender = rdbGender1.getText();
		}

		if (rdbGender2.isSelected()) {
			gender = rdbGender2.getText();
		}

		Department dno = (Department) cmDno.getSelectedItem();
		String hireDate = tfHireDate.getText().trim();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(hireDate);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "날짜 정확히 입력");
		}

		return new Employee(empNo, empName, tno, salary, gender, dno, date);
	}

	public Employee getTfUpdate() {
		int empNo = searchNo.getEmpNo();
		String empName = tfEmpName.getText().trim();
		Title tno = (Title) cmbTname.getSelectedItem();
		int salary = (int) spSalary.getValue();
		String gender = "";
		if (rdbGender1.isSelected()) {
			gender = rdbGender1.getText();
		}

		if (rdbGender2.isSelected()) {
			gender = rdbGender2.getText();
		}

		Department dno = (Department) cmDno.getSelectedItem();
		String hireDate = tfHireDate.getText().trim();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(hireDate);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "날짜 정확히 입력");
		}

		return new Employee(empNo, empName, tno, salary, gender, dno, date);
	}

}
