/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author diurno
 */
public class listausuarios extends ArrayList<Usuario> {
    
    public listausuarios() {
        
        this.add(new Usuario("pepe", "pepa"));
        this.add(new Usuario("paco", "paca"));
        this.add(new Usuario("juan", "juana"));
        
        
    }
}
