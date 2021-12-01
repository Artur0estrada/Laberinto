import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton btnDFS,btnBFS,btnSalir;
    JTextField txtcargarPath;
    JLabel labelInstruccion;

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
        btnDFS.setBounds(30,85,150,20);
        btnDFS.setText("Método DFS");
        add(btnDFS);

        btnBFS=new JButton();
        btnBFS.setBounds(200,85,150,20);
        btnBFS.setText("Método DBS");
        add(btnBFS);

        btnSalir=new JButton();
        btnSalir.setBounds(138,125,100,20);
        btnSalir.setText("Salir");
        add(btnSalir);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnSalir)){
            System.out.println("Se ha pulsado el botón Salir");
        }
    }
}
