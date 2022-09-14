package com.veer.voat.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.veer.voat.Dao.CandidateRepository;
import com.veer.voat.Dao.UserRepository;
import com.veer.voat.Dao.VoteDetailsRepository;
import com.veer.voat.model.Candidate;
import com.veer.voat.model.MyResponse;
import com.veer.voat.model.User;
import com.veer.voat.model.VoteDeatails;

@Controller
public class MainController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CandidateRepository candidateRepo;
	
	@Autowired
	private VoteDetailsRepository voteRepo;
	
	
	@GetMapping("/register")
	public String REgistrationPage() {
		
		return "Registration";
	}

	
	@GetMapping("/login")
	public String Login() {
		
		return "login";
	}
	
	@PostMapping("/doregister")
	public String RegisterProcess(@ModelAttribute("user") User user) {
		System.out.println("data is : "+user);
		userRepo.save(user);
		return "login";
	}
	
	@PostMapping("/dologin")
	public String LoginProcess(Model model, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
		try {
			System.out.println("data is : "+username+" and "+password);
			User user = userRepo.findByUsernameAndPassword(username, password);
			System.out.println("user for login : "+user);
			System.out.println(user.getUsername());
			if(("admin".equals(user.getUsername())) && ("admin".equals(user.getPassword()))){
				session.setAttribute("username", user.getUsername());
				return "redirect:/admin";
			}
			if(user != null) {
				session.setAttribute("username", username);
				model.addAttribute("candidatelist", candidateRepo.findAll());
				System.out.println("is voted : "+ user.isVoted());
				if(user.isVoted() == true) {
					model.addAttribute("isvoted",voteRepo.findByUserId(user.getUserId()).getCandidateid());
				}
				
				if(user.isVoted()== true) {
					session.setAttribute("message", new MyResponse("You are already voted", "alert-danger"));
				}
				
			return "vote";
			}
			else {
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
		
	}
	
	
	@PostMapping("/dovote")
	public String VoteProcess(@RequestParam("exampleRadios") int exampleRadios, HttpSession session, Model model){
		try {
		String username = (String) session.getAttribute("username");
		User user = userRepo.findByUsername(username);
		if(user.isVoted()== true) {
			session.setAttribute("message", new MyResponse("You are already voted", "alert-danger"));
			model.addAttribute("candidatelist", candidateRepo.findAll());
			model.addAttribute("isvoted",voteRepo.findByUserId(user.getUserId()).getCandidateid());
			return "vote";
		}
		user.setVoted(true);
		userRepo.save(user);
		session.setAttribute("message", new MyResponse("You have successfully voted", "alert-success"));
		System.out.println("data is : "+exampleRadios);
		Optional<Candidate> candidate = this.candidateRepo.findById(exampleRadios);
		System.out.println("candidate data: "+candidate);
		int voteCounts = candidate.get().getVoteCounts();
		voteCounts=voteCounts+1;
		System.out.println("voteCounts : "+voteCounts);
		candidate.get().setVoteCounts(voteCounts);
		candidateRepo.save(candidate.get());
		
		VoteDeatails voteDeatails = new VoteDeatails();
		voteDeatails.setUserId(user.getUserId());
		voteDeatails.setCandidateid(candidate.get().getCandidateId());
		
		voteRepo.save(voteDeatails);
		
//		session.setAttribute("vote", new MyResponse("checked", exampleRadios));
		model.addAttribute("candidatelist", candidateRepo.findAll());
		model.addAttribute("isvoted",voteRepo.findByUserId(user.getUserId()).getCandidateid());
		}catch(Exception e) {
			e.printStackTrace();
			return "vote";
		}
		return "vote";
	}
	
	
	
	@GetMapping("/admin")
	public String Admin( Model model) {
		List<Candidate> candidates = candidateRepo.findAll();
		model.addAttribute("data", candidates);
		return "AdminPage";
	}
	
	
	@GetMapping("/vote")
	public String ShowVote(Model model, HttpSession session) {
		try {
			String username = (String) session.getAttribute("username");
			User user = userRepo.findByUsername(username);
			model.addAttribute("candidatelist", candidateRepo.findAll());
			model.addAttribute("isvoted",voteRepo.findByUserId(user.getUserId()).getCandidateid());
			if(user.isVoted()== true) {
				session.setAttribute("message", new MyResponse("You are already voted", "alert-danger"));
				return "vote";
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "vote";
	}
	
	
	@GetMapping("/logout")
	public String LogoutProcess(HttpSession session) {
		session.setAttribute("logoutMessage", new MyResponse("You have successfully Logged out!!!", "alert-success"));
		return "login";
	}
}
