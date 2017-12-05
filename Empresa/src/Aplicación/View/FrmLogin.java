package Aplicación.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplicación.DAO.ConexiónBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField TxtUsuario;
	private JPasswordField TxtContraseña;
	private JButton BtnIngresar;
	private JButton BtnCancelar;
	private JButton BtnSalir;
	FrmPrincipal p=new FrmPrincipal();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin()
	{
		inicializar();
		funcionalidad();
		
	}
	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(66, 70, 88, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(66, 124, 88, 14);
		contentPane.add(lblContrasea);
		
		TxtUsuario = new JTextField();
		
		TxtUsuario.setBounds(164, 67, 86, 20);
		contentPane.add(TxtUsuario);
		TxtUsuario.setColumns(10);
		
		TxtContraseña = new JPasswordField();
		TxtContraseña.setBounds(164, 121, 86, 20);
		contentPane.add(TxtContraseña);
		
		BtnIngresar = new JButton("Ingresar");
		
		BtnIngresar.setBounds(66, 198, 91, 23);
		contentPane.add(BtnIngresar);
		
		BtnCancelar = new JButton("Cancelar");
		
		BtnCancelar.setBounds(177, 198, 91, 23);
		contentPane.add(BtnCancelar);
		
		BtnSalir = new JButton("Salir");
		
		BtnSalir.setBounds(291, 198, 91, 23);
		contentPane.add(BtnSalir);
	}
	
	private void funcionalidad()
	{
		BtnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			salir();
			}
		});
		BtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			limpiar();
			}
		});
		BtnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ingresar();
			}
		});
		TxtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				TxtContraseña.requestFocus();
			}
			}
		});
		TxtContraseña.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				ingresar();
			}
			}
		});
	}
	
	public void ingresar()
	{
		ConexiónBD conex=new ConexiónBD();
		Connection con=null;
		String usuario=TxtUsuario.getText();
		char[] contra=TxtContraseña.getPassword();
		String contraseña=new String(contra);
        Statement stmt=null;		
		ResultSet rs=null;
        try {
			con=conex.ObtenerConexión();
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from Usuario where UserName='"+usuario+"' and Contraseña='"+contraseña+"'");
			
			if(rs!=null)
			{
				if(rs.next())
				{
					switch(rs.getString("TipoUsuario"))
					{
					case "Administrador":
						dispose();
						JOptionPane.showMessageDialog(null, "Bienvenido administrador","Login",JOptionPane.INFORMATION_MESSAGE);
						p.setVisible(true);
						break;
					case "Usuario":
						break;
					default:
						JOptionPane.showMessageDialog(null, "No existe ese tipo de usuario","Login",JOptionPane.WARNING_MESSAGE);
						break;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Usuario no registrado","Login",JOptionPane.WARNING_MESSAGE);
					
				}	

			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void limpiar()
	{
		TxtUsuario.setText("");
		TxtContraseña.setText("");
		TxtUsuario.requestFocus();
	}
	
	public void salir()
	{
		int opc=JOptionPane.showConfirmDialog(null, "¿Desea salir del login?","Login",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		
		if(opc==JOptionPane.YES_OPTION)
		{
			dispose();
		}
		else
		{
			
		}
	}
}
