package org.thompson.stlcf.user.donor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Donor
	{
		@NotNull
		@Size (min=2, max=30)
		private String fName;
		@NotNull
		@Size (min=2, max=30)
		private String lName;
		@NotNull
		@Email
		private String email;
		@NotNull
		@Size(min=5,max=5)
		private Integer zip;
		@NotNull
		@Size(min=8, max=16)
		private String passwd;
		@NotNull
		@Size(min=8, max=16)
		private String confirmPasswd;
		
		
		public String getfName()
			{
				return fName;
			}
		public void setfName(String fName)
			{
				this.fName = fName;
			}
		public String getlName()
			{
				return lName;
			}
		public void setlName(String lName)
			{
				this.lName = lName;
			}
		public String getEmail()
			{
				return email;
			}
		public void setEmail(String email)
			{
				this.email = email;
			}
		public Integer getZip()
			{
				return zip;
			}
		public void setZip(Integer zip)
			{
				this.zip = zip;
			}
		public String getPasswd()
			{
				return passwd;
			}
		public void setPasswd(String passwd)
			{
				this.passwd = passwd;
			}
		public String getConfirmPasswd()
			{
					return confirmPasswd;
			}
		public void setConfirmPasswd(String confirmPasswd)
			{
					this.confirmPasswd = confirmPasswd;
			}
		
		
	}
