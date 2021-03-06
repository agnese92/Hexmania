/*
 * FirstWin.java
 *
 * TODO:
 * PODŚWIETLANIE OBSZARÓW
 * jednostka, baza
 * zmiana polozenia jednostki - click, podświetlenie hexa, click na wolne pole:
 *  hex jest wolny, czy może się tam przesunąć (podświetlanie dostępnego obszaru),
 * podział mapy na pół,
 * odpowiednia mapa - ustawienie grup hexów-państw na stałe w kodzie,
 * 
 */
package org.yary.realhexgen;

import java.awt.Color;
import java.awt.event.MouseEvent;
import org.yary.realhexgen.controller.events.HexEventHandler;
import org.yary.realhexgen.controller.events.HexEventRegister;
import org.yary.realhexgen.controller.events.map.RedrawMap;
import org.yary.realhexgen.controller.events.tile.RedrawTile;
import org.yary.realhexgen.controller.events.tile.TilesDistance;
import org.yary.realhexgen.model.ConfigurationData;
import org.yary.realhexgen.model.map.MapModel;
import org.yary.realhexgen.model.map.TileModel;
import org.yary.realhexgen.model.WindowModel;
import org.yary.realhexgen.view.MapView;

/**
 *
 * @author Yary Ribero
 */
public class FirstWin extends javax.swing.JFrame {

    private WindowModel windowModel;
    private MapModel mapModel;
    private MapView mapView;

    int oldX;
    int oldY;
    
    // DO USTALENIA (pod mapę)
    public static final int mapRows = 15;
    public static final int mapColumns = 15;

