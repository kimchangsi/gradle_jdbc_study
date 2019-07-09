package gradle_jdbc_study_ui_info;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;
import gradle_jdbc_study_dto.Department;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelDepartment extends JPanel {
	private JTextField tfTDeptNo;
	private JTextField tfDeptName;
	private JTextField tfFloor;
	private Department nextDept;
	private Department searchNo;

	public void setSearchNo(Department searchNo) {
		this.searchNo = searchNo;
	}

	public PanelDepartment() {
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblDeptNo = new JLabel("\uBC88\uD638");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeptNo.setPreferredSize(new Dimension(36, 15));
		lblDeptNo.setMinimumSize(new Dimension(36, 15));
		lblDeptNo.setMaximumSize(new Dimension(36, 15));
		add(lblDeptNo);

		tfTDeptNo = new JTextField();
		add(tfTDeptNo);
		tfTDeptNo.setColumns(20);

		JLabel lblDeptName = new JLabel("\uBD80\uC11C\uBA85");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblDeptName);

		tfDeptName = new JTextField();
		add(tfDeptName);
		tfDeptName.setColumns(20);

		JLabel lblFloor = new JLabel("\uC704\uCE58");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFloor.setMaximumSize(new Dimension(36, 15));
		lblFloor.setMinimumSize(new Dimension(36, 15));
		lblFloor.setPreferredSize(new Dimension(36, 15));
		add(lblFloor);

		tfFloor = new JTextField();
		add(tfFloor);
		tfFloor.setColumns(20);

	}

	public void setTf(List<Department> list) {
		if (list.size() == 0) {
			int num = 1;
			tfTDeptNo.setText(String.format("D%03d", num));
		} else {
			nextDept = list.get(list.size() - 1);
			tfTDeptNo.setText(String.format("D%03d", nextDept.getDeptNo() + 1));
		}

		tfTDeptNo.setEditable(false);
		tfDeptName.setText("");
		tfFloor.setText("");
	}

	public void setTf(Department d) {
		tfTDeptNo.setText(String.format("D%03d", d.getDeptNo()));
		tfTDeptNo.setEditable(false);
		tfDeptName.setText(d.getDeptName());
		tfFloor.setText(String.valueOf(d.getFloor()));
	}

	public Department getTf() {
		int deptNo = 0;
		try {
			deptNo = nextDept.getDeptNo() + 1;
		} catch (NullPointerException e) {
			deptNo = 1;
		}
		String deptName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, deptName, floor);
	}

	public Department getTfUpdate() {
		int deptNo = searchNo.getDeptNo();
		String titName = tfDeptName.getText().trim();
		int floor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(deptNo, titName, floor);
	}

}
