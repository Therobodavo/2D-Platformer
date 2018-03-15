package Files;

import java.util.ArrayList;

import Weapons.Weapon;

public class Account 
{
	private String username = "";
	private String password = "";
	private ArrayList<Weapon> armory = new ArrayList<Weapon>();
	private int coinbal = 0;
    
	public boolean LogIn(String name, String pass)
	{
		boolean isUser = false;
		if(name == username && password == pass)
		{
			isUser = true;
		}
		return isUser;
	}
}
