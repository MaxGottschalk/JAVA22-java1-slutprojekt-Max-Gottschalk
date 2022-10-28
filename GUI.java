package calender;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BoxLayout;

class GUI {

	private static JPanel container;

	static void createAndDisplay() {
		JFrame frame = new JFrame("Calender");
		frame.setSize(1100, 400);

		container = new JPanel();

		JPanel dayPanel = new JPanel();	

		BoxLayout pnlLayout = new BoxLayout(dayPanel, BoxLayout.Y_AXIS);
		BoxLayout conlayout = new BoxLayout(container, BoxLayout.X_AXIS);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dayPanel.setLayout(pnlLayout);
		container.setLayout(conlayout);
		addNewComponent(dayPanel);	
		container.add(dayPanel);


		frame.add(container);
		frame.setVisible(true);
	}

	static void addNewComponent(JPanel panel) {

		String[] arrDays = Calender.weekdays();
		String[] arrDate = Calender.date();
        
		for (int i = 0; i < 7; i++) {
			
			JPanel pnl = new JPanel();
			JLabel lblDayName = new JLabel(arrDays[i]);
			JLabel lblDate = new JLabel(arrDate[i]);
			JPanel newPanel = new JPanel();
			JButton btn = new JButton("Add event");
			
	        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
	        pnl.setBorder(new RoundedBorder(25));
			newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
	
			newPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
			lblDayName.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
			newPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			pnl.add(lblDayName);
			pnl.add(lblDate);
			pnl.add(newPanel);
			pnl.add(btn);
			highlight(pnl, newPanel, i);
			container.add(pnl);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String event = JOptionPane.showInputDialog(null, "Add event", "Event", JOptionPane.INFORMATION_MESSAGE);
					JButton remove = new JButton("X");
					JLabel task = new JLabel(event);
					
					remove.setAlignmentX(Component.CENTER_ALIGNMENT);
					task.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					newPanel.add(task);
					newPanel.add(remove);
					pnl.revalidate();
					
					remove.addActionListener(new ActionListener(){
						
						public void actionPerformed(ActionEvent e) {
		                    newPanel.remove(task);
		                    newPanel.remove(remove);
		                    newPanel.repaint();
		                }
		            });
				}
			});
		}
	}
	
	static void highlight(JPanel pnl, JPanel newPnl, int i){
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_WEEK);
		
		if(i == today - 2) {
			pnl.setBackground(new Color(207, 240, 204));
			newPnl.setBackground(new Color(207, 240, 204));
		}
	}
	
	private static class RoundedBorder implements Border {
        
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
}