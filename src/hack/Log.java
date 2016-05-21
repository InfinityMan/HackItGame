/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.Serializable;

/**
 *
 * @author Dima
 */
public class Log implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Type {FILE_DELETED,FILE_COPIED,PROTECT_DISABLED,USER_CONNECTED,USER_DISCONNECTED}; //if u change this - change the constructor switch
    
    public Type type;
    
    public String id,message; //id for number of log in sys
    
    public Log(Type type, String lastID, String userIP) {
        
        this.type = type;
        
        switch(type) {
            case FILE_DELETED :
                message = "File deleted by "+userIP;
                break;
            case FILE_COPIED :
                message = "File copied by "+userIP;
                break;
            case PROTECT_DISABLED :
                message = "Protect disabled by "+userIP;
                break;
            case USER_CONNECTED :
                message = userIP+" connected";
                break;
            case USER_DISCONNECTED :
                message = userIP+" disconnected";
                break;
        }
        
        id = lastID + "1"; //change to normal gen id
        
    }
    
}