    /** Creates new form FirstWin */
    public FirstWin() {
        initComponents();

        try {
            mapModel = new MapModel ();
            mapView = new MapView ( mapModel );
            windowModel = new WindowModel ( mapView );
        } catch ( Exception e ) {
            e.printStackTrace ( System.err );
        }
        //drawSpace.setBackground(Color.yellow);// I CHANGED
        windowModel.setDrawSpace ( drawSpace );
        

        HexEventRegister.getInstance ().registerHandlerForEvent (
            RedrawTile.class,
            new HexEventHandler < RedrawTile > () {
                @Override
                public void handle ( RedrawTile event ) throws Exception {
                    TileModel tile = event.getTile ();

                    if ( tile == null )
                        throw new Exception ( "Tile bound the redraw event is null" );

                    if ( //the tile is not visible in the drawSpace
                        (
                            tile.getEndX () < windowModel.getOffsetX () &&
                            tile.getEndY () < windowModel.getOffsetY ()
                        ) ||
                        (
                            tile.getStartX () > windowModel.getOffsetX () + drawSpace.getWidth () &&
                            tile.getStartY () > windowModel.getOffsetY () + drawSpace.getHeight ()
                        )
                    )
                        return; //no need to draw;

                    int mapStartX = Math.max ( tile.getStartX () - windowModel.getOffsetX (), 0 );
                    int mapStartY = Math.max ( tile.getStartY () - windowModel.getOffsetY (), 0 );
                    int mapEndX = Math.min ( tile.getEndX () - windowModel.getOffsetX (), drawSpace.getWidth () );
                    int mapEndY = Math.min ( tile.getEndY () - windowModel.getOffsetY (), drawSpace.getHeight () );

                    int imgStartX = mapStartX + windowModel.getOffsetX ();
                    int imgStartY = mapStartY + windowModel.getOffsetY ();
                    int imgEndX = mapEndX + windowModel.getOffsetX ();
                    int imgEndY = mapEndY + windowModel.getOffsetY ();

                    drawSpace.getGraphics ().drawImage (
                        mapView.getMap (),
                        mapStartX,
                        mapStartY,
                        mapEndX,
                        mapEndY,
                        imgStartX,
                        imgStartY,
                        imgEndX,
                        imgEndY,
                        drawSpace
                    );
                }
            },
            "mapRequest"
        );

        HexEventRegister.getInstance ().registerHandlerForEvent (
            RedrawMap.class,
            new HexEventHandler < RedrawMap > () {
                @Override
                public void handle ( RedrawMap event ) throws Exception {
                    redrawMap ();
                }
            },
            "mapRequest"
        );

        HexEventRegister.getInstance ().registerHandlerForEvent (
            TilesDistance.class,
            new HexEventHandler < TilesDistance > () {
                @Override
                public void handle ( TilesDistance event ) throws Exception {
                    distanceField.setText ( String.valueOf ( event.getDistance () ) );
                }
            }
        );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        yField = new javax.swing.JTextField();
        xField = new javax.swing.JTextField();
        startYfield = new javax.swing.JTextField();
        rowField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        columnField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edgeField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        distanceField = new javax.swing.JTextField();
        startXfield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        drawSpace = new javax.swing.JPanel();
        generate = new javax.swing.JButton();

        jLabel1.setText("rows");

        jLabel7.setText("columns");

        jLabel2.setText("size");

        edgeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edgeFieldActionPerformed(evt);
            }
        });

        jLabel8.setText("distance");

        jLabel3.setText("startX");

        jLabel4.setText("startY");

        jLabel5.setText("x");

        jLabel6.setText("y");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hexmania");
        setResizable(false);

        drawSpace.setMinimumSize(new java.awt.Dimension(922, 560));
        drawSpace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawSpaceMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawSpaceMousePressed(evt);
            }
        });
        drawSpace.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                drawSpaceComponentResized(evt);
            }
        });
        drawSpace.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                drawSpaceMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout drawSpaceLayout = new javax.swing.GroupLayout(drawSpace);
        drawSpace.setLayout(drawSpaceLayout);
        drawSpaceLayout.setHorizontalGroup(
            drawSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
        );
        drawSpaceLayout.setVerticalGroup(
            drawSpaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        generate.setText("Start");
        generate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generateMouseClicked(evt);
            }
        });
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(generate)
                .addGap(5, 5, 5)
                .addComponent(drawSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void generateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generateMouseClicked

    try {
        ConfigurationData.getInstance ().setEdge ( Integer.parseInt ( edgeField.getText () ) );
    } catch ( Exception e ) {
        //e.printStackTrace( System.err );
    }

    edgeField.setText ( String.valueOf ( ConfigurationData.getInstance ().getEdge () ) );

    try {
        ConfigurationData.getInstance ().setRows ( mapRows );
        //ConfigurationData.getInstance ().setRows ( Integer.parseInt ( rowField.getText () ) );
    } catch ( Exception e ) {
        //ConfigurationData.getInstance ().setRows ( 40 );//changed - 18
        e.printStackTrace( System.err );
    }

    rowField.setText ( String.valueOf ( ConfigurationData.getInstance ().getRows () ) );

    try {
        ConfigurationData.getInstance ().setColumns ( mapColumns );
        //ConfigurationData.getInstance ().setColumns ( Integer.parseInt ( columnField.getText () ) );
    } catch ( Exception e ) {
        //ConfigurationData.getInstance ().setColumns ( 46 );// changed 22
        e.printStackTrace( System.err );
    }

    columnField.setText ( String.valueOf ( ConfigurationData.getInstance ().getColumns () ) );

    ConfigurationData.getInstance ().ready ();

    drawSpace.getGraphics().drawImage ( mapView.getMap (), 0, 0, drawSpace );
}//GEN-LAST:event_generateMouseClicked

private void drawSpaceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawSpaceMouseClicked

    startXfield.setText ( String.valueOf ( evt.getX () ) );
    startYfield.setText ( String.valueOf ( evt.getY () ) );

    int x = 0;
    int y = 0;

    int clickX = evt.getX () + windowModel.getOffsetX ();
    int clickY = evt.getY () + windowModel.getOffsetY ();

    if ( mouseXinBlackArea ( clickX, clickY ) || mouseYinBlackArea ( clickX, clickY )  ) {
        xField.setText ( "out" );
        yField.setText ( "out" );
        return;
    }

    //Iterative translation
    //1) Diagonal translation
    while (
        clickX - 3 * ConfigurationData.getInstance ().getEdge () / 2 >= 0 &&
        !mouseXinBlackArea ( clickX - 3 * ConfigurationData.getInstance ().getEdge () / 2, clickY - ConfigurationData.getInstance ().getHeight () / 2 ) &&
        !mouseYinBlackArea ( clickX - 3 * ConfigurationData.getInstance ().getEdge () / 2, clickY - ConfigurationData.getInstance ().getHeight () / 2 )
    ) {
        clickX -= 3 * ConfigurationData.getInstance ().getEdge () / 2;
        clickY -= ( ConfigurationData.getInstance ().getHeight () / 2 );
        x++;
        y++;
    }

    while ( clickX - 3 * ConfigurationData.getInstance ().getEdge () >= 0 ) {
        clickX -= 3 * ConfigurationData.getInstance ().getEdge ();
        x += 2;
    }

    while ( clickY - ConfigurationData.getInstance ().getHeight () >= 0 ) {
        clickY -= ConfigurationData.getInstance ().getHeight ();
        y += 2;
    }

    int row = y;
    int column = x;

    if ( evt.getButton() == MouseEvent.BUTTON3 ) {
        //mapModel.setTileUnreachable ( row, column );
        return;
    }

    if (
        ( row < ConfigurationData.getInstance ().getRows () ) &&
        ( column < ConfigurationData.getInstance ().getColumns () )
    ) {
        xField.setText ( String.valueOf ( x ) );
        yField.setText ( String.valueOf ( y ) );

        if ( mapModel != null ) {
            try {
                mapModel.setSelectedTile ( row, column );
            } catch ( Exception e ) {
                e.printStackTrace ( System.err );
            }
        }
    } else {
        xField.setText ( "out" );
        yField.setText ( "out" );
    }
}//GEN-LAST:event_drawSpaceMouseClicked

