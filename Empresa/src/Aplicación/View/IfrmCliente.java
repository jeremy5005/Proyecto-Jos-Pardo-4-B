package Aplicación.View;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Aplicación.DAO.ConexiónBD;

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
	private JTextField TxtCódigo;
	private JTextField TxtNombres;
	private JTextField TxtRUC;
	private JTextField TxtDirección;
	private JTextField TxtTeléfono;
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
		
		TxtCódigo = new JTextField();
		
		TxtCódigo.setBounds(129, 59, 128, 20);
		getContentPane().add(TxtCódigo);
		TxtCódigo.setColumns(10);
		
		TxtNombres = new JTextField();
		TxtNombres.setBounds(129, 104, 128, 20);
		getContentPane().add(TxtNombres);
		TxtNombres.setColumns(10);
		
		TxtRUC = new JTextField();
		TxtRUC.setBounds(129, 156, 128, 20);
		getContentPane().add(TxtRUC);
		TxtRUC.setColumns(10);
		
		TxtDirección = new JTextField();
		TxtDirección.setBounds(129, 202, 128, 20);
		getContentPane().add(TxtDirección);
		TxtDirección.setColumns(10);
		
		TxtTeléfono = new JTextField();
		TxtTeléfono.setBounds(129, 248, 128, 20);
		getContentPane().add(TxtTeléfono);
		TxtTeléfono.setColumns(10);
		
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
			mostrarselección();
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
				JOptionPane.showMessageDialog(null, "No seleccionó nada","Cliente",JOptionPane.WARNING_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "No seleccionó nada","Cliente",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		TxtCódigo.addKeyListener(new KeyAdapter() {
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
				TxtDirección.requestFocus();
			}
			}
		});
		TxtDirección.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				TxtTeléfono.requestFocus();
			}
			}
		});
		TxtTeléfono.addKeyListener(new KeyAdapter() {
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
		ConexiónBD conex=new ConexiónBD();
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		String [] títulos={"Código","Nombres","RUC","Dirección","Teléfono","Sexo","Estado civil"};
		String [] filas=new String[7];
		
		try {
			con=conex.ObtenerConexión();
			cliente=new DefaultTableModel(null,títulos);
			
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
		ConexiónBD conex=new ConexiónBD();
		Statement stmt=null;
		Connection con=null;
		ResultSet rs=null;
		String [] títulos={"Código","Nombres","RUC","Dirección","Teléfono","Sexo","Estado civil"};
		String [] filas=new String[7];
		
		try {
			con=conex.ObtenerConexión();
			cliente=new DefaultTableModel(null,títulos);
			
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
		ConexiónBD conex=new ConexiónBD();
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=conex.ObtenerConexión();
			pstmt=con.prepareStatement("insert into cliente values(?,?,?,?,?,?,?)");
			pstmt.setString(1, TxtCódigo.getText());
			pstmt.setString(2, TxtNombres.getText());
			pstmt.setString(3, TxtRUC.getText());
			pstmt.setString(4, TxtDirección.getText());
			pstmt.setString(5, TxtTeléfono.getText());
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
		ConexiónBD conex=new ConexiónBD();
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try {
			con=conex.ObtenerConexión();
			pstmt=con.prepareStatement("update cliente set nombres=?,ruc=?,direccion=?,telefono=?,sexo=?,estadoCivil=? where codcli=?");
			
			pstmt.setString(1, TxtNombres.getText());
			pstmt.setString(2, TxtRUC.getText());
			pstmt.setString(3, TxtDirección.getText());
			pstmt.setString(4, TxtTeléfono.getText());
			pstmt.setString(5, String.valueOf(CboSexo.getSelectedItem()));
			pstmt.setString(6, String.valueOf(CboEstadoCivil.getSelectedItem()));
			pstmt.setString(7, TxtCódigo.getText());
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
	
	public void mostrarselección()
	{
		int selección=TblCliente.getSelectedRow();
		
		if(selección>=0)
		{
			TxtCódigo.setText(String.valueOf(TblCliente.getValueAt(selección, 0)));
			TxtNombres.setText(String.valueOf(TblCliente.getValueAt(selección, 1)));
			TxtRUC.setText(String.valueOf(TblCliente.getValueAt(selección, 2)));
			TxtDirección.setText(String.valueOf(TblCliente.getValueAt(selección, 3)));
			TxtTeléfono.setText(String.valueOf(TblCliente.getValueAt(selección, 4)));
			CboSexo.setSelectedItem(TblCliente.getValueAt(selección, 5));
			CboEstadoCivil.setSelectedItem(TblCliente.getValueAt(selección, 6));
		}
		else
		{ 
			JOptionPane.showMessageDialog(null, "No ha seleccionado nada","Cliente",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void limpiar()
	{
		TxtCódigo.setText("");
		TxtNombres.setText("");
		TxtRUC.setText("");
		TxtDirección.setText("");
		TxtTeléfono.setText("");
		CboSexo.setSelectedIndex(0);
		CboEstadoCivil.setSelectedIndex(0);
		TblCliente.clearSelection();
		TxtBuscar.setText("");
		TxtCódigo.requestFocus();
	}
	public void eliminar()
	{
		ConexiónBD conex=new ConexiónBD();
		PreparedStatement pstmt=null;
		Connection con=null;
		int selección=TblCliente.getSelectedRow();
		
		try {
			con=conex.ObtenerConexión();
			pstmt=con.prepareStatement("delete from cliente where codcli=?");
			
			
			pstmt.setString(1, String.valueOf(TblCliente.getValueAt(selección, 0)));
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


