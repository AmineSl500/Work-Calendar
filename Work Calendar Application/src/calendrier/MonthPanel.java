
package calendrier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MonthPanel extends JPanel {
  int idGroupe;
  int month;
  int year;
  JFrame d;
  protected String[] monthNames = { "Janvier", "Fevrier", "Mars", "Avril",
      "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre",
      "Decembre" };

  protected String[] dayNames = { "Dim", "Lun", "Mar", "Mer", "Jeu", "Ven", "Sam" };
  JButton Precedant=new JButton("<");
    
  JButton Suivant=new JButton(">");

   MonthPanel(int month, int year,int C) {
    //System.out.println(C);
    this.idGroupe = C;
    this.month = month;
    this.year = year;
    JPanel monthPanel = new JPanel(true);
    monthPanel.setLayout(new BorderLayout());
    monthPanel.add(createTitleGUI(this), BorderLayout.NORTH);
    monthPanel.add(createDaysGUI(this), BorderLayout.SOUTH);
    this.add(monthPanel);
    
  
}
   protected JPanel createTitleGUI(MonthPanel o){
    JPanel titlePanel = new JPanel(true);
    titlePanel.setLayout(new FlowLayout());
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setPreferredSize(new Dimension(1000,50));
    JLabel label = new JLabel("   "+monthNames[month] + " " + year+"    ");
    label.setForeground(Color.BLACK);
    Precedant.addActionListener(new BoutonListener(this.idGroupe));
    Suivant.addActionListener(new Bouton2Listener(this.idGroupe));
    titlePanel.add(Precedant);
    titlePanel.add(label, BorderLayout.CENTER);
    titlePanel.add(Suivant);
    return titlePanel;
  }

  protected JPanel createDaysGUI(MonthPanel o) {
    JPanel dayPanel = new JPanel(true);
    
    dayPanel.setLayout(new GridLayout(0, dayNames.length));
    dayPanel.setPreferredSize(new Dimension(1000,600));
    Calendar today = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.DAY_OF_MONTH, 1);

    Calendar iterator = (Calendar) calendar.clone();
    iterator.add(Calendar.DAY_OF_MONTH,
        -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

    Calendar maximum = (Calendar) calendar.clone();
    maximum.add(Calendar.MONTH, +1);

    for (int i = 0; i < dayNames.length; i++) {
      JPanel dPanel = new JPanel(true);
      dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      JLabel dLabel = new JLabel(dayNames[i]);
      dPanel.add(dLabel);
      dPanel.setBackground(Color.ORANGE);
      dayPanel.add(dPanel);
    }

    int count = 0;
    int limit = dayNames.length * 6;

    while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
      int lMonth = iterator.get(Calendar.MONTH);
      int lYear = iterator.get(Calendar.YEAR);

      JPanel dPanel = new JPanel(true);
      dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      JLabel dayLabel = new JLabel();
      JButton b=new JButton();
      b.setSize(new Dimension(dPanel.getWidth(),dPanel.getHeight()));
      if ((lMonth == month) && (lYear == year)) {
        int lDay = iterator.get(Calendar.DAY_OF_MONTH);
        b.setText(Integer.toString(lDay));
        Bouton3Listener B3 = new Bouton3Listener(lDay);
        b.addActionListener(B3);
        dPanel.add(b);
      } 
      else{
          dPanel.add(dayLabel);
      }
      dayPanel.add(dPanel);
      iterator.add(Calendar.DAY_OF_YEAR, +1);
      count++;
    }

    for (int i = count; i < limit; i++) {
      JPanel dPanel = new JPanel(true);
      dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      dPanel.add(new JLabel());
      dayPanel.add(dPanel);
    }
    return dayPanel;
  }
   class BoutonListener implements ActionListener{

        int idGroupe;
        public BoutonListener(int c)
        {
        this.idGroupe = c;
        }
        public void actionPerformed(ActionEvent e) {
            //d.dispose();
            if(month==0){
                
                Design d=new Design(11,year-1,this.idGroupe);
            }
            else{
                //month = month-1;
                Design d=new Design(month-1,year,this.idGroupe);
            }
        }
       
   }
   class Bouton2Listener implements ActionListener{
       int idGroupe;
       public Bouton2Listener(int c)
       {
       this.idGroupe = c;
       }
        public void actionPerformed(ActionEvent e) {
            //d.dispose();
            if(month==11){
                
                Design d=new Design(0,year+1,this.idGroupe);
            }
            else{
                
                Design d=new Design(month+1,year,this.idGroupe);
            }
        }
       
   }
   class Bouton3Listener implements ActionListener{
        int day;
        public Bouton3Listener(int d){
        day = d;
        }
        public void actionPerformed(ActionEvent e) {
            firstpage l = new firstpage(this.day,month+1,year,idGroupe);
            l.setVisible(true);
        }
       
   }
  }

