package Controller;

import DAO.UsuarioDAO;
import DAO.UsuarioDAOImplementar;
import Model.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controlador extends HttpServlet {
    UsuarioDAO dao = new UsuarioDAOImplementar();
    Usuarios p = new Usuarios();
    int r;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       response.setContentType("text/html;charset=UTF-8");
       String accion = request.getParameter("accion");
            if(accion.equals("Ingresar")){
                    String nom=request.getParameter("txtnom");
                    String Correo=request.getParameter("txtCorreo");
                    p.setNombre(nom);
                    p.setCorreo(Correo);
                    try {
                        System.out.println("Estado busqueda " + dao.validar(p));
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    if(dao.validar(p) == 1){
                        // Variable que contendra la session 
                        HttpSession Logeado = request.getSession(true);
                        // Asigno los valores a la session 
                        Logeado.setAttribute("datosUsuario", p); // objeto de tipo persona con los valores
                                                                 // nombre y correo asignados
                        // Revisar vista principal el codigo de ahi lo penga en donde quiere que
                        // que se compruebe que hay una session activa
                        request.getRequestDispatcher("Principal.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
            }else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}