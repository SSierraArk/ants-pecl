/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sergiosierra.ants.views;

import com.sergiosierra.ants.util.Config;
import javax.swing.ImageIcon;

/**
 *
 * @author ssierra
 */
public class ServerView extends javax.swing.JFrame {

    
    /**
     * Creates new form serverView
     */
    public ServerView() {
        initComponents();
        setIcon();
    }

    public void setIcon() {
    
        ImageIcon ii = new ImageIcon(Config.APP_ICON);
        
        this.setIconImage(ii.getImage());
    
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        foodInEatingZoneText = new javax.swing.JTextField();
        eatingZoneLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        eatingZoneArea = new javax.swing.JTextArea();
        foodInEatingZoneLabel = new javax.swing.JLabel();
        foodInStorageText = new javax.swing.JTextField();
        antsRestingText = new javax.swing.JTextField();
        foodInStorageLabel = new javax.swing.JLabel();
        soldiersTrainingText = new javax.swing.JTextField();
        antsTransportingFoodText = new javax.swing.JTextField();
        antsInFoodStorageText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        soldiersFightingArea = new javax.swing.JTextArea();
        antsRestingLabel = new javax.swing.JLabel();
        soldiersTrainingLabel = new javax.swing.JLabel();
        antsTransportingFoodLabel = new javax.swing.JLabel();
        antsInFoodStorageLabel = new javax.swing.JLabel();
        antsCollectingFoodText = new javax.swing.JTextField();
        soldiersFightingLabel = new javax.swing.JLabel();
        antsCollectingFoodLabel = new javax.swing.JLabel();
        InsideLabel = new javax.swing.JLabel();
        OutsideLabel = new javax.swing.JLabel();
        shelterLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        shelterArea = new javax.swing.JTextArea();
        pauseExecBtn = new javax.swing.JButton();
        restartExecBtn = new javax.swing.JButton();
        generateThreatBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Colony Lookup - server-side");
        setResizable(false);

        foodInEatingZoneText.setName("foodInEatingZoneText"); // NOI18N

        eatingZoneLabel.setText("ZONA PARA COMER");

        jScrollPane2.setName("eatingZoneScrollPane"); // NOI18N

        eatingZoneArea.setColumns(20);
        eatingZoneArea.setLineWrap(true);
        eatingZoneArea.setRows(5);
        eatingZoneArea.setName("eatingZoneArea"); // NOI18N
        jScrollPane2.setViewportView(eatingZoneArea);

        foodInEatingZoneLabel.setText("Uds. comida (COMEDOR):");

        foodInStorageText.setName("foodInStorageText"); // NOI18N

        antsRestingText.setName("antsRestingText"); // NOI18N

        foodInStorageLabel.setText("Uds. comida (ALMACÉN):");

        soldiersTrainingText.setName("soldiersTrainingText"); // NOI18N

        antsTransportingFoodText.setName("antsTransportingFoodText"); // NOI18N

        antsInFoodStorageText.setName("antsInFoodStorageText"); // NOI18N

        jScrollPane1.setName("soldiersFightingScrollPane"); // NOI18N

        soldiersFightingArea.setColumns(20);
        soldiersFightingArea.setLineWrap(true);
        soldiersFightingArea.setRows(5);
        soldiersFightingArea.setName("soldiersFightingArea"); // NOI18N
        jScrollPane1.setViewportView(soldiersFightingArea);

        antsRestingLabel.setText("Hormigas descansando:");

        soldiersTrainingLabel.setText("Hormigas haciendo INSTRUCCIÓN:");

        antsTransportingFoodLabel.setText("Hormigas llevando comida a la ZONA PARA COMER:");

        antsInFoodStorageLabel.setText("Hormigas en el ALMACÉN DE COMIDA:");

        antsCollectingFoodText.setName("antsCollectingFoodText"); // NOI18N
        antsCollectingFoodText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antsCollectingFoodTextActionPerformed(evt);
            }
        });

        soldiersFightingLabel.setText("Hormigas repeliendo un insecto invasor:");
        soldiersFightingLabel.setName("soldiersFightingLabel"); // NOI18N

        antsCollectingFoodLabel.setText("Hormigas buscando comida:");

        InsideLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        InsideLabel.setText("Interior de la colonia:");

        OutsideLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        OutsideLabel.setText("Exterior de la colonia:");

        shelterLabel.setText("REFUGIO");

        jScrollPane3.setName("shelterScrollPane"); // NOI18N

        shelterArea.setColumns(20);
        shelterArea.setLineWrap(true);
        shelterArea.setRows(5);
        shelterArea.setName("shelterArea"); // NOI18N
        jScrollPane3.setViewportView(shelterArea);

        pauseExecBtn.setText("Pausar");
        pauseExecBtn.setName("pauseExecBtn"); // NOI18N
        pauseExecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseExecBtnActionPerformed(evt);
            }
        });

        restartExecBtn.setText("Reanudar");
        restartExecBtn.setName("restartExecBtn"); // NOI18N

        generateThreatBtn.setText("Generar Amenaza Insecto Invasor");
        generateThreatBtn.setName("generateThreatBtn"); // NOI18N
        generateThreatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateThreatBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InsideLabel)
                    .addComponent(OutsideLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(shelterLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pauseExecBtn)
                                .addGap(18, 18, 18)
                                .addComponent(restartExecBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(generateThreatBtn))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eatingZoneLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(antsCollectingFoodLabel)
                                .addGap(18, 18, 18)
                                .addComponent(antsCollectingFoodText))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(soldiersFightingLabel)
                                    .addComponent(antsInFoodStorageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(antsInFoodStorageText, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(soldiersTrainingLabel)
                                .addGap(18, 18, 18)
                                .addComponent(soldiersTrainingText))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(antsTransportingFoodLabel)
                                .addGap(18, 18, 18)
                                .addComponent(antsTransportingFoodText))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(antsRestingLabel)
                                .addGap(18, 18, 18)
                                .addComponent(antsRestingText, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(foodInEatingZoneLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                        .addComponent(foodInEatingZoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(foodInStorageLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(foodInStorageText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(31, 31, 31)))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(OutsideLabel)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(antsCollectingFoodText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(antsCollectingFoodLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soldiersFightingLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(InsideLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(antsInFoodStorageLabel)
                    .addComponent(antsInFoodStorageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(antsTransportingFoodLabel)
                    .addComponent(antsTransportingFoodText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soldiersTrainingLabel)
                    .addComponent(soldiersTrainingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(foodInStorageLabel)
                        .addComponent(foodInStorageText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(antsRestingLabel)
                            .addComponent(antsRestingText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foodInEatingZoneLabel)
                    .addComponent(foodInEatingZoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eatingZoneLabel)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shelterLabel)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseExecBtn)
                    .addComponent(restartExecBtn)
                    .addComponent(generateThreatBtn))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pauseExecBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseExecBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pauseExecBtnActionPerformed

    private void generateThreatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateThreatBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateThreatBtnActionPerformed

    private void antsCollectingFoodTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antsCollectingFoodTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_antsCollectingFoodTextActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel InsideLabel;
    private javax.swing.JLabel OutsideLabel;
    private javax.swing.JLabel antsCollectingFoodLabel;
    private javax.swing.JTextField antsCollectingFoodText;
    private javax.swing.JLabel antsInFoodStorageLabel;
    private javax.swing.JTextField antsInFoodStorageText;
    private javax.swing.JLabel antsRestingLabel;
    private javax.swing.JTextField antsRestingText;
    private javax.swing.JLabel antsTransportingFoodLabel;
    private javax.swing.JTextField antsTransportingFoodText;
    private javax.swing.JTextArea eatingZoneArea;
    private javax.swing.JLabel eatingZoneLabel;
    private javax.swing.JLabel foodInEatingZoneLabel;
    private javax.swing.JTextField foodInEatingZoneText;
    private javax.swing.JLabel foodInStorageLabel;
    private javax.swing.JTextField foodInStorageText;
    private javax.swing.JButton generateThreatBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton pauseExecBtn;
    private javax.swing.JButton restartExecBtn;
    private javax.swing.JTextArea shelterArea;
    private javax.swing.JLabel shelterLabel;
    private javax.swing.JTextArea soldiersFightingArea;
    private javax.swing.JLabel soldiersFightingLabel;
    private javax.swing.JLabel soldiersTrainingLabel;
    private javax.swing.JTextField soldiersTrainingText;
    // End of variables declaration//GEN-END:variables

}


