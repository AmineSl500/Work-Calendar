/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendrier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Amouna
 */
public class ConnectDay {
    Map<Integer, Integer> MonthDays = new HashMap<>();
    
    private int idGroupe;
    private int day;
    private int month;
    private int year;
    private ArrayList<String> DroitT=new ArrayList<String>();
    private ArrayList<String> DroitM=new ArrayList<String>();
    private ArrayList<String> DroitR=new ArrayList<String>();
    private ArrayList<String> DroitTF=new ArrayList<String>();
    private ArrayList<String> DroitMF=new ArrayList<String>();
    private ArrayList<String> DroitRF=new ArrayList<String>();
    private ArrayList<String> idTache=new ArrayList<String>();
    private ArrayList<String> idReunion=new ArrayList<String>();
    private ArrayList<String> idMulti=new ArrayList<String>();
    private ArrayList<String> Tache=new ArrayList<String>();
    private ArrayList<String> TitreTache=new ArrayList<String>();
    private ArrayList<String> Heure=new ArrayList<String>();
    private ArrayList<String> TitreMulti=new ArrayList<String>();
    private ArrayList<String> Reunion =new ArrayList<String>();
    private ArrayList<String> TitreReunion =new ArrayList<String>();
    private ArrayList<String> HeureR=new ArrayList<String>();
    private ArrayList<String> ReunionID =new ArrayList<String>();
    private ArrayList<String> ID =new ArrayList<String>();
    private ArrayList<String> Multi =new ArrayList<String>();
    private ArrayList<String> HeureMulti =new ArrayList<String>();
    ConnectDay(int c,int d,int m,int y){
    
      MonthDays.put(1, 31);
      MonthDays.put(2, 30);
      MonthDays.put(3, 31);
      MonthDays.put(4, 30);
      MonthDays.put(5, 31);
      MonthDays.put(6, 30);
      MonthDays.put(7, 31);
      MonthDays.put(8, 31);
      MonthDays.put(9, 30);
      MonthDays.put(10, 31);
      MonthDays.put(11, 30);
      MonthDays.put(12, 31);
 try {
      Class.forName("org.postgresql.Driver");


      String url = "jdbc:postgresql://localhost:5432/postgres";
      String user = "postgres";
      String passwd = "azaro";
      Connection conn = DriverManager.getConnection(url, user, passwd);
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet result;
      
        
      //On récupère les MetaData
      
         
      
      this.year = y;
      this.month = m;
      this.day = d;
      this.idGroupe = c;
      
      String days = Integer.toString(this.day);
      if(this.day < 9)
      {
      days = "0" + days;
      }
      String months = Integer.toString(this.month);
      if(this.month < 9)
      {
      months = "0" + months;
      }
      String date = days + "/"+months+"/"+Integer.toString(this.year);
      result = state.executeQuery("SELECT * FROM \"Droit\" ");
     
      while(result.next()){ 
          if(String.valueOf(this.idGroupe).equals(result.getObject(3).toString()))
          {
          if(("0").equals(result.getObject(4).toString())&&("0").equals(result.getObject(5).toString()))
          {
              idMulti.add(result.getObject(6).toString());
              DroitM.add(result.getObject(2).toString());
          }
          if(("0").equals(result.getObject(5).toString())&&("0").equals(result.getObject(6).toString()))
          {
              idTache.add(result.getObject(4).toString());
              DroitT.add(result.getObject(2).toString());
          }
          if(("0").equals(result.getObject(4).toString())&&("0").equals(result.getObject(6).toString()))
          {
              //System.out.println("loool "+result.getObject(5).toString()+ " loooooool ");
              idReunion.add(result.getObject(5).toString());
              DroitR.add(result.getObject(2).toString());
          }      
          } 
          
          
         }
      result.close();
      result = state.executeQuery("SELECT * FROM \"Tache\" ");
      while(result.next()){ 
          
          
          for(int i = 0;i < idTache.size();i++)
          {
          if(date.equals(result.getObject(4).toString()) &&idTache.get(i).equals(result.getObject(1).toString()))
          {
              TitreTache.add(result.getObject(6).toString());
          Tache.add(result.getObject(2).toString());
          DroitTF.add(DroitT.get(i));
          Heure.add(result.getObject(5).toString());
          System.out.println(result.getObject(2).toString() + " " + result.getObject(5).toString());
          }
          }
        
         }
      
      result.close();
      
      result = state.executeQuery("SELECT * FROM \"Reunion\" ");
      
      while(result.next()){ 
          if(date.equals(result.getObject(2).toString()))
          {
          for(int i = 0;i < idReunion.size();i++)
          {
             if(idReunion.get(i).equals(result.getObject(1).toString()))
             {
                 //System.out.println(result.getObject(4).toString()+ " loooooool ");
                 TitreReunion.add(result.getObject(5).toString());
                 this.DroitRF.add(DroitR.get(i));
          this.Reunion.add(result.getObject(4).toString()); 
          this.HeureR.add(result.getObject(3).toString());
             }
          }
          }
         }
      result.close();
      result = state.executeQuery("SELECT * FROM \"MultiTache\" ");
      //On récupère les MetaData
      
         
      
      while(result.next()){      
          for(int i = 0;i < idMulti.size();i++)
          {
        if(idMulti.get(i).equals(result.getObject(1).toString()))
          {
          String DateD = result.getObject(3).toString();
          String DateF = result.getObject(4).toString();
          String DayD = "";
          DayD = DayD + DateD.charAt(0) + DateD.charAt(1);
          String MonthD = "";
          MonthD = MonthD + DateD.charAt(3) + DateD.charAt(4);
          String YearD = "";
          YearD = YearD + DateD.charAt(6) + DateD.charAt(7)+DateD.charAt(8) + DateD.charAt(9);
          String DayF = "";
          DayF = DayF + DateF.charAt(0) + DateF.charAt(1);
          String MonthF = "";
          MonthF = MonthF + DateD.charAt(3) + DateF.charAt(4);
          String YearF = "";
          YearF = YearF + DateD.charAt(6) + DateF.charAt(7)+DateD.charAt(8) + DateD.charAt(9);
          int jourD = Integer.parseInt(DayD);
          int moisD= Integer.parseInt(MonthD);
          int anneeD= Integer.parseInt(YearD);
          int jourF = Integer.parseInt(DayF);
          int moisF= Integer.parseInt(MonthF);
          int anneeF= Integer.parseInt(YearF);
          
          int period =Integer.parseInt(result.getObject(5).toString());
          int periods = period;
          if(this.day == jourD&&this.month == moisD&&this.year == anneeD)
          {
              
          periods = period;
          this.DroitMF.add(DroitM.get(i));
          this.Multi.add(result.getObject(2).toString());
          this.HeureMulti.add(result.getObject(6).toString());
          TitreMulti.add(result.getObject(7).toString());
          }
          while(jourD < jourF||moisD<moisF||anneeD<anneeF)
          {
          if(this.day == jourD&&this.month == moisD&&this.year == anneeD)
          {
              if(periods == 0)
          {
          periods = period;
          this.DroitMF.add(DroitM.get(i));
          this.Multi.add(result.getObject(2).toString());
          this.HeureMulti.add(result.getObject(6).toString());
          TitreMulti.add(result.getObject(7).toString());
          }
          }
           if(periods == 0)
          {
              periods = period;
          }
          
          jourD++;
          periods--;
          
          
          if(jourD > MonthDays.get(moisD))
          {
          jourD%= MonthDays.get(moisD);
          moisD++;
          }
          if(moisD == 13)
          {
          moisD = 1;
          anneeD++;
          }
          }
          if(this.day == jourD&&this.month == moisD&&this.year == anneeD)
          {
              if(periods == 0)
          {
          periods = period;
          this.DroitMF.add(DroitM.get(i));
          this.Multi.add(result.getObject(2).toString());
          this.HeureMulti.add(result.getObject(6).toString());
          TitreMulti.add(result.getObject(7).toString());
          }
          }
          }
          }
         }
      result.close();
      state.close();
        
         
    } catch (Exception e) {
      e.printStackTrace();
    }      

}
    
    ArrayList<String> getTache(){
        return (this.Tache);
}
    ArrayList<String> getDroitT(){
        return (this.DroitTF);
}
    ArrayList<String> getHeureMulti(){
        return (this.HeureMulti);
}
    
    ArrayList<String> getMulti(){
        return (this.Multi);
}
    ArrayList<String> getDroitM(){
        return (this.DroitMF);
}
    ArrayList<String> getHeure(){
        return (this.Heure);
}
    ArrayList<String> getReunion(){
        return (this.Reunion);
}
     ArrayList<String> getTitreReunion(){
        return (this.TitreReunion);
}
     ArrayList<String> getTitreTache(){
        return (this.TitreTache);
}
     ArrayList<String> getTitreMulti(){
        return (this.TitreMulti);
}
    ArrayList<String> getDroitR(){
        return (this.DroitRF);
}
    ArrayList<String> getHeureR(){
        return (this.HeureR);
}
  public static void main(String[] args) {      
   
  }
}
