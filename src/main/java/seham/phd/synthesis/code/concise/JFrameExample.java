package seham.phd.synthesis.code.concise;

import javax.swing.JFrame;		

import seham.phd.synthesis.annotations.*;

public class JFrameExample {
	
	public static int ONE_BILLION = 1000000000;
	public static int TWO_BILLION = 2_000_000_000;

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
