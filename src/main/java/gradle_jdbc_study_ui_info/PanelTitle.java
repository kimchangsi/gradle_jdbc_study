package gradle_jdbc_study_ui_info;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;


import gradle_jdbc_study_dto.Title;

@SuppressWarnings("serial")
public class PanelTitle extends JPanel {
	private JTextField tfTitNo;
	private JTextField tfTitName;
	private Title nextTit;
	private Title searchNo;

	public void setSearchNo(Title searchNo) {
		this.searchNo = searchNo;
	}

	public PanelTitle() {
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblTitNo = new JLabel("\uBC88\uD638");
		lblTitNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitNo.setPreferredSize(new Dimension(36, 15));
		lblTitNo.setMinimumSize(new Dimension(36, 15));
		lblTitNo.setMaximumSize(new Dimension(36, 15));
		add(lblTitNo);

		tfTitNo = new JTextField();
		add(tfTitNo);
		tfTitNo.setColumns(20);

		JLabel lblTitName = new JLabel("\uC9C1\uCC45\uBA85");
		lblTitName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTitName);

		tfTitName = new JTextField();
		add(tfTitName);
		tfTitName.setColumns(20);

	}

	public void setTf(List<Title> list) {
		if (list.size() == 0) {
			int num = 1;
			tfTitNo.setText(String.format("T%03d", num));
		} else {
			nextTit = list.get(list.size() - 1);
			tfTitNo.setText(String.format("T%03d", nextTit.getTitno() + 1));
		}

		tfTitNo.setEditable(false);
		tfTitName.setText("");
	}

	public void setTf(Title t) {
		tfTitNo.setText(String.format("T%03d", t.getTitno()));
		tfTitNo.setEditable(false);
		tfTitName.setText(t.getTitname());
	}

	public Title getTf() {
		int titNo = 0;
		try {
			titNo = nextTit.getTitno() + 1;
		} catch (NullPointerException e) {
			titNo = 1;
		}
		String titName = tfTitName.getText().trim();
		return new Title(titNo, titName);
	}

	public Title getTfUpdate() {
		int titNo = searchNo.getTitno();
		String titName = tfTitName.getText().trim();
		return new Title(titNo, titName);
	}

}
