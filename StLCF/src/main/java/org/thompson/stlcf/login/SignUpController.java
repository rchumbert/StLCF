package org.thompson.stlcf.login;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thompson.stlcf.config.JdbcConnection;
import org.thompson.stlcf.user.donor.Donor;
import org.thompson.stlcf.user.donor.Nonprofit;

@Controller
public class SignUpController
	{
		List<Donor> donors = new ArrayList<Donor>();
		List<Nonprofit> np = new ArrayList<Nonprofit>();

		@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
		public String contactSubmit(@ModelAttribute Donor donor, BindingResult bindingResult, Model model)
			{
				model.addAttribute("donor", donor);
				return "donor-signup";
			}
		
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String index()
		{
			return "search";
		}
		@RequestMapping(value = "/sign-up", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		@ResponseBody
		public String addUser(Donor donor) throws Exception
			{
				if (donor.getPasswd().equals(donor.getConfirmPasswd()))
					{
						JdbcConnection con = new JdbcConnection();
						if (con.getEmail(donor.getEmail(), con.connect())==null)
							{
								try
									{
										con.addUser(donor, con.connect());
									} catch (SQLException e)
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (Exception e)
									{
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								donors.add(donor);
								return "Registered!";
							} else
							return "That Email has already been used. please click  <a href=/sign-up> here</a> to retry";
					} else
					return "Passwords do not match, please click <a href=/sign-up> here</a> to retry";
			}

		@RequestMapping(value = "/users", method = RequestMethod.GET)
		@ResponseBody
		public String showUsers()
			{
				return donors+"\n \n"+np;
			}
		
		@RequestMapping(value ="/np-signup", method = RequestMethod.GET)
		public String addNP(@ModelAttribute Nonprofit np, BindingResult bindingResult, Model model)
		{
			model.addAttribute("np", np);
			return "nonprof-signup";
		}
		
		@RequestMapping(value = "/np-signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		@ResponseBody
		public String addNP(Nonprofit nonp) throws Exception
			{
				np.add(nonp);
				if(nonp.getPass().equals(nonp.getConfirm()))
					{
						JdbcConnection con = new JdbcConnection();
						if (con.getNpEmail(nonp.getEmail(), con.connect())==null)
							{
								if(con.getNpName(nonp.getName(), con.connect())==null)
									{
									con.addUser(nonp, con.connect());
									return "Registered!";
									}
								else
									return "Name already used, please click <a href=/np-signup> here </a> to retry";
							}
						else return "Email already used, please click <a href=/np-signup> here</a> to retry";
					}
				else
					return "Passwords do not match, please click <a href=/np-signup> here</a> to retry" ;
				
			}
		
	}
