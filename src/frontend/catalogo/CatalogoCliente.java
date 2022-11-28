package frontend.catalogo;

import java.awt.BorderLayout;
import javax.swing.JFrame;
public class CatalogoCliente extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private PanelProductos panelProductos;	
	
	//En esta clase solo se agrega el panel de Productos al Frame para mostrarlo
	public CatalogoCliente() {
		setTitle("CATÁLOGO DE PRODUCTOS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(520,458);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		
		panelProductos = new PanelProductos(this);
		getContentPane().add(panelProductos,BorderLayout.CENTER);
		
	}


}