private void drawSpaceComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_drawSpaceComponentResized
    redrawMap ();
}//GEN-LAST:event_drawSpaceComponentResized

private void drawSpaceMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawSpaceMouseDragged
    if (
        evt.getX () < 0 ||
        evt.getX () > drawSpace.getWidth () - 1 ||
        evt.getY () < 0 ||
        evt.getY () > drawSpace.getHeight () - 1
    )
        return;

    windowModel.incrementOffsetX ( oldX - evt.getX () );
    windowModel.incrementOffsetY ( oldY - evt.getY () );

    oldX = evt.getX ();
    oldY = evt.getY ();

    redrawMap ();
}//GEN-LAST:event_drawSpaceMouseDragged

private void drawSpaceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawSpaceMousePressed
    oldX = evt.getX ();
    oldY = evt.getY ();
}//GEN-LAST:event_drawSpaceMousePressed

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateActionPerformed

    private void edgeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edgeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edgeFieldActionPerformed

    private boolean mouseXinBlackArea ( int x, int y ) {
        if ( y < 0  || x < 0 )
            return true;

        if ( x > ConfigurationData.getInstance ().getEdge () / 2 - 1 )
            return false;

        y = y % ConfigurationData.getInstance ().getHeight ();

        if ( x < mapModel.slopeGap ( y ) )
            return true;

        return false;
    }

    private boolean mouseYinBlackArea ( int x, int y ) {
        if ( y < 0  || x < 0 )
            return true;

        if ( y > ConfigurationData.getInstance ().getHeight () / 2 - 1 )
            return false;

        x = x % ( 3 * ConfigurationData.getInstance ().getEdge () );

        if ( x < mapModel.slopeGap ( y ) || x > 2 * ConfigurationData.getInstance ().getEdge () - mapModel.slopeGap ( y ) )
            return true;

        return false;
    }
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
            java.util.logging.Logger.getLogger(FirstWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FirstWin fw = new FirstWin();
                fw.setTitle("Hexmania");
                fw.setVisible(true);
            }
        });
    }
    
    private void redrawMap () {

        if ( mapView == null || mapView.getMap () == null)
            return;

        drawSpace.getGraphics().drawImage (
            mapView.getMap (),
            0,
            0,
            Math.min ( drawSpace.getWidth (), mapView.getMap ().getWidth () ),
            Math.min ( drawSpace.getHeight (), mapView.getMap ().getHeight () ),
            windowModel.getOffsetX (),
            windowModel.getOffsetY (),
            Math.min ( windowModel.getOffsetX () + drawSpace.getWidth (), mapView.getMap ().getWidth () ),
            Math.min ( windowModel.getOffsetY () + drawSpace.getHeight (), mapView.getMap ().getHeight () ),
            this
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField columnField;
    private javax.swing.JTextField distanceField;
    private javax.swing.JPanel drawSpace;
    private javax.swing.JTextField edgeField;
    private javax.swing.JButton generate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField rowField;
    private javax.swing.JTextField startXfield;
    private javax.swing.JTextField startYfield;
    private javax.swing.JTextField xField;
    private javax.swing.JTextField yField;
    // End of variables declaration//GEN-END:variables
}
