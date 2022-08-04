package interfacesgraficas;

import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Alexandre
 */
public class SeletorArquivos extends javax.swing.JFrame {
    
    protected MainFrame mainMenu;
    
    public SeletorArquivos() {
        initComponents();
    }
    
    public void init(MainFrame main) {
        setVisible(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("isi");
        SeletorDeArquivos.addChoosableFileFilter(filter);
        mainMenu = main;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SeletorDeArquivos = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SeletorDeArquivos.setApproveButtonText("");
        SeletorDeArquivos.setApproveButtonToolTipText("");
        SeletorDeArquivos.setCurrentDirectory(new java.io.File("C:\\Users"));
        SeletorDeArquivos.setDialogTitle("Selecione o arquivo .isi para iniciar.");
        SeletorDeArquivos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        SeletorDeArquivos.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeletorDeArquivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SeletorDeArquivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser SeletorDeArquivos;
    // End of variables declaration//GEN-END:variables
}
