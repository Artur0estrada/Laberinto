import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class laberinto {
    public static void main(String[] args) {
        String holap;
        String prueba;
        String prueba2;
        System.out.println("quiubole");
        System.out.println("Puros corridos tumbados");
        char[][] laberinto = cargaLaberinto(args[0]);
        Point start = new Point(0,1);
        char[][] resuelto = visit(laberinto, start);
        System.out.println("a ver: ");
        for(char[] fila: resuelto){
            for(char elemento: fila){
                System.out.print(elemento);
            }
            System.out.println();
        }
    }

    public static char[][] visit(char[][] map , Point start){
        char[][] resultado = map;
        int []x = {0,0,1,-1};
        int []y = {1,-1,0,0};
        LinkedList<Point> q = new LinkedList();
        q.add(start);
        int n = map.length;
        int m = map[0].length;
        int[][]dist = new int[n][m];
        for(int []a : dist){
            Arrays.fill(a,-1);
        }
        dist[start.x][start.y] = 0;
        while(!q.isEmpty()){
            Point p = q.removeFirst();
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < n && b < m && dist[a][b] == -1 && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ){
                    dist[a][b] = 1 + dist[p.x][p.y];
                    resultado[a][b]='#';
                    if(map[a][b]==map[19][20]){
                        break;
                    }
                    q.add(new Point(a,b));
                }
            }
        }
        return resultado;
    }

    //Para leer el laberinto
    public static char[][] cargaLaberinto(String archivo) {
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
