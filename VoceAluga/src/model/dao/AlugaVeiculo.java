package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


import model.Veiculo;

public class AlugaVeiculo {

    public static boolean alugarVeiculo(TreeMap<String,String> camposLocacao) {
        String query = "INSERT INTO Locacoes ";
        query = adicionarParametrosQueryDeInsercao(query,camposLocacao);

        try {
            Statement stmt  = MySQLConnector.connection.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println("model.dao.alugaVeiculo: método alugarVeiculo");
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static String adicionarParametrosQueryDeInsercao(String query,TreeMap<String,String> campos){
        boolean algumParametroAdicionado = false; // indica se algum parametro ja foi adicionado apos WHERE

        query = query+ " (";
        for(String key : campos.keySet()){
            if(!algumParametroAdicionado){
                query= query + key;
                algumParametroAdicionado = true;
            }
            else{
                query = query +","+key;
            }
        }
        query = query +") VALUES (";
        algumParametroAdicionado=false;

        for(String key: campos.keySet()){
            if((key.equals("id"))&& campos.get(key).equals("")){
                if(!algumParametroAdicionado){
                    query= query + "NULL";
                    algumParametroAdicionado = true;
                }
                else{
                    query = query +","+"NULL";
                }
            }
            else if((key.equals("id_cliente"))&& campos.get(key).equals("")){
                if(!algumParametroAdicionado){
                    query= query + "NULL";
                    algumParametroAdicionado = true;
                }
                else{
                    query = query +","+"NULL";
                }
            }
            else{
                if(!algumParametroAdicionado){
                    query= query + "\""+campos.get(key)+"\"";
                    algumParametroAdicionado = true;
                }
                else{
                    query = query +","+"\""+campos.get(key)+"\"";
                }
            }
        }

        query = query + ");";
        return query;
    }
}
