/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diurno
 */
public class mmagica {
    
    int[][] matrix;
    
    public mmagica(int n) {
        
        this.matrix = new int[n][n];
        
        int i = 0, j = n / 2;
        
        // Filling the matrix
                
        for (int k = 1; k <= Math.pow(n, 2); k++) {
            matrix[i][j] = k;
            
            // Checking if the number we add is a multiple of matrix's dimmension
            if (k % n == 0) {
                i++; // Placing the number below the last position if it is a multiple
            } else {
                i--; 
                j--;
                
                // Is it between the matrix's limits?
                if (i < 0) i = n - 1; // If not, we place it on the other side
                if (j < 0) j = n - 1; // If not, we place it on the other side
            }
        }
    
    }

    public int[][] getMatrix() {
        return matrix;
    }
    
}
