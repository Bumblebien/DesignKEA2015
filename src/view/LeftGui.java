package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class LeftGui extends JPanel {

	private JLabel lDato, lStart, lSlut, lTimer, panelHeader, kolonLabel, kolonLabel2, samletTimerLabel, 
	lmtow, ltakeoff, ltakeofreduced, lopening, ldanmark, lCallsign;
	
	private JButton bTilfoejTid;
	private JComboBox<String> cbStartTime, cbStartKvarter, cbSlutTime, cbSlutKvarter, cbDatoDag, cbDatoMåned, cbDatoÅr, cbmtow;

	private JTextField tfCallsign;
	
	private String[] timer = { "00", "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23" };

	private String[] kvarter = { "00", "15", "30", "45" };

	private String[] dage = { "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
			"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
			"31" };

	private String[] maaneder = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	private String[] aar = new String[3];
	
	int rightwidthsize = 70;

	Calendar cal = Calendar.getInstance();

	public LeftGui() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 400));

		JPanel indtastNy = new JPanel(new MigLayout("center alignment"));
		add(indtastNy, BorderLayout.CENTER);

		// components rightpanel
		panelHeader = new JLabel("Overskrift", SwingConstants.CENTER);
		panelHeader.setFont(new Font("Verdana", Font.BOLD, 14));
		
		lCallsign = new JLabel("Callsign: ");
		tfCallsign = new JTextField(10);
		lmtow = new JLabel("MTOW: ");
		ltakeoff = new JLabel("Take-off: ");
		ltakeofreduced = new JLabel("Take-off 70%: ");
		lopening = new JLabel("Åbning: ");
		ldanmark = new JLabel("Indenrigs: ");
		lDato = new JLabel("Dato: ");
		lStart = new JLabel("Ankomst: ");
		lSlut = new JLabel("Afgang: ");
		lTimer = new JLabel("Timer: ");
		kolonLabel = new JLabel(":");
		kolonLabel2 = new JLabel(":");

		cbmtow = new JComboBox<String>();
		
		cbDatoDag = setupDateCombobox(dage, cal.get(Calendar.DAY_OF_MONTH) - 1, false);
		cbDatoMåned = setupDateCombobox(maaneder, cal.get(Calendar.MONTH), false);
		cbDatoÅr = setupDateCombobox(aar, 1, true);
		
		
		cbStartTime = setupTimeCombobox(timer, 7);
		cbStartKvarter = setupTimeCombobox(kvarter, 0);
		cbSlutTime = setupTimeCombobox(timer, 16);
		cbSlutKvarter = setupTimeCombobox(kvarter, 0);

		samletTimerLabel = new JLabel("0 timer, 0 minutter");

		// Button rightpanel
		bTilfoejTid = new JButton("Tilføj/Rediger");
		bTilfoejTid.setPreferredSize(new Dimension(190, 50));

		//Tilføjoverskrift
		add(panelHeader, BorderLayout.NORTH);
		//Tilføj til center
		indtastNy.add(lCallsign);
		indtastNy.add(tfCallsign, "wrap");
		indtastNy.add(lDato);
		indtastNy.add(cbDatoDag, "split 3");
		indtastNy.add(cbDatoMåned);
		indtastNy.add(cbDatoÅr, "wrap");
		indtastNy.add(lStart);
		indtastNy.add(cbStartTime, "split 3");
		indtastNy.add(kolonLabel);
		indtastNy.add(cbStartKvarter, "wrap");
		indtastNy.add(lSlut);
		indtastNy.add(cbSlutTime, "split 3");
		indtastNy.add(kolonLabel2);
		indtastNy.add(cbSlutKvarter, "wrap");
		indtastNy.add(lmtow);
		indtastNy.add(cbmtow, "wrap");
		indtastNy.add(ltakeoff, "wrap");
		indtastNy.add(ltakeofreduced, "wrap");
		indtastNy.add(lopening, "wrap");
		indtastNy.add(ldanmark, "wrap");
		indtastNy.add(lTimer);
		indtastNy.add(samletTimerLabel, "wrap");
		indtastNy.add(bTilfoejTid, "span, align center");
		revalidate();
	}
	
	private JComboBox<String> setupTimeCombobox(String[] data, int selectedIndex){
		JComboBox<String> cb = new JComboBox<String>(data);
		cb.setPreferredSize(new Dimension(rightwidthsize, 10));
		((JLabel) cb.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		return cb;
	}
	
	private JComboBox<String> setupDateCombobox(String[] data, int selectedIndex, boolean years){
		JComboBox<String> cb;
		if (years) {
			int thisYear = cal.get(Calendar.YEAR);;
			aar[0] = thisYear - 1 + "";
			aar[1] = thisYear + "";
			aar[2] = thisYear + 1 + "";
			cb = new JComboBox<String>(data);
		}else{
			cb = new JComboBox<String>(data);
		}
		cb.setSelectedIndex(selectedIndex);
		cb.setPreferredSize(new Dimension(43, 10));
		return cb;
	}

}
