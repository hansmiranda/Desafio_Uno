/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.previred.periodosfaltantes.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.periodosfaltantes.dao.FileJson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hans
 */
public class Util {
    
    
    /**

    * Método que devuelve una lista con las fechas que no se generaron
    * @param list con las fechas que si se generaron
    * @param fechaIni fecha inicial, la de creacion
    * @param fechaFin fecha final
    * @return fechasFaltantes lista con las fechas faltantes

     */
    public static List<String> getListFechasFaltantes(List<String> list, String fechaIni, String fechaFin){
        
        LocalDate firstDate = LocalDate.parse(fechaIni);
        LocalDate lastDate = LocalDate.parse(fechaFin); 
        //Se obtiene el perido entre dos fechas
        Period period = Period.between(firstDate, lastDate);

        //Cantidad de iteraciones que se realizaran desde
        //de la fecha de creacion hasta la fecha fin.
        int meses =  ( period.getYears() * 12 ) + period.getMonths();
        //Arreglo para depositar solo las fecha que no se generaron
        List<String> fechasFaltantes = new ArrayList<>();
        
        while(meses>=0){
            //Si la lista de las generadas no la contiene, entonces es una faltante
            if(!list.contains(firstDate.toString()))
                fechasFaltantes.add(firstDate.toString());

            //Avanzo un mes
            firstDate = firstDate.plusMonths(1);
            
            meses--;

        }

        //retorna lista con las fechas faltantes
        return fechasFaltantes;
        
        
    }
    
    
    
    /**
    * Método que genera un archivo .json 
    * @param fJsonOuput Objeto de tipo FileJson que permite crear la estructura
     */
    public static void generaFileJson(FileJson fJsonOuput){
        
        //Imnpresion ordenada
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Objeto java escrito a .json
        try (FileWriter writer = new FileWriter("./output.json")) {
            gson.toJson(fJsonOuput, writer);
        } catch (IOException e) {
            System.out.println("Error generaFileJson - Msj: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
    * Método que crea un objeto a partir de un .json 
     */
    public static FileJson readObjectJson(){
        
        FileJson fJsonInput = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader("./datos.json"))){
         fJsonInput = new Gson().fromJson(br, FileJson.class); 
        
        } catch (IOException e) {
            System.out.println("Error readObjectJson - Msj: "+e.getMessage());
            e.printStackTrace();
        }
        
        return fJsonInput;
    }
    
}
