/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DatosDTO.EstadosDTO;
import DatosDTO.MuniciopioDTO;
import Models.ModelEstadoDashboard;
import Models.ModelMunicipioDashboard;
import Views.DashBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author CCNAR
 */
public class ControladorDashboard implements ActionListener, MouseListener{
    
    DashBoard db;
    ModelMunicipioDashboard mdb = new ModelMunicipioDashboard();
    ModelEstadoDashboard edb = new ModelEstadoDashboard();
    MuniciopioDTO dto;
    
    int filaActual = 0;
    boolean verificar = false;
    
    public ControladorDashboard(DashBoard db) {
        this.db = db;
        this.db.btn_Aceptar.addActionListener(this);
        this.db.btn_Actualizar.addActionListener(this);
        this.db.btn_Eliminar.addActionListener(this);
        this.db.btn_cancelar.addActionListener(this);  
        this.db.tblMunicipios.addMouseListener(this);
        this.db.cbx_entidades.addActionListener(this);
        mdb.setDatos(db);
        edb.setDatos(db);
        db.ilbd.setText(""+edb.lastItemList());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == db.btn_Aceptar){
            int recNo = Integer.parseInt(db.txt_recNo.getText());
            int id = Integer.parseInt(db.txt_id.getText());
            String Nombre = db.txt_Nombre.getText();
            if(verificar){
                edb.Aceptar(recNo, id, Nombre);
                edb.setDatos(db);
            }else{
                mdb.Aceptar(recNo, id, Nombre);
                mdb.setDatos(db);
            }
            mdb.LimpiarCampos(db);

        }else if(e.getSource() == db.btn_Actualizar){
            mdb.Actualizar(filaActual, db);
            
        }else if(e.getSource() == db.btn_Eliminar){
             if(verificar){
                 edb.removeRow(filaActual);
                 edb.setDatos(db);
             }else{
                 mdb.removeRow(filaActual);
                 mdb.setDatos(db);
             }
            mdb.LimpiarCampos(db);
            
        }else if(e.getSource() == db.btn_cancelar){
           mdb.LimpiarCampos(db);
           
        }else if(e.getSource() == db.cbx_entidades){
            String entidad = db.cbx_entidades.getSelectedItem().toString();
            if(entidad.equals("ESTADOS")){
                db.ilbd.setText(""+edb.lastItemList());
               db.txt_id.setText(""+(edb.lastItemList()+1));
                verificar = true;
                edb.setDatos(db);
            }else if (entidad.equals("MUNICIPIOS")){
                db.ilbd.setText(""+mdb.lastItemList());
                db.txt_id.setText(""+(mdb.lastItemList()+1));
                verificar = false;
                mdb.setDatos(db);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == db.tblMunicipios){
            filaActual = db.tblMunicipios.getSelectedRow();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
   }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
