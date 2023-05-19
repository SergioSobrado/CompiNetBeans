/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

import static compilador.Token.CorchDer;
import static compilador.Token.CorchIzq;
import static compilador.Token.Mientras;
import static compilador.Token.ParentDer;
import static compilador.Token.ParentIzq;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author sergi
 */
public class GenerarArchivos {

    public static void main(String[] args) throws IOException {
        //Archivo de tokens    
        FileWriter MisTokens = null;
        PrintWriter Escribir = null;

        //Archivo de Simbolos
        FileWriter TablasSimbolos = null;
        PrintWriter EscribirSimbo;
        try {
            String ruta = "C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/prueba.txt";
            File archivo = new File(ruta);

            //Archivo Tokens
            String rutaEs = "C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/Tokens.txt";
            MisTokens = new FileWriter(rutaEs);

            //Arhivo Simbolos
            String RutaSim = "C:/Users/sergi/OneDrive/Documentos/NetBeansProjects/Compilador/src/compilador/TablaSimbolos.txt";
            TablasSimbolos = new FileWriter(RutaSim);

            //leer
            BufferedReader LeerArchivo = new BufferedReader(new FileReader(archivo));
            //Escribir
            Escribir = new PrintWriter(MisTokens);
            EscribirSimbo = new PrintWriter(TablasSimbolos);

            //HashMap
            HashMap<String, Integer> direcciones = new HashMap<String, Integer>();
            int contValores = 0;
            //Traer datos del analizador
            AnalizadorLexico AnalizarJflex = new AnalizadorLexico(LeerArchivo);
            while (true) {
                Token Info = AnalizarJflex.yylex();

                if (Info == null) {
                    Escribir.println("Fin Del Escaneo");
                    return;
                }
                

                switch (Info) {
                    case ParentIzq:
                    case ParentDer:
                    case CorchIzq:
                    case CorchDer:
                        Escribir.println(Info);
                        break;
                    case Mientras:
                        Escribir.println(Info + " " + AnalizarJflex.Lexema);
                        break;
                    case Variables:
                        if (direcciones.containsKey(AnalizarJflex.Lexema)) {
                            int IDLex = direcciones.get(AnalizarJflex.Lexema);
                            EscribirSimbo.println(Info + " " + IDLex);
                        } else {
                            direcciones.put(AnalizarJflex.Lexema, contValores);
                            Escribir.println(Info + " " + contValores);
                            EscribirSimbo.println(AnalizarJflex.Lexema);
                            contValores++;
                        }
                        break;
                    default:
                        Escribir.println("Lexema: " +AnalizarJflex.Lexema + "Token: " + Info);
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            MisTokens.close();
            TablasSimbolos.close();
        }

    }
}
