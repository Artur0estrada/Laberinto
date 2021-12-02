import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame implements ActionListener {
    JButton btnDFS,btnBFS,btnSalir,btnCargar;
    JTextField txtcargarPath;
    JLabel labelInstruccion;
    public static String path;




    public Menu(){
        componentes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,225);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setTitle("Proyecto Laberinto");
    }


    public void componentes(){

        labelInstruccion=new JLabel();
        labelInstruccion.setBounds(110,20,300,20);
        labelInstruccion.setText("Ingrese aquí el argumento");
        add(labelInstruccion);

        txtcargarPath=new JTextField();
        txtcargarPath.setBounds(75,50,250,20);
        txtcargarPath.setText("Escriba aquí...");
        add(txtcargarPath);

        btnDFS=new JButton();
        btnDFS.setBounds(30,100,150,20);
        btnDFS.setText("Método DFS");
        btnDFS.addActionListener(this);
        add(btnDFS);

        btnBFS=new JButton();
        btnBFS.setBounds(200,100,150,20);
        btnBFS.setText("Método BFS");
        btnBFS.addActionListener(this);
        add(btnBFS);

        btnCargar=new JButton();
        btnCargar.setBounds(128,73,150,15);
        btnCargar.setText("Cargar");
        btnCargar.addActionListener(this);
        add(btnCargar);

        btnSalir=new JButton();
        btnSalir.setBounds(138,135,100,20);
        btnSalir.setText("Salir");
        btnSalir.addActionListener(this);
        add(btnSalir);


    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(btnSalir)){
            System.out.println("Se ha pulsado el botón Salir");
            System.exit(0);
        }
        if(e.getSource().equals(btnCargar)){
            System.out.println("Se ha pulsado el botón Cargar");
            path=txtcargarPath.getText();

        }
        if(e.getSource().equals(btnBFS)){
            System.out.println("Se ha pulsado el botón BFS");

            char[][] matriz = laberinto.cargaLaberinto(txtcargarPath.getText());
            char[][] matriz_resuelta = laberinto.BFS(matriz);
            laberinto.imprimeLaberinto(matriz_resuelta);

        }
        if(e.getSource().equals(btnDFS)){
            System.out.println("Se ha pulsado el botón DFS");
            char[][] matriz = laberinto.cargaLaberinto(txtcargarPath.getText());
            char[][] matriz_resuelta = laberinto.DFS(matriz);
            laberinto.imprimeLaberinto(matriz_resuelta);


        }
    }
}
