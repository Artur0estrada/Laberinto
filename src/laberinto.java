import java.io.*;
import java.util.*;

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

        System.out.println("quiubole");
        System.out.println("Puros corridos tumbados");
        char[][] laberinto = cargaLaberinto(args[0]);
        char[][] laberinto2 = cargaLaberinto(args[0]);
        for(char[] fila: laberinto){
            for(char elemento: fila){
                System.out.print(elemento);
            }
            System.out.println();
        }
        ArrayList<String> recorrido = new ArrayList<>();
        ArrayList<String> recorrido2 = new ArrayList<>();
        int startfila = 0;
        int startcolumna = 0;
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                if(laberinto[i][startcolumna] == ' '){
                    startfila = i;
                }
            }

        }
        Point start = new Point(startcolumna,startfila);
        char[][] resueltoBFS = visitBFS(laberinto, start, recorrido);
        char[][] resueltoDFS = visitDFS(laberinto2, start, recorrido2);
        //char[][] resueltoDIJ = visitDijkstra(laberinto, start, recorrido);
        System.out.println("BFS: ");
        for(char[] fila: resueltoBFS){
            for(char elemento: fila){
                System.out.print(elemento);
            }
            System.out.println();
        }
        System.out.println("DFS: ");
        for(char[] fila: resueltoDFS){
            for(char elemento: fila){
                System.out.print(elemento);
            }
            System.out.println();
        }
        new Menu();
    }

    public static char[][] visitDFS(char[][] map , Point start, ArrayList<String> recorrido){
        char[][] resultado = map;
        int []x = {0,0,1,-1};
        int []y = {1,-1,0,0};
        Stack<Point> stack = new Stack<>();
        stack.push(start);
        int n = map.length;
        int m = map[0].length;
        boolean[][] visto = new boolean[n][m];
        int endx = resultado[0].length-1;
        int endy = 0;
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if(resultado[i][endx] == ' '){
                    endy = i;
                }
            }
        }
        boolean bandera = false;
        while(!stack.isEmpty()){
            if(bandera == true){
                break;
            }
            Point p = stack.pop();
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < n && b < m && visto[a][b] == false  && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ){
                    visto[a][b] = true;
                    recorrido.add("\"(" + a + ","+b + ")\"");
                    resultado[a][b]='#';
                    if(map[a][b]==map[endy][endx]){
                        bandera = true;
                        break;
                    }
                    stack.push(new Point(a,b));
                }
            }
        }
        for(String recorrer: recorrido){
            System.out.println(recorrer);
        }
        escribeArchivo(recorrido,resultado);
        return resultado;
    }

    public static char[][] visitBFS(char[][] map , Point start, ArrayList<String> recorrido){
        char[][] resultado = map;
        int []x = {0,0,1,-1};
        int []y = {1,-1,0,0};
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        int n = map.length;
        int m = map[0].length;
        boolean[][] visto = new boolean[n][m];
        int endx = resultado[0].length-1;
        int endy = 0;
        for (int i = 0; i < resultado.length; i++) {
            for (int j = 0; j < resultado[0].length; j++) {
                if(resultado[i][endx] == ' '){
                    endy = i;
                }
            }

        }
        visto[start.x][start.y] = true;
        boolean bandera = false;
        while(!queue.isEmpty()){
            if(bandera == true){
                break;
            }
            Point p = queue.remove();
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < n && b < m && visto[a][b] == false && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ){
                    visto[a][b] = true;
                    resultado[a][b]='#';
                    recorrido.add("(" + a + ","+b + ")");
                    System.out.println();
                    if(map[a][b]==map[endy][endx]){
                        bandera = true;
                        break;
                    }
                    queue.add(new Point(a,b));
                }
            }
        }
        for(String recorrer: recorrido){
            System.out.println(recorrer);
        }
        return resultado;
    }

    public static void escribeArchivo(ArrayList<String> nodos, char[][] laberinto){

        int i=0;
        Queue<String> queue = new LinkedList<>();
        for (String x: nodos) {
            //System.out.println(x.nombre + "->" + y.nombre_destino + "[label=" + y.peso + "]");
            if(i<nodos.size()-1) {
                queue.add(nodos.get(i) + "->" + nodos.get(i+1));
            }
            i++;
        }
        try {
            FileWriter fw = new FileWriter("laberinto.dot");
            for (String oracion : queue) {
                fw.write(oracion);
                fw.write("\n");
            }
            for(char[] fila: laberinto){
                for(char elemento: fila){
                    fw.write(elemento);
                    //System.out.print(elemento);
                }
                fw.write("\n");
            }
            fw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
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
