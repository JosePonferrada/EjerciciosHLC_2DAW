/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author diurno
 */
public class ListaVuelos extends ArrayList<Vuelo> {
    
    public ListaVuelos() {
        
        this.add(new Vuelo("001", "Malaga", "Madrid", 150));
        this.add(new Vuelo("002", "Madrid", "Berlin", 350));
        this.add(new Vuelo("003", "Barcelona", "Madrid", 200));
        
    }
    
}
