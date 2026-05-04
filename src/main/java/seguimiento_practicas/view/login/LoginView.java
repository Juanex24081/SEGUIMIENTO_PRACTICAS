package seguimiento_practicas.view.login;

import seguimiento_practicas.controller.LoginController;

public class LoginView extends javax.swing.JFrame {

    LoginController controller = new LoginController();

    public LoginView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        txtCorreo = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtCorreo.setBorder(javax.swing.BorderFactory.createTitledBorder("Correo"));
        txtPass.setBorder(javax.swing.BorderFactory.createTitledBorder("Contraseña"));

        btnLogin.setText("Iniciar sesión");
        btnLogin.addActionListener(evt -> btnLoginActionPerformed());

        setLayout(new java.awt.GridLayout(3,1));
        add(txtCorreo);
        add(txtPass);
        add(btnLogin);

        pack();
    }

    private void btnLoginActionPerformed() {
        String correo = txtCorreo.getText();
        String pass = new String(txtPass.getPassword());

        controller.login(correo, pass, this);
    }

    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JButton btnLogin;
}
