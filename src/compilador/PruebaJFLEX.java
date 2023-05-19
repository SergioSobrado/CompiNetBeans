/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author sergi
 */
public class PruebaJFLEX {

    public static void main(String[] args) throws Exception{
        String ruta = "C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/Anali.flex";
        String ruta2 = "C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/LexicoCup.flex";
        String[] rutaS = {"-parser", "Sintax", "C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/Sintax.cup"};

        GenerarPrueba(ruta, ruta2, rutaS);
    }

    public static void GenerarPrueba(String ruta, String ruta2, String[] rutaSin) throws Exception {
        File Archivo;
        Archivo = new File(ruta);
        JFlex.Main.generate(Archivo);

        Archivo = new File(ruta2);
        JFlex.Main.generate(Archivo);
        
        java_cup.Main.main(rutaSin);
        
        
        Path rutaSym = Paths.get("C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/sym.java");
        if(Files.exists(rutaSym)){
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get("C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/sym.java"),
                Paths.get("C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/sym.java"));
        
        
         Path rutaSim = Paths.get("C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/Sintax.java");
        if(Files.exists(rutaSim)){
            Files.delete(rutaSim);
        }
        Files.move(
                Paths.get("C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/Sintax.java"),
                Paths.get("C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/Sintax.java"));
            
    }
    
}
