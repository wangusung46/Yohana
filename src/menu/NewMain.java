/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

/**
 *
 * @author Khanza
 */
public class NewMain {
    
    private static FormMenu formMenu;
//    private static String tes;
//    public String FormMenu(String tes){
//        tes = "TES";
//        this.tes = tes;
//        return this.tes;
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        formMenu = new FormMenu("TES");
        formMenu.setVisible(true);
        
    }
    
}
