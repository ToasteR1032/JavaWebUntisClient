package at.toaster.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class MainControl implements ActionListener{
	private MainModel mModel;
	private MainView mView;
	
	public MainControl() {
		this.mModel = new MainModel();
		this.mView = new MainView(mModel, this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
