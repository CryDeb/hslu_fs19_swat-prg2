package connect4.views;

import java.awt.FlowLayout;

class AboutDialog extends javax.swing.JDialog {

    public AboutDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
	
    private void initComponents() {
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        this.setSize(200, 150);
        setAlwaysOnTop(true);
        setResizable(false);
        FlowLayout layout = new FlowLayout();
        getContentPane().setLayout(layout);
		
        jLabel1.setText("Help me i am stuck inside this computer");

        add(jLabel1);

        pack();
    }
}
