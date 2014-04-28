/**
 * 
 */
package test;

import utils.UtilsPassword;

/**
 * @author Lwaxana
 *
 */
public class Test5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String test = "Essai";
		String pass = UtilsPassword.encoderMotDePasse(test);
		System.out.println(pass);
		System.out.println(pass.length());
		

	}

}
