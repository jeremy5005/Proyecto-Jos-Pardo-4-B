package Aplicaci�n.View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Aplicaci�n.DAO.Conexi�nBD;

import javax.swing.JComboBox;
import javax.swing.JComboBox.KeySelectionManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrmCliente extends JInternalFrame {
	private JTextField TxtC�digo;
	private JTextField TxtNombres;
	private JTextField TxtRUC;
	private JTextField TxtDirecci�n;
	private JTextField TxtTel�fono;
	private JTable TblCliente;
	private JScrollPane scrollPane;
	private JComboBox CboEstadoCivil;
	private JComboBox CboSexo;
	private JButton BtnAgregar;
	private JButton BtnEliminar;
	private JButton BtnModificar;
	private JButton BtnLimpiar;
	private JTextField TxtBuscar;
	private JPopupMenu popupMenu;
    DefaultTableModel cliente;
    private JMenuItem mntmModificar;
    private JMenuItem mntmEliminar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IfrmCliente frame = new IfrmCliente();
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
	public IfrmCliente()
	{
		inicializar();
		funcionalidad();
		cargarcombos();
	    mostrartodo();
	}
	private void inicializar() {
		setIconifiable(true);
		setTitle("Cliente");
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 100, 479, 609);
		getContentPane().setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(48, 62, 71, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(48, 107, 71, 14);
		getContentPane().add(lblNombres);
		
		JLabel lblRuc = new JLabel("RUC:");
		lblRuc.setBounds(48, 159, 71, 14);
		getContentPane().add(lblRuc);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(48, 205, 71, 14);
		getContentPane().add(lblDireccin);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(48, 251, 71, 14);
		getContentPane().add(lblTelfono);
		
		JLabel lblNewLabel_1 = new JLabel("Sexo:");
		lblNewLabel_1.setBounds(48, 294, 82, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblEstadpCivil = new JLabel("Estado civil:");
		lblEstadpCivil.setBounds(48, 337, 82, 14);
		getContentPane().add(lblEstadpCivil);
		
		TxtC�digo = new JTextField();
		
		TxtC�digo.setBounds(129, 59, 128, 20);
		getContentPane().add(TxtC�digo);
		TxtC�digo.setColumns(10);
		
		TxtNombres = new JTextField();
		TxtNombres.setBounds(129, 104, 128, 20);
		getContentPane().add(TxtNombres);
		TxtNombres.setColumns(10);
		
		TxtRUC = new JTextField();
		TxtRUC.setBounds(129, 156, 128, 20);
		getContentPane().add(TxtRUC);
		TxtRUC.setColumns(10);
		
		TxtDirecci�n = new JTextField();
		TxtDirecci�n.setBounds(129, 202, 128, 20);
		getContentPane().add(TxtDirecci�n);
		TxtDirecci�n.setColumns(10);
		
		TxtTel�fono = new JTextField();
		TxtTel�fono.setBounds(129, 248, 128, 20);
		getContentPane().add(TxtTel�fono);
		TxtTel�fono.setColumns(10);
		
		CboSexo = new JComboBox();
		CboSexo.setBounds(124, 290, 86, 22);
		getContentPane().add(CboSexo);
		
		CboEstadoCivil = new JComboBox();
		CboEstadoCivil.setBounds(124, 333, 86, 22);
		getContentPane().add(CboEstadoCivil);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 378, 397, 176);
		getContentPane().add(scrollPane);
		
		popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);
		
		mntmModificar = new JMenuItem("Modificar");
		
		
		popupMenu.add(mntmModificar);
		
		mntmEliminar = new JMenuItem("Eliminar");
		
		popupMenu.add(mntmEliminar);
		
		TblCliente = new JTable();
		scrollPane.setViewportView(TblCliente);
		
		BtnAgregar = new JButton("Agregar");
		
		BtnAgregar.setBounds(292, 74, 91, 23);
		getContentPane().add(BtnAgregar);
		
		BtnModificar = new JButton("Modificar");
		
		BtnModificar.setBounds(292, 129, 91, 23);
		getContentPane().add(BtnModificar);
		
		BtnEliminar = new JButton("Eliminar");
		
		BtnEliminar.setBounds(292, 180, 91, 23);
		getContentPane().add(BtnEliminar);
		
		BtnLimpiar = new JButton("Limpiar");
		
		BtnLimpiar.setBounds(292, 230, 91, 23);
		getContentPane().add(BtnLimpiar);
		
		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setBounds(249, 294, 60, 14);
		getContentPane().add(lblBuscar);
		
		TxtBuscar = new JTextField();
		
		TxtBuscar.setBounds(319, 291, 111, 20);
		getContentPane().add(TxtBuscar);
		TxtBuscar.setColumns(10);
		TblCliente.setComponentPopupMenu(popupMenu);

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
          	
	}
	private void funcionalidad()
	{
		TxtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			filtrarcliente(TxtBuscar.getText());
			}
		});
		
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			mostrarselecci�n();
			}
		});
		
		BtnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			agregar();
			limpiar();
			mostrartodo();
			}
		});
		
		BtnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			modificar();
			limpiar();
			mostrartodo();
			}
		});
		BtnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			limpiar();
			mostrartodo();
			}
		});
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try
			{	
			eliminar();
			mostrartodo();
			}
			catch(java.lang.ArrayIndexOutOfBoundsException arg0)
			{
				JOptionPane.showMessageDialog(null, "No seleccion� nada","Cliente",JOptionPane.WARNING_MESSAGE);
			}
			
			}
		});
		BtnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{	
				eliminar();
				mostrartodo();
				}
				catch(java.lang.ArrayIndexOutOfBoundsException arg0)
				{
					JOptionPane.showMessageDialog(null, "No seleccion� nada","Cliente",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		TxtC�digo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				TxtNombres.requestFocus();
			}
			}
		});
		TxtNombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				TxtRUC.requestFocus();
			}
			}
		});
		
		TxtRUC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				TxtDirecci�n.requestFocus();
			}
			}
		});
		TxtDirecci�n.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				TxtTel�fono.requestFocus();
			}
			}
		});
		TxtTel�fono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				CboSexo.requestFocus();
			}
			}
		});
		CboSexo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				CboEstadoCivil.requestFocus();
			}
			}
		});
		CboEstadoCivil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				agregar();
				limpiar();
				mostrartodo();
			}
			}
		});
	}
	public void cargarcombos()
	{
		CboSexo.addItem("m");
		CboSexo.addItem("f");
		CboEstadoCivil.addItem("s");
		CboEstadoCivil.addItem("c");
		CboEstadoCivil.addItem("v");
		CboEstadoCivil.addItem("d");
	}
	
	public void mostrartodo()
	{
		Conexi�nBD conex=new Conexi�nBD();
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		String [] t�tulos={"C�digo","Nombres","RUC","Direcci�n","Tel�fono","Sexo","Estado civil"};
		String [] filas=new String[7];
		
		try {
			con=conex.ObtenerConexi�n();
			cliente=new DefaultTableModel(null,t�tulos);
			
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from Cliente");
			
			while(rs.next())
			{
				filas[0]=rs.getString("codcli");
				filas[1]=rs.getString("nombres");
				filas[2]=rs.getString("ruc");
				filas[3]=rs.getString("direccion");
				filas[4]=rs.getString("telefono");
				filas[5]=rs.getString("sexo");
				filas[6]=rs.getString("estadoCivil");
				
				cliente.addRow(filas);
			}
			
			TblCliente.setModel(cliente);
			con.close();
			stmt.close();
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void filtrarcliente(String valor)
	{
		Conexi�nBD conex=new Conexi�nBD();
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		String [] t�tulos={"C�digo","Nombres","RUC","Direcci�n","Tel�fono","Sexo","Estado civil"};
		String [] filas=new String[7];
		
		try {
			con=conex.ObtenerConexi�n();
			cliente=new DefaultTableModel(null,t�tulos);
			
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from Cliente where nombres like '%"+valor+"%'");
			
			while(rs.next())
			{
				filas[0]=rs.getString("codcli");
				filas[1]=rs.getString("nombres");
				filas[2]=rs.getString("ruc");
				filas[3]=rs.getString("direccion");
				filas[4]=rs.getString("telefono");
				filas[5]=rs.getString("sexo");
				filas[6]=rs.getString("estadoCivil");
				
				cliente.addRow(filas);
			}
			
			TblCliente.setModel(cliente);
			con.close();
			stmt.close();
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void agregar()
	{
		Conexi�nBD conex=new Conexi�nBD();
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=conex.ObtenerConexi�n();
			pstmt=con.prepareStatement("insert into cliente values(?,?,?,?,?,?,?)");
			pstmt.setString(1, TxtC�digo.getText());
			pstmt.setString(2, TxtNombres.getText());
			pstmt.setString(3, TxtRUC.getText());
			pstmt.setString(4, TxtDirecci�n.getText());
			pstmt.setString(5, TxtTel�fono.getText());
			pstmt.setString(6, String.valueOf(CboSexo.getSelectedItem()));
			pstmt.setString(7, String.valueOf(CboEstadoCivil.getSelectedItem()));
			
			int result=pstmt.executeUpdate();
			
			if(result>0)
			{
				JOptionPane.showMessageDialog(null, "Cliente registrado","Cliente",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Cliente no registrado","Cliente",JOptionPane.WARNING_MESSAGE);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void modificar()
	{
		Conexi�nBD conex=new Conexi�nBD();
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=conex.ObtenerConexi�n();
			pstmt=con.prepareStatement("update cliente set nombres=?,ruc=?,direccion=?,telefono=?,sexo=?,estadoCivil=? where codcli=?");
			
			pstmt.setString(1, TxtNombres.getText());
			pstmt.setString(2, TxtRUC.getText());
			pstmt.setString(3, TxtDirecci�n.getText());
			pstmt.setString(4, TxtTel�fono.getText());
			pstmt.setString(5, String.valueOf(CboSexo.getSelectedItem()));
			pstmt.setString(6, String.valueOf(CboEstadoCivil.getSelectedItem()));
			pstmt.setString(7, TxtC�digo.getText());
			int result=pstmt.executeUpdate();
			
			if(result>0)
			{
				JOptionPane.showMessageDialog(null, "Cliente actualizado","Cliente",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Cliente no actualizado","Cliente",JOptionPane.WARNING_MESSAGE);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void mostrarselecci�n()
	{
		int selecci�n=TblCliente.getSelectedRow();
		
		if(selecci�n>=0)
		{
			TxtC�digo.setText(String.valueOf(TblCliente.getValueAt(selecci�n, 0)));
			TxtNombres.setText(String.valueOf(TblCliente.getValueAt(selecci�n, 1)));
			TxtRUC.setText(String.valueOf(TblCliente.getValueAt(selecci�n, 2)));
			TxtDirecci�n.setText(String.valueOf(TblCliente.getValueAt(selecci�n, 3)));
			TxtTel�fono.setText(String.valueOf(TblCliente.getValueAt(selecci�n, 4)));
			CboSexo.setSelectedItem(TblCliente.getValueAt(selecci�n, 5));
			CboEstadoCivil.setSelectedItem(TblCliente.getValueAt(selecci�n, 6));
		}
		else
		{ 
			JOptionPane.showMessageDialog(null, "No ha seleccionado nada","Cliente",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void limpiar()
	{
		TxtC�digo.setText("");
		TxtNombres.setText("");
		TxtRUC.setText("");
		TxtDirecci�n.setText("");
		TxtTel�fono.setText("");
		CboSexo.setSelectedIndex(0);
		CboEstadoCivil.setSelectedIndex(0);
		TblCliente.clearSelection();
		TxtBuscar.setText("");
		TxtC�digo.requestFocus();
	}
	public void eliminar()
	{
		Conexi�nBD conex=new Conexi�nBD();
		PreparedStatement pstmt=null;
		Connection con=null;
		int selecci�n=TblCliente.getSelectedRow();
		
		try {
			con=conex.ObtenerConexi�n();
			pstmt=con.prepareStatement("delete from cliente where codcli=?");
			
			
			pstmt.setString(1, String.valueOf(TblCliente.getValueAt(selecci�n, 0)));
			int result=pstmt.executeUpdate();
			
			if(result>0)
			{
				JOptionPane.showMessageDialog(null, "Cliente eliminado","Cliente",JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Cliente no eliminado","Cliente",JOptionPane.WARNING_MESSAGE);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


