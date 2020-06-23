
package DAO;

import Factory.ConexionDB;
import Factory.FactoryConexionDB;
import Model.Categoria;
import Model.Producto;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author castr
 */
public class ProductoDAOImplementar implements ProductoDAO {
    
ConexionDB conn;  //Crear el objeto tipo conexión.


public ProductoDAOImplementar() {
//Definir a la base de datos que se conectará por defecto.   
}



//METODO PARA LISTAR LOS PRODUCTOS 
@Override
public List<Producto> Listar() {
this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
StringBuilder miSQL = new StringBuilder();

miSQL.append("SELECT * FROM tb_producto;");//LA CONSULTA

List<Producto> lista = new ArrayList<Producto>();
try{
            
//Se crea el objeto ResultSet implementando el método (consultaSQL) creado para la consulta.
ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());

while(resultadoSQL.next()){
    
Producto producto = new Producto();

//Asignar cada campo consultado al atributo en la clase.

producto.setId_producto(resultadoSQL.getInt("id_producto"));
producto.setNom_producto(resultadoSQL.getString("nom_producto"));
producto.setDes_producto(resultadoSQL.getString("des_producto"));
producto.setStock(resultadoSQL.getFloat("stock"));
producto.setPrecio(resultadoSQL.getFloat("precio"));
producto.setUnidadMedida(resultadoSQL.getString("unidad_de_medida"));
producto.setEstado(resultadoSQL.getInt("estado_producto"));
producto.setCategoria(null); //AQUI NO PUDE PONER LO DE CATEGORIA AYUDA
producto.setFecha_entrada(LocalDateTime.now());



lista.add(producto); //Agregar al array cada registro encontrado.
}
}catch(Exception ex){
            
}finally{
this.conn.cerrarConexion();
}
        
return lista;
    
}

    

@Override
public List<Producto> Listar2() {
 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
}

    
    
    
    
//METODO PARA EDITAR 
@Override
public Producto editarPro(int id_pro_edit) {
    
this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);

Producto producto = new Producto();
StringBuilder miSQL = new StringBuilder();
//Agregar la consulta SQL.


miSQL.append("SELECT * FROM tb_producto WHERE id_producto = ").append(id_pro_edit);

//Realizar la consulta.
try{
ResultSet resultadoSQL = this.conn.consultaSQL(miSQL.toString());

while(resultadoSQL.next()){
producto.setId_producto(resultadoSQL.getInt("id_producto"));
producto.setNom_producto(resultadoSQL.getString("nom_producto"));
producto.setDes_producto(resultadoSQL.getString("des_producto"));
producto.setStock(resultadoSQL.getFloat("stock"));
producto.setPrecio(resultadoSQL.getFloat("precio"));
producto.setUnidadMedida(resultadoSQL.getString("unidad_de_medida"));
producto.setEstado(resultadoSQL.getInt("estado_producto"));
producto.setCategoria(null); //AQUI NO PUDE PONER LO DE CATEGORIA AYUDA
producto.setFecha_entrada(LocalDateTime.now());


}

}catch(Exception e){
}finally{
this.conn.cerrarConexion();
}      
return producto;
}




//METODO PARA GUARDAR LOS PRODUCTOS

@Override
public boolean guardarPro(Producto producto) {

    
 this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);
     
boolean guardar = false; //BANDERA DE RESULTADO
try{
    
if(producto.getId_producto() ==0){ //PARA CUANDO ES UN NUEVO PRODUCTO

StringBuilder miSQL = new StringBuilder();
//AGREGA CONSULTA SQL EL ID_PRODUCTO  ES AUTOINCREMNTABLE

//AQUI CREO QUE ESTA EL PROBLEMA LAS COMILLAS NOSE 

miSQL.append("INSERT INTO tb_producto (nom_producto , des_producto, stock, precio, unidad_de_medida, estado_producto, categoria,  fecha_entrada ) VALUES (' ");
miSQL.append(producto.getNom_producto() + " ', ").append(producto.getDes_producto()+" ',").append
                        (producto.getStock()+ " ' ,").append(producto.getPrecio() +" ' ,").append(producto.getUnidadMedida() + " ', ").append
                       (producto.getEstado() +" ', ").append(producto.getCategoria()+ " ',").append(producto.getFecha_entrada());
                       miSQL.append(");");
                
                
//Invocar método para ejecutar la consulta.
                
this.conn.ejecutarSQL(miSQL.toString());

System.out.println("Registro Guardado...");

}else if(producto.getId_producto()>0){//ACTUALIZAR ID MAYORES A 0
    
System.out.println("Entramos...");
    
//MAS CODIGO AGREGADO

StringBuilder miSQL = new StringBuilder();
miSQL.append("UPDATE tb_producto SET id_producto = ").append(producto.getId_producto());
miSQL.append(", nom_producto =  '").append(producto.getNom_producto());
miSQL.append("', des_producto =  ").append(producto.getDes_producto());
miSQL.append("', stock =  ").append(producto.getStock());
miSQL.append("', precio =  ").append(producto.getPrecio());
miSQL.append("', unidad_de_medida =  ").append(producto.getUnidadMedida());
miSQL.append("', estado_producto=  ").append(producto.getEstado());
miSQL.append("',categoria=  ").append(producto.getCategoria());
miSQL.append("', fecha_entrada=  ").append(producto.getFecha_entrada());

miSQL.append(" WHERE id_producto = ").append(producto.getId_producto()).append(";");
          
//Invocar método para ejecutar la consulta.
this.conn.ejecutarSQL(miSQL.toString());

//AQUI IRA UN SOUT
System.out.println("Registro modificado correctamente!");
}
guardar =true;  //ESTO LO MODIFICAMOS
            
}catch(Exception e){
            
}finally{
this.conn.cerrarConexion();
}
return guardar;
}




@Override
public boolean borrarPro(int id_pro_borrar) {
    
this.conn = FactoryConexionDB.open(FactoryConexionDB.MySQL);

boolean borrar = false;//Bandera de resultados
try{
    
StringBuilder miSQL = new StringBuilder();

miSQL.append("DELETE FROM tb_producto WHERE id_producto = ").append(id_pro_borrar);
this.conn.ejecutarSQL(miSQL.toString());
borrar = true;
}catch(Exception e){
            
}finally{
this.conn.cerrarConexion();  //Cerrar la conexión.
}
       
return borrar;
}
}
    