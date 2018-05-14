
package lemarioo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Autor:Juan Pablo Carpio
 */

public class Lemarioo{
    
    ArrayList<String> lemario = new ArrayList();
    
    public void cargaLemario(){
        
        File fichero = new File("src/lemarioo/lemario-20101017.txt");
        
        try{
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            
            while ((linea = br.readLine()) != null){
                linea = quitarAcentos(linea);
                lemario.add(linea);
            } 
            
        } 
        catch (FileNotFoundException ex){
            Logger.getLogger(Lemarioo.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex){
            Logger.getLogger(Lemarioo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean buscador(String palabra){
        
        if(lemario.contains(palabra)){
            return true;
        }
        return false;
    }
    
    public boolean escaleraDePalabras(ArrayList<String> escalera){
        
        boolean lemario = true;
	boolean escaleraPalabras = true;
        int contador;
	
        for (int i = 0; i < escalera.size() - 1 && escaleraPalabras; i++){	
            lemario = buscador(escalera.get(i));
            if(!lemario){
                return false;
            }
            contador = 0;
	    if (escalera.get(i + 1).length() == escalera.get(i).length()){		
		for (int j = 0; j < escalera.get(i).length(); j++){
                    if(escalera.get(i).charAt(j) != escalera.get(i + 1).charAt(j)){
                        contador++;
                    }
                    if(contador > 1){
                        escaleraPalabras = false;
                    }
                    if(!lemario){
                        escaleraPalabras = false;
                    }
		}
	    } 
	}
	return escaleraPalabras;
    }
    
    public static String quitarAcentos(String cadena){
        
        String quitado = null;
        if (cadena != null){
            String valor = cadena;
            valor = valor.toUpperCase();
            
            
            quitado = Normalizer.normalize(valor, Normalizer.Form.NFD);
            quitado = Normalizer.normalize(quitado, Normalizer.Form.NFC);
        }
        return quitado;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        ArrayList<String> listaPalabras = new ArrayList();
        Lemarioo ejercicio = new Lemarioo();
        ejercicio.cargaLemario();
        
        listaPalabras.add("PATO");
        listaPalabras.add("GATO");
        listaPalabras.add("MATO");
        listaPalabras.add("MANO");
        listaPalabras.add("MAGO");
        listaPalabras.add("MAJO");
        
        System.out.println(ejercicio.escaleraDePalabras(listaPalabras));
    }
}