/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author IVAN
 */
public class estudiante extends persona{
    private String carnet;
    private int id;
        Conexion cn;
        public TableModel leer;
    
   public estudiante() {
    }

    public estudiante(String carnet, int id) {
        this.carnet = carnet;
        this.id = id;
    }

    public estudiante(String carnet, int id, String nombres, String apellidos, String direccion, String telefono, String genero, String email, String fechanac) {
        super(nombres, apellidos, direccion, telefono, genero, email, fechanac);
        this.carnet = carnet;
        this.id = id;
    }

       
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "Select id_estudiantes as id,nit,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento from estudiantes;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","Carnet","Nombres","Apellidos","Direccion","Telefono","Genero","Mail","Nacimiento"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[]=new String [7];
        
        while (consulta.next()){
        datos[0] = consulta.getString("id");
        datos[1] = consulta.getString("carnet");
        datos[2] = consulta.getString("nombres");
        datos[3] = consulta.getString("apellidos");
        datos[4] = consulta.getString("direccion");
        datos[5] = consulta.getString("telefono");
         datos[6] = consulta.getString("genero");
          datos[7] = consulta.getString("email");
        datos[8] = consulta.getString("fecha_nacimiento");
        tabla.addRow(datos);
        }
        cn.cerrar_conexion();
        
        
    }catch(SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error: " + ex.getMessage());
        
    }
    return tabla;
    }
   
        @Override
    public void agregar()
    {
        try{
                PreparedStatement parametro;
                String query= "INSERT INTO estudiantes (carnet,nombres,apellidos,direccion,telefono,,genero,email,fecha_nacimiento)VALUES(?,?,?,?,?,?,?,?,?);";
                cn = new Conexion();
                cn.abrir_conexion();
                parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
                parametro.setString(1, this.getCarnet());
                parametro.setString(2, this.getNombres());
                parametro.setString(3, this.getApellidos());
                parametro.setString(4, this.getDireccion());
                parametro.setString(5, this.getTelefono());
                 parametro.setString(5, this.getGenero());
                  parametro.setString(5, this.getEmail());
                parametro.setString(6, this.getFechanac());
                
                int executar = parametro.executeUpdate();
                cn.cerrar_conexion();
                JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException | SQLException ex){
                System.out.println("Error..."+ ex.getMessage());            
        }
    }
    
    
    
    
    
    
}
