/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pdam.controller;

/**
 *
 * @author salti dilfani
 */

import pdam.view.Formpdam;
import pdam.model.pdam;
import pdam.Dao.pdamDb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pdam.controller.pdamControllerDb;

public class pdamControllerDb 
{
    pdam pdam;
    pdamDb pdamDb;
    Formpdam formPDAM;
    

    
    
    public pdamControllerDb(Formpdam formPDAM) 
    {
        this.formPDAM = formPDAM;
        pdamDb = new pdamDb();
    }
    
    public void cancel(){
        formPDAM.getTxtKodePembayaran().setText("");
        formPDAM.getTxtNama().setText("");
        formPDAM.getTxtTanggal().setText("");
        formPDAM.getTxtMeterBulanIni().setText("");
        formPDAM.getTxtMeterBulanLalu().setText("");
        formPDAM.getTxtBiayaPerMeter().setText("");
        formPDAM.getTxtTotalBayar().setText("");
        isiJenisPelanggan();
    } 
    
    public void isiJenisPelanggan()
    {
        formPDAM.getCboJenisPelanggan().removeAllItems();
        formPDAM.getCboJenisPelanggan().addItem("Gold");
        formPDAM.getCboJenisPelanggan().addItem("Silver");
        formPDAM.getCboJenisPelanggan().addItem("Umum");
    }
    
    public void insert(){
        try {
            pdam = new pdam();
            pdam.setKodePembayaran(formPDAM.getTxtKodePembayaran().getText());
            pdam.setNama(formPDAM.getTxtNama().getText());
            pdam.setJenisPelanggan(formPDAM.getCboJenisPelanggan().getSelectedItem().toString());
            pdam.setTanggal(formPDAM.getTxtTanggal().getText());
            pdam.setMeterBulanIni(formPDAM.getTxtMeterBulanIni().getText());
            pdam.setMeterBulanLalu(formPDAM.getTxtMeterBulanLalu().getText());
            pdam.setBiayaPerMeter(formPDAM.getTxtBiayaPerMeter().getText());
            pdam.setTotalBayar(formPDAM.getTxtTotalBayar().getText());
            pdamDb.insert(pdam);
            JOptionPane.showMessageDialog(formPDAM, "Entri Data Ok");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formPDAM, "Error  "+ex.getMessage());
        }
    }
    
    public void viewData(){
        try {
            DefaultTableModel model = (DefaultTableModel)formPDAM.getTabelPDAM().getModel();
            model.setNumRows(0);
            List<pdam> list = pdamDb.getAllPDAM();
            for(pdam pdam : list){
                Object[] data = 
                {
                    pdam.getKodePembayaran(),
                    pdam.getNama(),
                    pdam.getJenisPelanggan(),
                    pdam.getTanggal(),
                    pdam.getMeterBulanIni(),
                    pdam.getMeterBulanLalu(),
                    pdam.getBiayaPerMeter(),
                    pdam.getTotalBayar()
                };
                model.addRow(data);
            }
        } catch (Exception ex) {
            Logger.getLogger(pdamControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void actionClickTabel(){
        try {
            String kodePembayaran = formPDAM.getTabelPDAM()
                    .getValueAt(formPDAM.getTabelPDAM().getSelectedRow(), 0).toString();
            pdam = pdamDb.getPDAM(kodePembayaran);
            formPDAM.getTxtKodePembayaran().setText(pdam.getKodePembayaran());
            formPDAM.getTxtNama().setText(pdam.getNama());
            formPDAM.getCboJenisPelanggan().setSelectedItem(pdam.getJenisPelanggan());
            formPDAM.getTxtTanggal().setText(pdam.getTanggal());
            formPDAM.getTxtMeterBulanIni().setText(pdam.getMeterBulanIni());
            formPDAM.getTxtMeterBulanLalu().setText(pdam.getMeterBulanLalu());
            formPDAM.getTxtBiayaPerMeter().setText(pdam.getBiayaPerMeter());
            formPDAM.getTxtTotalBayar().setText(pdam.getTotalBayar());
        } catch (Exception ex) {
            Logger.getLogger(pdamControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update()
    {    
        try {
            pdam.setKodePembayaran(formPDAM.getTxtKodePembayaran().getText());
            pdam.setNama(formPDAM.getTxtNama().getText());
            pdam.setJenisPelanggan(formPDAM.getCboJenisPelanggan().getSelectedItem().toString());
            pdam.setTanggal(formPDAM.getTxtTanggal().getText());
            pdam.setMeterBulanIni(formPDAM.getTxtMeterBulanIni().getText());
            pdam.setMeterBulanLalu(formPDAM.getTxtMeterBulanLalu().getText());
            pdam.setBiayaPerMeter(formPDAM.getTxtBiayaPerMeter().getText());
            pdam.setTotalBayar(formPDAM.getTxtTotalBayar().getText());
            pdamDb.update(pdam);
            JOptionPane.showMessageDialog(formPDAM, "Update Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(pdamControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(){
        try {
            String kodePembayaran = formPDAM.getTabelPDAM()
                    .getValueAt(formPDAM.getTabelPDAM().getSelectedRow(), 0).toString();
            pdamDb.delete(kodePembayaran);
            JOptionPane.showMessageDialog(formPDAM, "Delete Data Ok");
        } catch (Exception ex) {
            Logger.getLogger(pdamControllerDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
