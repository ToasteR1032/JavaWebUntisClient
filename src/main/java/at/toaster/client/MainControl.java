package at.toaster.client;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.JSONException;

import at.toaster.client.data.WebUntisConnector;

@SuppressWarnings("unused")
public class MainControl implements ActionListener, PropertyChangeListener{
	private MainModel mModel;
	private MainView mView;
	
	private WebUntisConnector conn;
	
	public MainControl() {
		
		conn = null;
		
		try {
			this.conn = new WebUntisConnector("https://stpl.tgm.ac.at/WebUntis/",
					"tgm", "rhollander", JOptionPane.showInputDialog(null, "Passwort fuer rhollander"));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.mModel = new MainModel();
		this.mView = new MainView(mModel, this, conn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		try {
		mView.refresh();
		} catch (Exception e) {}
	}

}
