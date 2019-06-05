package util;

public class EmailDisenio {
    
    public static String DisenioAlta(int id){
        String enlace = "/Vistas/activa.action?accion="+id;
        String mensaje = "<HTML><BODY>"
                + "<h1>Para confirmar su usuario </h1><a href=\""+enlace+"\">Presione este enlace</a>"
                + "</BODY></HTML>";
        return mensaje;
    }
    
    public static String DisenioCompra(String to, String file){
        String mensaje = "<HTML><BODY>"
                + "Se adjunta la factura"
                + "</BODY></HTML>";
        //para agregar imágenes pongo dentro del body
        //<img src='cid:cidnombre' />
        //colocando en el campo cidnombre el nombre que quiera
        return mensaje;
    }
    
    public static String DisenioApadrina(String nombre, String email, String u){
        String enlace = "/Vistas/altaUsuarioApadrinado.action?usuEmail="+email;
        String mensaje = "<HTML><BODY>"
                + "<h1>Hola <b>"+nombre+"</b>!</h1><br>"
                + "<p>Este email te llega porque "+u+" "+u
                +" te está enviando esta invitación para que te puedas dar de alta en nuestra tienda beneficiándote en"
                + " tus compras con un % de descuento"
                + " durante  días!<br>"
                + "Para poder darte de alta por favor pincha en el siguiente enlace:<br>"
                + "<a href=\""+enlace+"\">Pincha aquí para darte de alta</a></p>"
                + "</BODY></HTML>";
        return mensaje;        
    }
    
    public static String DisenioRecuperaPass(String u){
        String enlace = "/Vistas/Tienda.action";
        String mensaje = "<HTML><BODY>"
                + "<h1>Hola <b>"+u+" "+u+"</b>!</h1><br>"
                + "<p>Este email te llega porque has pedido recuperar tu contraseña en nuestra tienda. "
                + "Te hacemos llegar tu nueva contraseña.<br><br>"
                + "Usuario: "+u+"<br>"
                + "Contraseña: nueva<br><br>"
                + "Para abrir la página de nuestra tienda por favor pincha en el siguiente enlace:<br>"
                + "<a href=\""+enlace+"\">Super Look</a></p>"
                + "</BODY></HTML>";
        return mensaje;        
    }
}
