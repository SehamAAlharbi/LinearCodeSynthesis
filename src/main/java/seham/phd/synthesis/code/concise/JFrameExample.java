package seham.phd.synthesis.code.concise;

import javax.swing.JFrame;			

import seham.phd.synthesis.annotations.*;

public class JFrameExample {

	@Documentation
	public void docFrameWithoutTitle() {
		JFrame frame = createJFrame(false);
		show(frame);
		System.out.println();

	}

	@Documentation
	public void docFrameWithTitle() {
		JFrame frame = createJFrame(true);
		show(frame);
	}

	@Utility
	public JFrame createJFrame(boolean withTitle) {
		JFrame frame = new JFrame();
		if (withTitle)
			frame.setTitle("App");
		return frame;
	}

	@Utility
	public void show(JFrame frame) {
		frame.setBounds(100, 100, 200, 200);
		frame.setVisible(true);
	}

}
