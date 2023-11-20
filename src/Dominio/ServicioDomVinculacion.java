package Dominio;

import Modelos.Respuesta;
import Modelos.Telefono;

public class ServicioDomVinculacion {
    
    public Respuesta VerificarVincular(Telefono tel, int User){
        
        if(tel.getId() == 0){
            return new Respuesta("Seleccione un Teléfono para Vincular.");
        }
        
        if(User == 0){
            return new Respuesta("Seleccione un Usuario para Vincular.");
        }
        
        if(tel.getIdUsuario() == User){
            return new Respuesta("El Usuario ya tiene Vinculado este Teléfono.");
        }
        
        if(tel.getIdUsuario() != 0 && tel.getIdUsuario() != User){
            return new Respuesta("El Teléfono ya tiene Vinculado a otro Usuario.");
        }
        
        return new Respuesta();
    }
    
        public Respuesta VerificarDesvincular(Telefono tel){
        
        if(tel.getId() == 0){
            return new Respuesta("Seleccione un Teléfono para Desvincular.");
        }
        
        if(tel.getIdUsuario() == 0){
            return new Respuesta("La acción no puede cpncluirse ya que el Teléfono no tiene algún Usuario Vinculado..");
        }
        
        return new Respuesta();
    }
}
