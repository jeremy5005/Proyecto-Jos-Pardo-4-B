package Aplicación.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmCliente;
	private JMenuItem mntmPedido;
    IfrmCliente cliente=new IfrmCliente();
    private JDesktopPane desktopPane;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
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
	public FrmPrincipal()
	{
		inicializar();
		funcionalidad();
		
	}
	private void inicializar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		mntmCliente = new JMenuItem("Cliente");
		
		
		mnMantenimiento.add(mntmCliente);
		
		mntmPedido = new JMenuItem("Pedido");
		mnMantenimiento.add(mntmPedido);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}
	
	private void funcionalidad()
	{
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(cliente.isVisible())
				{
					
				}
				else
				{
					
					desktopPane.add(cliente);
			        Dimension desktopSize = desktopPane.getSize();
			        Dimension FrameSize = cliente.getSize();
			        cliente.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
			        cliente.setVisible(true);
				}
			}
		});
		
	}
}
