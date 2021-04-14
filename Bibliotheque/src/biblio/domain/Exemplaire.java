package biblio.domain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Exemplaire {
	@Id @GeneratedValue ( strategy =GenerationType.SEQUENCE)
	private int idExemplaire;
	@Column
	private LocalDate dateAchat;
	@Column
	private EnumStatusExemplaire status;
	public DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.FRANCE );
	SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
	@Column
	private String isbn;
	
	public Exemplaire(int idExemplaire,String dateAchat,EnumStatusExemplaire status,String isbn) {
		this.setIsbn(isbn);
		this.setIdExemplaire(idExemplaire);
		this.setDateAchat(dateAchat);
		this.setStatus(status);
		
	}
	public Exemplaire() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Exemplaire [idExemplaire = " + idExemplaire + ", dateAchat=" + dateAchat.format(df) + ", status=" + status + ", isbn=" + isbn + "]";
	}

	public String getIsbn() {
		
		return isbn;
	}

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = LocalDate.parse(dateAchat);
	}

	public int getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(int idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public EnumStatusExemplaire getStatus() {
		return status;
	}

	public void setStatus(EnumStatusExemplaire status) {
		this.status=status;
	}
	

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
}