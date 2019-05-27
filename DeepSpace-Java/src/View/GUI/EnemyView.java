/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.GUI;

import deepspace.EnemyToUI;

/**
 *
 * @author ana
 */
public class EnemyView extends javax.swing.JPanel {
    LootView lootView;
    DamageView damageView;

    /**
     * Creates new form EnemyView
     */
    public EnemyView() {
        initComponents();
        lootView = new LootView();
        damageView = new DamageView();

        LootPanel.add(lootView);
        DamagePanel.add(damageView);

        repaint();
        revalidate();
    }

    public void setEnemy(deepspace.EnemyToUI enemigo) {
        enemyName.setText(enemigo.getName());
        power.setText(Float.toString(enemigo.getAmmoPower()));
        shields.setText(Float.toString(enemigo.getShieldPower()));

        lootView.setLoot(enemigo.getLoot());
        damageView.setDamage(enemigo.getDamage());

        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enemyText = new javax.swing.JLabel();
        powerText = new javax.swing.JLabel();
        shieldText = new javax.swing.JLabel();
        enemyName = new javax.swing.JLabel();
        power = new javax.swing.JLabel();
        shields = new javax.swing.JLabel();
        LootPanel = new javax.swing.JPanel();
        DamagePanel = new javax.swing.JPanel();

        enemyText.setText("Enemigo:");
        add(enemyText);

        powerText.setText("Pontecia de fuego:");
        add(powerText);

        shieldText.setText("Capacidad defensiva:");
        add(shieldText);

        enemyName.setText("jLabel1");
        add(enemyName);

        power.setText("jLabel2");
        add(power);

        shields.setText("jLabel3");
        add(shields);
        add(LootPanel);
        add(DamagePanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DamagePanel;
    private javax.swing.JPanel LootPanel;
    private javax.swing.JLabel enemyName;
    private javax.swing.JLabel enemyText;
    private javax.swing.JLabel power;
    private javax.swing.JLabel powerText;
    private javax.swing.JLabel shieldText;
    private javax.swing.JLabel shields;
    // End of variables declaration//GEN-END:variables
}
