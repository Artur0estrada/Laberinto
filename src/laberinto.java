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
        char[][] laberinto = cargaLaberinto(args[0]);
        char[][] laberinto2 = cargaLaberinto(args[0]);
        imprimeLaberinto(laberinto);
        char[][] resueltoBFS = BFS(laberinto);
        char[][] resueltoDFS = DFS(laberinto2);
        int distancia = caminoCorto(laberinto);
        new Menu();
    }

    public static char[][] DFS(char[][] map){
        int startfila = 0;
        int startcolumna = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][startcolumna] == ' '){
                    startfila = i;
                }
            }

        }
        ArrayList<String> recorrido = new ArrayList<>();
        Point start = new Point(startcolumna,startfila);
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
        imprimeLaberinto(resultado);
        return resultado;
    }

    public static char[][] BFS(char[][] map){
        int startfila = 0;
        int startcolumna = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][startcolumna] == ' '){
                    startfila = i;
                }
            }

        }
        ArrayList<String> recorrido = new ArrayList<>();
        Point start = new Point(startcolumna,startfila);
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
                    recorrido.add("\"(" + a + ","+b + ")\"");
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
        imprimeLaberinto(resultado);
        return resultado;
    }

    public static void imprimeLaberinto(char[][] map){
        for(char[] fila: map){
            for(char elemento: fila){
                System.out.print(elemento);
            }
            System.out.println();
        }
    }

    public static int caminoCorto(char[][] map) {
        int startfila = 0;
        int startcolumna = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][startcolumna] == ' '){
                    startfila = i;
                }
            }
        }
        Point start = new Point(startcolumna,startfila);
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        LinkedList<Point> q = new LinkedList();
        q.add(start);
        int n = map.length;
        int m = map[0].length;
        int[][] dist = new int[n][m];
        for (int[] a : dist) {
            Arrays.fill(a, -1);
        }
        int resultado=0;
        dist[start.x][start.y] = 0;
        while (!q.isEmpty()) {
            Point p = q.removeFirst();
            for (int i = 0; i < 4; i++) {
                int a = p.x + x[i];
                int b = p.y + y[i];
                if (a >= 0 && b >= 0 && a < n && b < m && dist[a][b] == -1 && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ) {
                    dist[a][b] = 1 + dist[p.x][p.y];
                    resultado=dist[a][b];
                    q.add(new Point(a, b));
                }
            }
        }

        System.out.println("El camino más corto tiene una distancia de: "+resultado);
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
