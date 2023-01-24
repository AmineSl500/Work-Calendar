
package calendrier;
import java.awt.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.ArrayList;

public class Design extends JFrame{
    int mois;
    int annee;
    int idGroupe;
    public Design(int mois,int annee,int c){
        this.setTitle("Calendrier");
        this.setSize(new Dimension(1000,600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mois=mois;
        this.annee=annee;
        this.idGroupe = c;
        MonthPanel m = new MonthPanel(this.mois,this.annee,c);
        m.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
        this.setResizable(false);
        this.setContentPane(m);
        this.setVisible(true);
        
    }
    
}
