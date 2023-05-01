package com.ssafy.enjoytrip.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.enjoytrip.model.dto.Place;
import com.ssafy.enjoytrip.model.dto.User;
import com.ssafy.enjoytrip.model.dto.Worldcup;
import com.ssafy.enjoytrip.model.dao.BoardDaoImpl;
import com.ssafy.enjoytrip.model.service.PlaceServiceImpl;
import com.ssafy.enjoytrip.model.service.UserServiceImpl;
import com.ssafy.enjoytrip.model.service.WorldcupServiceImpl;


/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		try {
			if (action == null) {
				redirect(request, response, "/index.jsp");
			}else if ("login".equals(action)) {
				login(request, response);
			}else if ("logout".equals(action)) {
				logout(request, response);
				forward(request, response, "/index.jsp");
			}else if ("mvtrip".equals(action)) {
				moveTrip(request, response);
				forward(request, response, "/trip/themetrip.jsp");
			}else if ("mvplan".equals(action)) {
				movePlan(request, response);
				forward(request, response, "/trip/myplan.jsp");
			}else if ("mvworldcup".equals(action)) {
				moveWorldcup(request, response);
				forward(request, response, "/trip/worldcup.jsp");
			}else if ("worldcup".equals(action)) {
				worldcup(request, response);
				forward(request, response, "/trip/worldcupResult.jsp");
			}else if ("mvrecommend".equals(action)) {
				moveRecommend(request, response);
				forward(request, response, "/trip/recommend.jsp");
			}else if ("mvboard".equals(action)) {
				moveBoard(request, response);
				forward(request, response, "/board/board.jsp");
			}else if ("userregist".equals(action)) {
				doRegistUser(request, response);
				forward(request, response, "/index.jsp");
			}else if ("mvMypage".equals(action)) {
				mvMypage(request, response);
				forward(request, response, "/user/mypage.jsp");
			}else if("deleteUser".equals(action)) {
				deleteUser(request, response);
				redirect(request, response, "/index.jsp");
			}else if("updateUser".equals(action)) {
				updateUser(request, response);
			}else if("mvNoticeBoardWrite".equals(action)) {
				moveNoticeBoardWrite(request, response);
				forward(request, response, "/board/noticeWrite.jsp");
			}else if("boardNoticeWrite".equals(action)) {
				noticeBoardWrite(request, response);
				redirect(request, response, "/board/board.jsp");
			}else if("searchPlace".equals(action)) {
				searchPlace(request, response);
				forward(request, response, "/trip/themetrip.jsp");
			}else if("searchPlan".equals(action)) {
				searchPlace(request, response);
				forward(request, response, "/trip/myplan.jsp");
			}else if("savePlan".equals(action)) {
				savePlan(request, response);
				forward(request, response, "/index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect(request, response, "/error/error.jsp");
		}
	}
	protected void noticeBoardWrite(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String title = request.getParameter("title");
		String content  = request.getParameter("content");
		
		BoardDaoImpl.getBoardDaoImpl().registNoticeBoard(title, content, u);

		System.out.println("등록성공");
	}

	
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		String changpw = request.getParameter("changpw");
		String changpw2 = request.getParameter("changpw2");
		String originalpw = request.getParameter("originalpw");
		boolean check = UserServiceImpl.getUserService().updateUser(u, originalpw, changpw, changpw2);
		if(check) {
			request.setAttribute("msg", "비밀번호 변경 성공! 다시 로그인해주세요!");
			session.invalidate();
			forward(request, response, "/index.jsp");
		}else {
			request.setAttribute("msg", "비밀번호 변경 실패!");
			forward(request, response, "/user/mypage.jsp");
		}
	}
	
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		UserServiceImpl.getUserService().deleteUser(u);
		
		session.invalidate();
	}
	
	protected void mvMypage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
	}
	
	protected void doRegistUser(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("regid");
		String name = request.getParameter("nickname");
		String pass = request.getParameter("regpw");
		String email = request.getParameter("email");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(pass.getBytes("UTF-8"));
        pass = new String(Base64.getEncoder().encode(messageDigest.digest()));
		User user = new User(id,name,pass,email);
		try {
			int result = UserServiceImpl.getUserService().registUser(user);
			request.setAttribute("msg", result<0?"회원가입에 실패했습니다.":"정상적으로 회원가입 되었습니다.");
		}catch(Exception e) {
			request.setAttribute("msg", "회원가입에 실패했습니다 : "+(e.getMessage().contains("Duplicate")?"이미 같은 아이디의 회원이 존재합니다":"잠시 후 다시 시도해보세요"));
			System.out.println(e.getMessage());
		}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		String id = request.getParameter("userid");
		String pass = request.getParameter("userpw");
	
		
	        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	        messageDigest.update(pass.getBytes("UTF-8"));
	        pass = new String(Base64.getEncoder().encode(messageDigest.digest()));
	        System.out.println(pass);
	  
		User u = UserServiceImpl.getUserService().getUser(id);
		
		if(u == null || !u.getPassword().equals(pass)) {
			request.setAttribute("msg", "로그인 실패!!!");
			forward(request, response, "/index.jsp");
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			redirect(request, response, "/index.jsp");
		}
	}
	protected void template(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	}
	protected void moveTrip(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO : feat
	}
	protected void movePlan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO : feat
	}
	protected void moveWorldcup(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArrayList<Worldcup> worldcups = WorldcupServiceImpl.getWorldcupService().getWorldcupList();
		Collections.shuffle(worldcups);
		HttpSession session = request.getSession();
		session.setAttribute("worldcups", worldcups);
//		session.setAttribute("worldcups", new ArrayDeque<Worldcup>(worldcups));
		System.out.println(worldcups);
	}
	protected void worldcup(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int s = Integer.parseInt(request.getParameter("selected"));
		System.out.println(s);
		WorldcupServiceImpl.getWorldcupService().updateWorldcupWins(s);
		Worldcup selectedWorldcup = WorldcupServiceImpl.getWorldcupService().getWorldcup(s);
		ArrayList<Worldcup> worldcups = WorldcupServiceImpl.getWorldcupService().getWorldcupList();
		Collections.sort(worldcups, (o1,o2)->Integer.compare(o1.getWins(), o2.getWins())*-1);
		double totalWin = 0;
		for(Worldcup w : worldcups) totalWin+=w.getWins();
		HttpSession session = request.getSession();
		session.setAttribute("selectedWorldcup", selectedWorldcup);
		session.setAttribute("worldcups", worldcups);
		session.setAttribute("totalWin", totalWin);
	}
	protected void moveRecommend(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO : feat
	}
	protected void moveNoticeBoardWrite(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO : feat
	}
	protected void moveBoard(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO : feat
	}
	protected void searchPlace(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int sido = Integer.parseInt(request.getParameter("sido"));
		int type = Integer.parseInt(request.getParameter("type"));
		String keyword = request.getParameter("keyword");
		if(sido==0||type==0||keyword==null||keyword.length()==0) {
			request.setAttribute("msg", "검색 조건이 올바르지 않습니다.");
			return;
		}
		ArrayList<Place> places = PlaceServiceImpl.getPlaceService().searchPlace(sido, type, keyword);
		System.out.println(places);
		request.setAttribute("places", places);
	}
	protected void savePlan(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO : 저장
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("user");
		if(u==null) request.setAttribute("msg", "로그인이 필요합니다.");
		else request.setAttribute("msg", "저장되었습니다.");
	}

	protected void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}

	protected void redirect(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + path);
	}
}
