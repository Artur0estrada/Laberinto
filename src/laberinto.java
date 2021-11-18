import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class laberinto {
    public static void main(String[] args) {
        String holap;
        String prueba;
        String prueba2;
        System.out.println("quiubole");
        System.out.println("Puros corridos tumbados");
    }

    //Para leer el laberinto
    public static char[][] cargaLaberinto(String archivo)
    {
        int i=0;
        ArrayList<String> lineas = new ArrayList<>();
        try {
            File f = new File(archivo);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            while ((readLine = b.readLine()) != null) {
                //System.out.println(readLine);
                lineas.add(readLine);
            }
            int ancho = lineas.get(0).length();
            int largo = lineas.size();
            char[][] mapa = new char[largo][ancho];
            for(String linea: lineas){
                char[] caracteres = linea.toCharArray();
                for (int j = 0; j < caracteres.length; j++) {
                    mapa[i][j] = caracteres[j];
                }
                i++;
            }
            return mapa;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
