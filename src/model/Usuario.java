package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;

public class Usuario { 
	
	public static SimpleStringProperty ingreso = new SimpleStringProperty ();
	
	public static SimpleStringProperty gasto = new SimpleStringProperty ();
	
	public SimpleDateFormat fecha = new SimpleDateFormat();
	
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha.get2DigitYearStart();
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(SimpleDateFormat fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the ingreso
	 */
	public String getIngreso() {
		return ingreso.get();
	}

	/**
	 * @param ingreso the ingreso to set
	 */
	public void setIngreso(SimpleStringProperty ingreso) {
		Usuario.ingreso = ingreso;
	}

	/**
	 * @return the gasto
	 */
	public String getGasto() {
		return gasto.get();
	}

	/**
	 * @param gasto the gasto to set
	 */
	public void setGasto(SimpleStringProperty gasto) {
		Usuario.gasto = gasto;
	}

	
	
	
}
