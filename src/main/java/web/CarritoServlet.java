package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset-UTF-8");
        //PROCESAr el nuevo articulo
        String articuloNuevo = request.getParameter("articulo");
        //creamos o recuperamos el objeto httpsesion
        HttpSession sesion = request.getSession();
        //recuperar la lista de articulos previos si es que existen
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        //si no existe el valor es nulo
        //verificacmos si la lista existe
        if (articulos == null) {
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);//agrega una lista vacia a la sesion

        }
        //revisamos y agregamos el articulo nuevo
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
        }
        try ( //mandar a imprimir la lista de articulos
                PrintWriter out = response.getWriter()) {
            out.print("<h1>Lista de articulos</h1>");
            
            out.print("<br>");
            //iteramos todos los articulos
            for (String articulo : articulos) {
                out.print("<li>" + articulo + "</li>");
            }
            //agregamos un link para regresar al inicio
            out.print("<br>");
            out.print("<a href='/EjemploCarritoCompras'> Regresar al inicio </a>");
        }
    }
}
