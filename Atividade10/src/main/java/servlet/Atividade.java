package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Atividade")
public class Atividade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Atividade() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		LivroDAO dao = new LivroDAO();
		String action = request.getParameter("action");
		
		if (action.equals("codigo")) {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			List<Livro> livros = dao.getListaByCod(codigo);
			int quantidade = livros.size();
			
			if (quantidade > 0) {
				for(Livro livro:livros) {
					out.println("<html>");
					out.println("<body>");
					out.println("Cod: " + livro.getCodlivro() + "<br>");
					out.println("Titulo: " + livro.getTitulo() + "<br>");
					out.println("Autor: " + livro.getAutor() + "<br>");
					out.println("Categoria: " + livro.getCategoria() + "<br>");
					out.println("Valor: " + livro.getValor() + "<br>");
					out.println("</body>");
					out.println("</html>");
				}
			} else {
				out.println("<html>");
				out.println("<body>");
				out.println("<h1>Não existem livros cadastrados nesse código</h1>");
				out.println("</body>");
				out.println("</html>");
			}
			
		} else if (action.equals("categoria")) {
			String categoria = request.getParameter("categoria");
			List<Livro> livros = dao.getListaByCat(categoria);
			int quantidade = livros.size();
			
			if (quantidade > 0) {
				for(Livro livro:livros) {
					out.println("<html>");
					out.println("<body>");
					out.println("Cod: " + livro.getCodlivro() + "<br>");
					out.println("Titulo: " + livro.getTitulo() + "<br>");
					out.println("Autor: " + livro.getAutor() + "<br>");
					out.println("Categoria: " + livro.getCategoria() + "<br>");
					out.println("Valor: " + livro.getValor() + "<br>");
					out.println("<hr>");
					out.println("</body>");
					out.println("</html>");
				}
			} else {
				out.println("<html>");
				out.println("<body>");
				out.println("<h1>Essa categoria não possui livros cadastrados</h1>");
				out.println("</body>");
				out.println("</html>");
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		PrintWriter out = response.getWriter();
		Livro livro = new Livro();
		LivroDAO dao = new LivroDAO();
		
		String action = request.getParameter("action");
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		if(action.equals("insert")) {
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String categoria = request.getParameter("categoria");
			float valor = Float.parseFloat(request.getParameter("valor"));
			
			livro.setCodlivro(codigo);
			livro.setTitulo(titulo);
			livro.setAutor(autor);
			livro.setCategoria(categoria);
			livro.setValor(valor);
			
			try {
				dao.adiciona(livro);
				out.println("Livro " + livro.getTitulo() + " foi adicionado");
			}catch(Throwable e) {
				out.println("Deu ERRO");
				e.printStackTrace();
			}
			
		} else if (action.equals("delete")) {
			livro.setCodlivro(codigo);
			
			try {
				dao.remover(livro);
				out.println("Livro " + livro.getCodlivro() + " foi deletado");
			}catch(Throwable e) {
				out.println("Deu ERRO");
				e.printStackTrace();
			}
			
		} else if (action.equals("update")) {
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String categoria = request.getParameter("categoria");
			float valor = Float.parseFloat(request.getParameter("valor"));
			
			livro.setCodlivro(codigo);
			livro.setTitulo(titulo);
			livro.setAutor(autor);
			livro.setCategoria(categoria);
			livro.setValor(valor);
			
			try {
				dao.alterar(livro);
				out.println("Livro " + livro.getTitulo() + " foi alterado");
			}catch(Throwable e) {
				out.println("Deu ERRO");
				e.printStackTrace();
			}
		} 
	}

}
