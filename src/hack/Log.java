/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hack;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Dima
 */
public class Log implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Type {FILE_DELETED,FILE_COPIED,PROTECT_DISABLED,USER_CONNECTED,USER_DISCONNECTED}; //if u change this - change the constructor switch
    
    public Type type;
    
    public String message;
    
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    public Log(Type type, String userIP) {
        
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
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.type);
        hash = 83 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    
    
    
}
