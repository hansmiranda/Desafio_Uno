/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.previred.periodosfaltantes.ini;


import com.previred.periodosfaltantes.dao.FileJson;
import com.previred.periodosfaltantes.util.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Hans
 */
public class RunVerificaPeriodos {
    
 
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        //Se consume el archivo .json con las fechas de pruebas
        FileJson fJsonInput = Util.readObjectJson();  
        
        
        System.out.println("Fecha Ini\t: "+fJsonInput.getFechaCreacion());
        System.out.println("Fecha Fin\t: "+fJsonInput.getFechaFin());
        System.out.println("Fechas\t: "+fJsonInput.getFechas());
        
        
        List<String> fecFaltantes = Util.getListFechasFaltantes(fJsonInput.getFechas(), 
                                            fJsonInput.getFechaCreacion(), fJsonInput.getFechaFin());
        
        //Muestro los elementos de la list fecFaltantes
        fecFaltantes.forEach(fecha->System.out.println("Periodo faltante: "+fecha));
        
        FileJson fJsonOuput = fJsonInput;
        fJsonOuput.setFechas(null);//Para que no se genere en el archivo output
        fJsonOuput.setFechasFaltantes(fecFaltantes);//se asignan las fechas faltantes
        
        
        //Genera el archivo .json con la estructura del objeto
        Util.generaFileJson(fJsonOuput);
        
        
        
        
	}
    
    
     
    
    
    


    
    
}
