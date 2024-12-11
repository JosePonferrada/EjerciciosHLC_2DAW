/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 
 */
public class Usuario {
    private String usuario;
    private String password;
    private double saldo;
    private String colorPreferido;
    private String fuentePreferido;

    public Usuario(String usuario, String password, double saldo, String colorPreferido, String fuentePreferido) {
        this.usuario = usuario;
        this.password = password;
        this.saldo = saldo;
        this.colorPreferido = colorPreferido;
        this.fuentePreferido = fuentePreferido;
    }

    public String getColorPreferido() {
        return colorPreferido;
    }

    public void setColorPreferido(String colorPreferido) {
        this.colorPreferido = colorPreferido;
    }

    public String getFuentePreferido() {
        return fuentePreferido;
    }

    public void setFuentePreferido(String fuentePreferido) {
        this.fuentePreferido = fuentePreferido;
    }
    
    /**
     * Default Constructor.
     */
    public Usuario() {
        super();
    }

    /**
     * Constructor with fields.
     */
    public Usuario(String usuario, String password, double saldo) {
        this.usuario = usuario;
        this.password = password;
        this.saldo = saldo;
    }
    
    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
