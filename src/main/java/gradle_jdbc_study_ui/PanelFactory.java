package gradle_jdbc_study_ui;

import javax.swing.JPanel;

import gradle_jdbc_study_ui_info.PanelDepartment;
import gradle_jdbc_study_ui_info.PanelEmloyee;
import gradle_jdbc_study_ui_info.PanelTitle;

public class PanelFactory {

	public static JPanel getPanelItem(UItype type) {
		JPanel panelInfo = null;
		
		if (type == UItype.TITLE) {
			panelInfo = new PanelTitle();
			panelInfo.setBounds(113, 5, 306, 101);
			
			
		}
		if (type == UItype.EMPLOYEE) {
			panelInfo = new PanelEmloyee();
			panelInfo.setBounds(113, 5, 306, 101);
			
		}
		if (type == UItype.DEPARTMENT) {
			panelInfo = new PanelDepartment();
			panelInfo.setBounds(113, 5, 306, 101);
			
		}
		return panelInfo;
	}

}
