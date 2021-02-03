package example01;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/example01_servlet/*")
public class example01contreller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProc(request, response);
	}
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String path=request.getContextPath();
		String url=request.getRequestURL().toString();
		String page="";
		
		String pageNumber_;
		pageNumber_=request.getParameter("pageNumber");
		if(pageNumber_ == null || pageNumber_.trim().equals("")) {
			pageNumber_="1";
		}
		int pageNumber=Integer.parseUnsignedInt(pageNumber_);
		
		if(url.indexOf("memo.do")!= -1) {
			
			page="/memo/memo.jsp";
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}else if(url.indexOf("memoProc.do")!= -1) {
			
			String name=request.getParameter("name");
			String memo=request.getParameter("memo");
		
			ExampleDTO dto = new ExampleDTO();
			dto.setName(name);
			dto.setMemo(memo);
		
			
			Example01DAO dao = new Example01DAO();
			int result=dao.setInsert(dto);	
			
		}else if(url.indexOf("list.do")!= -1) {
			Example01DAO dao = new Example01DAO();
			
			//페이징 처리소스 
			int pageSize=10; //레코드(튜플)을 10개 보여주는것
			int blockSize=10;//10개의 이동단위가 나타남[1],[2],[3]...
			int totalRecord = dao.getTotalRecord();  // 레코드 총갯수
			int jj= totalRecord-pageSize*(pageNumber-1);
			int startRecord = pageSize*(pageNumber-1)+1;
			int lastRecord = pageSize*pageNumber;
			 
			int totalPage=0;
			int startPage=1; 
			int lastPage=1;
			
			//방법1
			if(totalRecord > 0) {
				totalPage=totalRecord/pageSize+(totalRecord % pageSize == 0 ? 0:1);
				startPage=(pageNumber/blockSize - (pageNumber % blockSize != 0? 0:1)) * blockSize + 1;
			
			
			lastPage = startPage + blockSize - 1;
			
			if(lastPage>totalPage) {
			   lastPage=totalPage;
			}
		}	

			ArrayList<ExampleDTO> list =dao.getSelectAll(startRecord,lastRecord);		
		
			request.setAttribute("list", list); 
			
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("jj", jj);
			
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
			
			
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
		
			page = "/memo/list.jsp"; 
			RequestDispatcher rd= request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}
		
		
		
	
	
	
	
	
	}
}
