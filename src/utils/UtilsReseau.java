/**
 * 
 */
package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Lwaxana
 *
 */
public class UtilsReseau {

	public static String getLocalIp(){
		InetAddress adresseloc = null;
		try {
			adresseloc = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresseloc.getHostAddress();		
	}
}
