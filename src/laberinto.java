import java.io.*;
import java.util.*;

//Equipo: Enigma            03/Diciembre/2021
//Luis Arturo Estrada Rios
//Sebastian Molina Perez
//Dulce Ramona Gutierrez Cordova

class punto{
    int x;
    int y;

    public punto(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class laberinto {
    private static boolean bandera = false;
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Error, no introdujo argumentos");
            System.exit(0);
        }

            if (Menu.path != null) {
                args[0] = Menu.path;
            } else {
                char[][] laberinto = cargaLaberinto(args[0]);
                char[][] laberinto2 = cargaLaberinto(args[0]);
                imprimeLaberinto(laberinto);
                char[][] resueltoBFS = BFS(laberinto);
                char[][] resueltoDFS = DFS(laberinto2);
                int distancia = caminoCorto(laberinto);
                //char[][] datoBFS = resueltoBFS;
            }
            new Menu();
    }

    public static char[][] DFS(char[][] map){
        System.out.println("Metodo DFS");
        int startfila = 0;
        int startcolumna = 0;
        int endfila = 0;
        int endcolumna = 0;
        boolean encontrado = false;
        boolean encontrado2 = false;
        while (encontrado2 == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][0] == ' ') {
                        if (encontrado == false) {
                            startfila = i;
                            startcolumna = 0;
                            encontrado = true;
                        }else{
                            endfila=i;
                            endcolumna=0;
                            encontrado2 = true;
                        }
                    }
                    if (map[0][i] == ' ') {
                        if (encontrado == false) {
                            startfila = 0;
                            startcolumna = i;
                            encontrado = true;
                        }else{
                            endfila=0;
                            endcolumna=i;
                            encontrado2 = true;
                        }
                    }
                    if (map[i][map[0].length-1] == ' ') {
                        if (encontrado == false) {
                            startfila = i;
                            startcolumna = map[0].length-1;
                            encontrado = true;
                        }else{
                            endfila=i;
                            endcolumna=map[0].length-1;
                            encontrado2 = true;
                        }
                    }
                    if (map[map.length-1][i] == ' ') {
                        if (encontrado == false){
                            startfila = map.length-1;
                            startcolumna = i;
                            encontrado = true;
                        }else{
                            endfila=map.length-1;
                            endcolumna=i;
                            encontrado2 = true;
                        }
                    }
                }
            }
        }
        System.out.println();
        ArrayList<String> recorrido = new ArrayList<>();
        punto start = new punto(startfila,startcolumna);
        char[][] resultado = map;
        int []x = {0,0,1,-1};
        int []y = {1,-1,0,0};
        Stack<punto> stack = new Stack<>();
        stack.push(start);
        boolean[][] visto = new boolean[map.length][map[0].length];
        visto[start.x][start.y]=true;
        while(!stack.isEmpty()){
            if(bandera == true){
                break;
            }
            punto p = stack.pop();
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < map.length && b < map[0].length && visto[a][b] == false  && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ){
                    visto[a][b] = true;
                    resultado[a][b]='#';
                    recorrido.add("\"(" + a + ","+b + ")\"");
                    if(map[a][b]==map[endfila][endcolumna]){
                        bandera = true;
                        break;
                    }
                    stack.push(new punto(a,b));
                }
            }
        }
        if(resultado[endfila][endcolumna] != '#'){
            imprimeLaberinto(resultado);
            System.out.println("El laberinto no tiene solucion");
            System.exit(0);
        }
        escribeArchivo(recorrido,resultado,"DFS");
        imprimeLaberinto(resultado);
        return resultado;
    }

    public static char[][] BFS(char[][] map){
        System.out.println("Metodo BFS");
        int startfila = 0;
        int startcolumna = 0;
        int endfila = 0;
        int endcolumna = 0;
        boolean encontrado = false;
        boolean encontrado2 = false;
        while (encontrado2 == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][0] == ' ') {
                        if (encontrado == false) {
                            startfila = i;
                            startcolumna = 0;
                            encontrado = true;
                        }else{
                            endfila=i;
                            endcolumna=0;
                            encontrado2 = true;
                        }
                    }
                    if (map[0][i] == ' ') {
                        if (encontrado == false) {
                            startfila = 0;
                            startcolumna = i;
                            encontrado = true;
                        }else{
                            endfila=0;
                            endcolumna=i;
                            encontrado2 = true;
                        }
                    }
                    if (map[i][map[0].length-1] == ' ') {
                        if (encontrado == false) {
                            startfila = i;
                            startcolumna = map[0].length-1;
                            encontrado = true;
                        }else{
                            endfila=i;
                            endcolumna=map[0].length-1;
                            encontrado2 = true;
                        }
                    }
                    if (map[map.length-1][i] == ' ') {
                        if (encontrado == false){
                            startfila = map.length-1;
                            startcolumna = i;
                            encontrado = true;
                        }else{
                            endfila=map.length-1;
                            endcolumna=i;
                            encontrado2 = true;
                        }
                    }
                }
            }
        }
        ArrayList<String> recorrido = new ArrayList<>();
        punto start = new punto(startfila,startcolumna);
        char[][] resultado = map;
        int []x = {0,0,1,-1};
        int []y = {1,-1,0,0};
        Queue<punto> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visto = new boolean[map.length][map[0].length];
        visto[start.x][start.y] = true;
        boolean bandera = false;
        while(!queue.isEmpty()){
            if(bandera == true){
                break;
            }
            punto p = queue.remove();
            for(int i = 0; i < 4; i++){
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < map.length && b < map[0].length && visto[a][b] == false && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ){
                    visto[a][b] = true;
                    resultado[a][b]='#';
                    recorrido.add("\"(" + a + ","+b + ")\"");
                    if(map[a][b]==map[endfila][endcolumna]){
                        bandera = true;
                        break;
                    }
                    queue.add(new punto(a,b));
                }
            }
        }
        if(resultado[endfila][endcolumna] != '#'){
            imprimeLaberinto(resultado);
            System.out.println("El laberinto no tiene solucion");
            System.exit(0);
        }
        escribeArchivo(recorrido,resultado,"BFS");
        imprimeLaberinto(resultado);
        return resultado;
    }

    //C:\Users\yuvit\IdeaProjects\prueba_lab\src\laberinto_mediano.txt

    public static void imprimeLaberinto(char[][] map){
        for(char[] fila: map){
            for(char elemento: fila){
                System.out.print(elemento);
            }
            System.out.println();
        }
    }

    public static int caminoCorto(char[][] map) {
        System.out.println("Camino Corto");
        int startfila = 0;
        int startcolumna = 0;
        int endfila = 0;
        int endcolumna = 0;
        boolean encontrado = false;
        boolean encontrado2 = false;
        while (encontrado2 == false) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][0] == ' ') {
                        if (encontrado == false) {
                            startfila = i;
                            startcolumna = 0;
                            encontrado = true;
                        }else{
                            endfila=i;
                            endcolumna=0;
                            encontrado2 = true;
                        }
                    }
                    if (map[0][i] == ' ') {
                        if (encontrado == false) {
                            startfila = 0;
                            startcolumna = i;
                            encontrado = true;
                        }else{
                            endfila=0;
                            endcolumna=i;
                            encontrado2 = true;
                        }
                    }
                    if (map[i][map[0].length-1] == ' ') {
                        if (encontrado == false) {
                            startfila = i;
                            startcolumna = map[0].length-1;
                            encontrado = true;
                        }else{
                            endfila=i;
                            endcolumna=map[0].length-1;
                            encontrado2 = true;
                        }
                    }
                    if (map[map.length-1][i] == ' ') {
                        if (encontrado == false){
                            startfila = map.length-1;
                            startcolumna = i;
                            encontrado = true;
                        }else{
                            endfila=map.length-1;
                            endcolumna=i;
                            encontrado2 = true;
                        }
                    }
                }
            }
        }
        punto start = new punto(startfila,startcolumna);
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        LinkedList<punto> q = new LinkedList();
        q.add(start);
        int[][] dist = new int[map.length][map[0].length];
        for (int[] a : dist) {
            Arrays.fill(a, -1);
        }
        int resultado=0;
        dist[start.x][start.y] = 0;
        while (!q.isEmpty()) {
            punto p = q.removeFirst();
            for (int i = 0; i < 4; i++) {
                int a = p.x + x[i];
                int b = p.y + y[i];
                if (a >= 0 && b >= 0 && a < map.length && b < map[0].length && dist[a][b] == -1 && map[a][b] != '+' && map[a][b] != '-' && map[a][b] != '|' ) {
                    dist[a][b] = 1 + dist[p.x][p.y];
                    resultado=dist[a][b];
                    q.add(new punto(a, b));
                }
            }
        }
        System.out.println("El camino más corto tiene una distancia de: "+resultado);
        return resultado;
    }


    public static void escribeArchivo(ArrayList<String> nodos, char[][] laberinto, String archivo){
        int i=0;
        String nombre = null;
        if(archivo == "DFS"){
            nombre = "laberintoDFS.dot";
        }
        if(archivo == "BFS"){
            nombre = "laberintoBFS.dot";
        }
        Queue<String> queue = new LinkedList<>();
        for (String x: nodos) {
            if(i<nodos.size()-1) {
                queue.add(nodos.get(i) + "->" + nodos.get(i+1));
            }
            i++;
        }
        try {
            FileWriter fw = new FileWriter(nombre);
            String cadena = "digraph G {";
            fw.write("digraph G {");
            fw.write("\n");
            for (String oracion : queue) {
                fw.write(oracion);
                fw.write("\n");
            }
            fw.write("}");
            fw.write("\n");
            for(char[] fila: laberinto){
                for(char elemento: fila){
                    fw.write(elemento);
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
