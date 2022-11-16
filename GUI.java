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

		BoxLayout panelLayout = new BoxLayout(dayPanel, BoxLayout.Y_AXIS);
		BoxLayout conlayout = new BoxLayout(container, BoxLayout.X_AXIS);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		dayPanel.setLayout(panelLayout);
		container.setLayout(conlayout);
		addNewComponent(dayPanel);	
		container.add(dayPanel);


		frame.add(container);
		frame.setVisible(true);
	}
	
	static void addNewComponent(JPanel panel) {

		String[] arrDays = Calender.weekdays();
		String[] arrDate = Calender.date();
        
		//Skapar 7 olika dagar med alla komponenter de behöver
		for (int i = 0; i <= 6; i++) {
			
			JPanel pnlDay = new JPanel();
			JLabel lblDayName = new JLabel(arrDays[i]);
			JLabel lblDate = new JLabel(arrDate[i]);
			JPanel pnlTask = new JPanel();
			JButton btn = new JButton("Add event");
			
			//Sätter layout på dags panelerna 
	        pnlDay.setLayout(new BoxLayout(pnlDay, BoxLayout.Y_AXIS));
	        //anger hur rundade kanter man vill ha på sina hörn
	        pnlDay.setBorder(new RoundedBorder(25));
			pnlTask.setLayout(new BoxLayout(pnlTask, BoxLayout.Y_AXIS));
	
			pnlTask.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));
			lblDayName.setAlignmentX(Component.CENTER_ALIGNMENT);
			lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);
			pnlTask.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			pnlDay.add(lblDayName);
			pnlDay.add(lblDate);
			pnlDay.add(pnlTask);
			pnlDay.add(btn);
			highlight(pnlDay, pnlTask, i);
			container.add(pnlDay);
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//Skapar ett pop-up fönster med ett info meddelande 
					String event = JOptionPane.showInputDialog(null, "Add event", "Event", JOptionPane.INFORMATION_MESSAGE);
					JButton remove = new JButton("X");
					JLabel task = new JLabel(event);
					
					remove.setAlignmentX(Component.CENTER_ALIGNMENT);
					task.setAlignmentX(Component.CENTER_ALIGNMENT);
					
					pnlTask.add(task);
					pnlTask.add(remove);
					pnlDay.revalidate();
					
					remove.addActionListener(new ActionListener(){
						//Tar bort en task när man klickat på "X"
						public void actionPerformed(ActionEvent e) {
		                    pnlTask.remove(task);
		                    pnlTask.remove(remove);
		                    pnlTask.repaint();
		                }
		            });
				}
			});
		}
	}
	//Ger dagens datum en higlight med annan färg 
	
	static void highlight(JPanel pnl, JPanel newPnl, int i){
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_WEEK);
		
		if(i == today - 2) {
			pnl.setBackground(new Color(207, 240, 204));
			newPnl.setBackground(new Color(207, 240, 204));
		}
	}
	
	//Rundar hörnen på panelerna 
	
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
