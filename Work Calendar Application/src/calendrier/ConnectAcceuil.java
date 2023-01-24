package calendrier;

import java.sql.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amouna
 */
//CTRL + SHIFT + O pour générer les imports
public class ConnectAcceuil {
    private ArrayList<String> Logins=new ArrayList<String>();
    private ArrayList<String> Passwords=new ArrayList<String>();
    private ArrayList<Integer> idGroupe = new ArrayList<Integer>();
    ConnectAcceuil(){
 try {
      Class.forName("org.postgresql.Driver");
      System.out.println("Driver O.K.");

      String url = "jdbc:postgresql://localhost:5432/postgres";
      String user = "postgres";
      String passwd = "azaro";
      Connection conn = DriverManager.getConnection(url, user, passwd);
      System.out.println("Connexion effective !");  
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet result;
        result = state.executeQuery("SELECT * FROM \"Personne\" ");
      //On récupère les MetaData
      ResultSetMetaData resultMeta = result.getMetaData();
      
     
      while(result.next()){         
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
        {
         
          if(i == 4)
          {
          Logins.add(result.getObject(i).toString());
          }
          else if(i == 7)
          {
          Passwords.add(result.getObject(i).toString());
          }
          else if(i == 8)
          {
          idGroupe.add(Integer.parseInt(result.getObject(i).toString()));
          }
          
        }
         }
         for(int i = 0;i < idGroupe.size();i++)
         {
         System.out.print(idGroupe.get(i));
         }
      result.close();
      state.close();
         
         
    } catch (Exception e) {
      e.printStackTrace();
    }      

}
    ArrayList<String> getLogins(){
        return (this.Logins);
}
    ArrayList<String> getPasswords(){
        return (this.Logins);
}
    ArrayList<Integer> getidGroupe(){
        return (this.idGroupe);
}
  public static void main(String[] args) {      
   ConnectAcceuil c = new ConnectAcceuil();
   
  }
}
