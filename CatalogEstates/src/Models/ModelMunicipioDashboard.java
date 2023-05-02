/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DatosDTO.MuniciopioDTO;
import Views.DashBoard;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CCNAR
 */
public class ModelMunicipioDashboard {
    MuniciopioDTO dto;
    List<MuniciopioDTO> municipios = new ArrayList<MuniciopioDTO>();
    
     public ModelMunicipioDashboard() {
        llenarMunicipios();
    }
    
    public  void llenarMunicipios(){
        municipios.add(new MuniciopioDTO(1, "Calinki")); //0
        municipios.add(new MuniciopioDTO(2, "Campeche")); //1
        municipios.add(new MuniciopioDTO(3, "Carmen"));   //2
        municipios.add(new MuniciopioDTO(7, "Palizada"));  //3
        System.out.println(municipios);
    }
    
     
    public void LimpiarCampos(DashBoard db){
        int contador = Integer.parseInt(db.ilbd.getText());
        db.txt_Nombre.setText("");
        db.txt_id.setText(""+(contador+1));
        db.txt_Nombre.requestFocus(true);
    }
    
    public void removeRow(int filaActual){
        if (filaActual != -1){
           System.out.println(filaActual);
            JOptionPane.showMessageDialog(null, "Se elimino el registro: "+filaActual);
           municipios.remove(filaActual);
        }
    }
    
    public void Actualizar(int filaActual, DashBoard db){
        if (filaActual != -1){
            System.out.println("Registro a Actualizar");
            System.out.println(db.tblMunicipios.getValueAt(filaActual, 0));
            System.out.println(db.tblMunicipios.getValueAt(filaActual, 1));
            System.out.println(db.tblMunicipios.getValueAt(filaActual, 2));
            
            db.txt_recNo.setText(""+db.tblMunicipios.getValueAt(filaActual, 0));
            db.txt_id.setText("" + db.tblMunicipios.getValueAt(filaActual, 1));
            db.txt_Nombre.setText("" + db.tblMunicipios.getValueAt(filaActual, 2));

        }else{
            System.out.println("Es necesario Seleccionar un registro");
        }
    }
    
    public void Aceptar(int recNo, int id, String Nombre){
        if (recNo == -1){
            municipios.add(new MuniciopioDTO(id, Nombre));
        }else{
            municipios.get(recNo).setID(id);
            municipios.get(recNo).setNombre(Nombre);

        }
        
    }

    public void setDatos(DashBoard db) {
        DefaultTableModel modeloTabla = (DefaultTableModel) db.tblMunicipios.getModel();
        limpiarTable(modeloTabla);
        int columnas = modeloTabla.getColumnCount();
         Object[] datos = new Object[columnas];
        for (int i = 0; i < municipios.size(); i++) {
            datos[0] = i;
            datos[1] = municipios.get(i).getID();
            datos[2] = municipios.get(i).getNombre();
            modeloTabla.addRow(datos);
        }
    }
    
    public void limpiarTable(DefaultTableModel modeloTabla) {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }
    
    public int lastItemList(){
        return  municipios.get(municipios.size()-1).getID();
    }
    
}
